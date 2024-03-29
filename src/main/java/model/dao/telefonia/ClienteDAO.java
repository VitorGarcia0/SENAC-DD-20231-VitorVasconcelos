package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.seletor.ClienteSeletor;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;

public class ClienteDAO {

	public ClienteVO consultarPorId(int id) {
		ClienteVO clienteBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {

			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				clienteBuscado = montarClienteComResultadoDoBanco(resultado);

			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id + "\nCausa: " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return clienteBuscado;
	}

	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT COUNT(*) FROM CLIENTE " + " WHERE CPF = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {

			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;

			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF: " + cpfBuscado + "\nCausa: " + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return cpfJaUtilizado;
	}

	public ClienteVO inserir(ClienteVO novoCliente) {
		// CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF, ID_ENDERECO, ATIVO) " + " VALUES (?,?,?,?) ";

		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		// EXECUTAR O INSERT
		try {
			query.setString(1, novoCliente.getNome());
			query.setString(2, novoCliente.getCpf());
			query.setInt(3, novoCliente.getEndereco().getId());
			query.setBoolean(4, novoCliente.isAtivo());
			// query.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));

			query.execute();

			// Prencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
			}

			if (!novoCliente.getTelefones().isEmpty()) {
				TelefoneDAO telefoneDAO = new TelefoneDAO();
				telefoneDAO.ativarTelefones(novoCliente.getId(), novoCliente.getTelefones());
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir o Cliente. " + "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novoCliente;
	}

	public boolean atualizar(ClienteVO cliente) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE CLIENTE SET NOME=?, CPF=?, ID_ENDERECO=?, ATIVO=? " + " WHERE ID = ?";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getEndereco().getId());
			stmt.setBoolean(4, cliente.isAtivo());
			stmt.setInt(5, cliente.getId());
			registrosAlterados = stmt.executeUpdate();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.ativarTelefones(cliente.getId(), cliente.getTelefones());
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		String sql = "DELETE FROM CLIENTE WHERE ID= " + id;
		Statement stmt = Banco.getStatement(conn);

		int quantidadeLinhasAfetadas = 0;

		try {
			quantidadeLinhasAfetadas = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		boolean excluiu = quantidadeLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.desativarTelefones(id);
		}

		return excluiu;
	}

	public List<ClienteVO> consultarTodos() {
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from cliente ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				ClienteVO clienteBuscado = montarClienteComResultadoDoBanco(resultado);
				clientes.add(clienteBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return clientes;

	}

	private ClienteVO montarClienteComResultadoDoBanco(ResultSet resultado) throws SQLException {
		ClienteVO clienteBuscado = new ClienteVO();
		clienteBuscado.setId(resultado.getInt("id"));
		clienteBuscado.setNome(resultado.getString("nome"));
		clienteBuscado.setCpf(resultado.getString("cpf"));
		clienteBuscado.setAtivo(resultado.getBoolean("ativo"));

		int idEnderecoDoCliente = resultado.getInt("id_endereco");
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		EnderecoVO enderecoVO = enderecoDAO.consultarPorId(idEnderecoDoCliente);
		clienteBuscado.setEndereco(enderecoVO);

		TelefoneDAO telefoneDAO = new TelefoneDAO();
		clienteBuscado.setTelefones(telefoneDAO.consultarPorIdCliente(clienteBuscado.getId()));

		return clienteBuscado;
	}

	public int contarClientesQueResidemNoEndereco(Integer idEndereco) {
		int totalClientesDoEnderecoBuscado = 0;
		Connection conexao = Banco.getConnection();
		String sql = " select count(id) from cliente " + " where id_endereco = ? ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idEndereco);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				totalClientesDoEnderecoBuscado = resultado.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Erro contar os clientes que residem em um endereço. \n Causa:" + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return totalClientesDoEnderecoBuscado;
	}

	public List<ClienteVO> consultarComFiltros(ClienteSeletor seletor) {
		List<ClienteVO> clientes = new ArrayList<ClienteVO>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from cliente ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		if (seletor.temPaginacao()) {
			// sql += " LIMIT " + seletor.getLimite() +
			// " OFFSET " + seletor.getOffset();
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				ClienteVO clienteBuscado = montarClienteComResultadoDoBanco(resultado);
				clientes.add(clienteBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return clientes;
	}

	private String preencherFiltros(String sql, ClienteSeletor seletor) {

		boolean primeiro = true;
		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		if (seletor.getCpf() != null && !seletor.getCpf().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " cpf LIKE '%" + seletor.getCpf() + "%'";
			primeiro = false;
		}

		if (seletor.getDataNascimentoInicial() != null && seletor.getDataNascimentoFinal() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " DT_NASCIMENTO BETWEEN '" + seletor.getDataNascimentoInicial() + "' " + " AND '"
					+ seletor.getDataNascimentoFinal() + "' ";
			primeiro = false;
		} else {
			if (seletor.getDataNascimentoInicial() != null) {
				if (primeiro) {
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				// CLIENTES QUE NASCERAM 'A PARTIR' DA DATA INICIAL
				sql += " DT_NASCIMENTO >= '" + seletor.getDataNascimentoInicial() + "' ";
				primeiro = false;
			}

			if (seletor.getDataNascimentoFinal() != null) {
				if (primeiro) {
					sql += " WHERE ";
				} else {
					sql += " AND ";
				}
				// CLIENTES QUE NASCERAM 'ATÉ' A DATA FINAL
				sql += " DT_NASCIMENTO <= '" + seletor.getDataNascimentoFinal() + "' ";
				primeiro = false;
			}
		}

		return sql;
	}

	public int contarTotalRegistrosComFiltro(ClienteSeletor seletor) {
		int total = 0;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from cliente ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				total = resultado.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Erro ao contar o total de clientes " + " \n Causa:" + e.getMessage());

		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return total;

	}

	public int contarTotalRegistrosComFiltros(ClienteSeletor seletor) {
		int total = 0;
		Connection conexao = Banco.getConnection();
		String sql = " select count(*) from cliente ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				total = resultado.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Erro contar o total de clientes" + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return total;
	}

}
