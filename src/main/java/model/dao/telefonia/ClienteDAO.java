package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
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
				clienteBuscado = new ClienteVO();
				clienteBuscado.setId(resultado.getInt("id"));
				clienteBuscado.setNome(resultado.getString("nome"));
				clienteBuscado.setCpf(resultado.getString("cpf"));
				clienteBuscado.setAtivo(resultado.getBoolean("ativo"));

				int idEnderecoDoCliente = resultado.getInt("id_endereco");

				EnderecoDAO enderecoDAO = new EnderecoDAO();
				EnderecoVO enderecoDoCliente = enderecoDAO.consultarPorId(idEnderecoDoCliente);
				clienteBuscado.setEndereco(enderecoDoCliente);
				TelefoneDAO telefoneDAO = new TelefoneDAO();
				clienteBuscado.setTelefones(telefoneDAO.consultarPorIdCliente(clienteBuscado.getId()));

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
		String sql = " INSERT INTO CLIENTE(NOME, CPF," + " ATIVO, ID_ENDERECO) " + " VALUES (?, ?, ?, ?) ";

		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		// EXECUTAR O INSERT
		try {
			if (novoCliente.getId() != null) {
				query.setInt(4, novoCliente.getId());

			} else {
				query.setInt(4, 0);
			}
			// OU query.setInt(5, novoTelefone.getIdCliente() != null ?
			// novoTelefone.getIdCliente() : 0);

			query.setString(1, novoCliente.getNome());
			query.setString(2, novoCliente.getCpf());
			// query.setArray(3, novoCliente.getTelefones());
			query.setBoolean(3, novoCliente.isAtivo());
			query.setObject(4, novoCliente.getEndereco().getId());

			query.execute();

			// Prencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
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

			if (resultado.next()) {
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
}
