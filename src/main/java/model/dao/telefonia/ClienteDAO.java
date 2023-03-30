package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;
import model.vo.telefonia.TelefoneVO;

public class ClienteDAO {
	
	public ClienteVO consultarPorId(int id) {
		ClienteVO clienteBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM CLIENTE "
				+ " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
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
			System.out.println("Erro ao buscar cliente com id: " + id 
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
	
		return clienteBuscado;
	}
	
	
	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT COUNT(*) FROM CLIENTE "
				+ " WHERE CPF = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
							
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF: " + cpfBuscado 
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
	
		return cpfJaUtilizado;
	}
	
	public ClienteVO inserir(ClienteVO novoCliente) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF,"
				+ " ATIVO, ID_ENDERECO) "
				+ " VALUES (?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			if(novoCliente.getId() != null) {
				query.setInt(4, novoCliente.getId());
				
			} else {
				query.setInt(4, 0);
			}
			// OU query.setInt(5, novoTelefone.getIdCliente() != null ? novoTelefone.getIdCliente() : 0);
			
			query.setString(1, novoCliente.getNome());
			query.setString(2, novoCliente.getCpf());
			//query.setArray(3, novoCliente.getTelefones());
			query.setBoolean(3, novoCliente.isAtivo());
			query.setObject(4, novoCliente.getEndereco().getId());
			
			query.execute();
			
			// Prencher o id gerado no banco no objeto 
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoCliente.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir o Cliente. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}		
		return novoCliente;
	}
}

