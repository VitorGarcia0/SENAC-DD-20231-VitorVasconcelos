package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.vo.telefonia.EnderecoVO;

public class EnderecoDAO {
	//INSERT 
	//INSERT INTO ENDERECO (RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO)
	//VALUES ('', '', '', '', 'SC', '');
	/**
	 * Insere um novo endereco no banco
	 * @param novoEndereco o endereco a ser persistido
	 * @return o endere�o inserido com a chave prim�ria gerada
	 */	
	public EnderecoVO inserir(EnderecoVO novoEndereco) {
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
			System.out.println("Erro ao inserir endere�o. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);	
		}
		
		return novoEndereco;
	}
	//UPDATE ENDERECO
	//SET CEP = ?, RUA = ?, NUMERO = ?, BAIRRO = ?, 
	//CIDADE = ?, ESTADO = ?
	// WHERE ID = ?
	public boolean atualizar(EnderecoVO enderecoEditado) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO "
				+ " SET CEP = ?, RUA = ?, NUMERO = ?, "
				+ " BAIRRO = ?, CIDADE = ?, ESTADO = ? "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setString(1, enderecoEditado.getCep());
		query.setString(2, enderecoEditado.getRua());
		query.setString(3, enderecoEditado.getNumero());
		query.setString(4, enderecoEditado.getBairro());
		query.setString(5, enderecoEditado.getCidade());
		query.setString(6, enderecoEditado.getEstado());
		query.setInt(7, enderecoEditado.getId());			
		
		int quantidadeLinhasAtualizadas = query.executeUpdate();
		
		atualizou = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar endereço"
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return atualizou;
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO "
				+ " WHERE ID = ?";
			
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao apagar o endereço "
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return excluiu;
	}
	
	public EnderecoVO consultarPorId(int id) {
		EnderecoVO enderecoConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			if(resultado.next()) {
				enderecoConsultado = converterDeResultSerParaEntidade(resultado);
			}		
			
		} catch(SQLException e) {
			System.out.println("Erro ao buscar endereço com id: " + id
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return enderecoConsultado;
	}
	
	public List<EnderecoVO> consultarTodos() {
		EnderecoVO enderecoConsultado = new EnderecoVO();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<EnderecoVO> enderecos = new ArrayList<EnderecoVO>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				enderecoConsultado = converterDeResultSerParaEntidade(resultado);				
				enderecos.add(enderecoConsultado);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos os endereços "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return enderecos;	
	}
	
	
	private EnderecoVO converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		EnderecoVO enderecoConsultado = new EnderecoVO();
		enderecoConsultado.setId(resultado.getInt("id"));
		enderecoConsultado.setCep(resultado.getString("cep"));
		enderecoConsultado.setRua(resultado.getString("rua"));
		enderecoConsultado.setBairro(resultado.getString("bairro"));
		enderecoConsultado.setNumero(resultado.getString("numero"));
		enderecoConsultado.setCidade(resultado.getString("cidade"));
		enderecoConsultado.setEstado(resultado.getString("estado"));
		return enderecoConsultado;
	}
	
	
	
}
