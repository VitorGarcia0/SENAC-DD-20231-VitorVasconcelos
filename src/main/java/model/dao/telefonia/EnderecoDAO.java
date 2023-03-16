package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.vo.telefonia.Endereco;

public class EnderecoDAO {
	//INSERT 
	//INSERT INTO ENDERECO (RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO)
	//VALUES ('', '', '', '', 'SC', '');
	/**
	 * Insere um novo endereco no banco
	 * @param novoEndereco o endereco a ser persistido
	 * @return o endereço inserido com a chave primária gerada
	 */
	
	public Endereco inserir(Endereco novoEndereco) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, CEP, BAIRRO, "
				+ " CIDADE, ESTADO, NUMERO) "
				+ " VALUES (?, ?, ?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getCep());
			query.setString(3, novoEndereco.getBairro());
			query.setString(4, novoEndereco.getCidade());
			query.setString(5, novoEndereco.getEstado());
			query.setString(6, novoEndereco.getNumero());
			query.execute();
			
			// Prencher o id gerado no banco no objeto 
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);	
		}
		
		return novoEndereco;
	}
}
