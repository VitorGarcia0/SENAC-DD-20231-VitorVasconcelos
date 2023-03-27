package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Banco;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;

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
}
