package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.vo.telefonia.TelefoneVO;

public class TelefoneDAO {
	
	public TelefoneVO inserir(TelefoneVO novoTelefone) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO TELEFONE(IDCLIENTE, DDD, NUMERO,"
				+ " ATIVO, MOVEL) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			if(novoTelefone.getIdCliente() != null) {
				query.setInt(5, novoTelefone.getIdCliente());
				
			} else {
				query.setInt(5, 0);
			}
			// OU query.setInt(5, novoTelefone.getIdCliente() != null ? novoTelefone.getIdCliente() : 0);
			
			query.setString(1, novoTelefone.getDdd());
			query.setString(2, novoTelefone.getNumero());
			query.setBoolean(3, novoTelefone.isAtivo());
			query.setBoolean(4, novoTelefone.isMovel());
			
			query.execute();
			
			// Prencher o id gerado no banco no objeto 
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoTelefone.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir o telefone. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}		
		
		return novoTelefone;
	}
	
	public boolean atualizar(TelefoneVO telefoneEditado) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE TELEFONE "
				+ " SET IDCLIENTE = ?, DDD = ?, NUMERO = ?, "
				+ " ATIVO = ?, MOVEL = ?, ESTADO = ? "
				+ " WHERE ID = ?";
		
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setInt(1, telefoneEditado.getIdCliente());
		query.setString(2, telefoneEditado.getDdd());
		query.setString(3, telefoneEditado.getNumero());
		query.setBoolean(4, telefoneEditado.isAtivo());
		query.setBoolean(5, telefoneEditado.isMovel());
		query.setInt(6, telefoneEditado.getId());			
		
		int quantidadeLinhasAtualizadas = query.executeUpdate();
		
		atualizou = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar Telefone"
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return atualizou;
	}
	
	public TelefoneVO consultarPorId(int id) {
		TelefoneVO telefoneConsultado = new TelefoneVO();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			if(resultado.next()) {
				telefoneConsultado = converterDeResultSerParaEntidade(resultado);				
			}		
			
		} catch(SQLException e) {
			System.out.println("Erro ao buscar telefone do id: " + id
					+ "\nCausa: " + e.getMessage());
		}
		return telefoneConsultado;
	}
	
	public List<TelefoneVO> consultarTodos() {
		TelefoneVO telefoneConsultado = new TelefoneVO();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				telefoneConsultado = converterDeResultSerParaEntidade(resultado);				
				telefones.add(telefoneConsultado);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos os telefones "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return telefones;	
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM TELEFONE "
				+ " WHERE ID = ?";
		
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao apagar o telefone "
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return excluiu;
	}
	
	
	
	private TelefoneVO converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		TelefoneVO telefoneConsultado = new TelefoneVO();
		telefoneConsultado.setId(resultado.getInt("id"));
		telefoneConsultado.setIdCliente(resultado.getInt("idCliente"));
		telefoneConsultado.setDdd(resultado.getString("ddd"));
		telefoneConsultado.setNumero(resultado.getString("numero"));
		telefoneConsultado.setAtivo(resultado.getBoolean("ativo"));
		telefoneConsultado.setMovel(resultado.getBoolean("movel"));
		return telefoneConsultado;
	}

	public List<TelefoneVO> consultarPorIdCliente(Integer id) {
		List<TelefoneVO> telefones = new ArrayList<TelefoneVO>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE "
				+ " WHERE ID_CLIENTE = ? ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {	
				TelefoneVO telefoneConsultado = converterDeResultSerParaEntidade(resultado);
				telefones.add(telefoneConsultado);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos os telefones do cliente informado "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return telefones;	
	}

	public void ativarTelefones(Integer idDono, List<TelefoneVO> telefones) {
		for (TelefoneVO telefoneDoCliente : telefones) {
			telefoneDoCliente.setIdCliente(idDono);
			telefoneDoCliente.setAtivo(true);
			if (telefoneDoCliente.getId() > 0) {
				// UPDATE no Telefone
				this.atualizar(telefoneDoCliente);
			} else {
				// INSERT no Telefone
				this.inserir(telefoneDoCliente);
			}
		}
	}

	public void desativarTelefones(int idCliente) {
		Connection conn = Banco.getConnection();
		String sql = " UPDATE EXEMPLOS.TELEFONE "
				   + " SET id_cliente= NULL, ativo= 0 "
				   + " WHERE ID_CLIENTE= ? ";

		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, idCliente);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao desativar telefone.");
			System.out.println("Erro: " + e.getMessage());
		}
	}

	
	
	
	
}
