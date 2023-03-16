package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.vo.telefonia.Telefone;

public class TelefoneDAO {
	
	public Telefone inserir(Telefone novoTelefone) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO TELEFONE(IDCLIENTE, DDD, NUMERO,"
				+ " ATIVO, MOVEL) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			if(novoTelefone.getIdCliente() != null) {
				query.setInt(2, novoTelefone.getIdCliente());
				query.setInt(1, novoTelefone.getIdCliente());
				
			} else {
				query.setInt(5, 0);
			}
			query.setString(2, novoTelefone.getDdd());
			query.setString(3, novoTelefone.getNumero());
			query.setBoolean(4, novoTelefone.isAtivo());
			query.setBoolean(5, novoTelefone.isMovel());
			
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
}
