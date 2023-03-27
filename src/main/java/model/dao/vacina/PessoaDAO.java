package model.dao.vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.vo.vacina.Pessoa;

public class PessoaDAO {
	
	
	public Pessoa inserir(Pessoa novaPessoa) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO Pessoa (NOME, DT_NASCIMENTO, SEXO, "
				+ " CPF) "
				+ " VALUES (?, ?, ?, ? ) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			query.setString(1, novaPessoa.getNome());
			query.setObject(2, novaPessoa.getDt_Nascimento());
			query.setString(3, novaPessoa.getSexo());
			query.setString(4, novaPessoa.getCpf());
			
			query.execute();
			
			// Prencher o id gerado no banco no objeto 
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir pessoa. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);	
		}
		
		return novaPessoa;
	}
	//UPDATE ENDERECO
	//SET CEP = ?, RUA = ?, NUMERO = ?, BAIRRO = ?, 
	//CIDADE = ?, ESTADO = ?
	// WHERE ID = ?
	public boolean atualizar(Pessoa pessoaEditada) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PESSOA "
				+ " SET NOME = ?, DT_NASCIMENTO = ?, SEXO = ?, "
				+ " CPF = ?, "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setString(1, pessoaEditada.getNome());
		query.setObject(2, pessoaEditada.getDt_Nascimento());
		query.setString(3, pessoaEditada.getSexo());
		query.setString(4, pessoaEditada.getCpf());
		query.setInt(5, pessoaEditada.getId());			
		
		int quantidadeLinhasAtualizadas = query.executeUpdate();
		
		atualizou = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar pessoa"
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
		String sql = " DELETE FROM PESSOA "
				+ " WHERE ID = ?";
			
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao apagar a pesoa "
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return excluiu;
	}
	
	public Pessoa consultarPorId(int id) {
		Pessoa pessoaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			if(resultado.next()) {
				pessoaConsultada = converterDeResultSerParaEntidade(resultado);
			}		
			
		} catch(SQLException e) {
			System.out.println("Erro ao buscar pessoa com id: " + id
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return pessoaConsultada;
	}
	
	public List<Pessoa> consultarTodos() {
		Pessoa pessoaConsultada = new Pessoa();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				pessoaConsultada = converterDeResultSerParaEntidade(resultado);				
				pessoas.add(pessoaConsultada);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos os pessoas "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pessoas;	
	}
	
	
	private Pessoa converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		Pessoa pessoaConsultada = new Pessoa();
		pessoaConsultada.setId(resultado.getInt("id"));
		pessoaConsultada.setNome(resultado.getString("cep"));
		//pessoaConsultada.setDt_Nascimento(resultado.getObject("dt_nascimento"));
		pessoaConsultada.setSexo(resultado.getString("sexo"));
		pessoaConsultada.setCpf(resultado.getString("cpf"));
		return pessoaConsultada;
	}

}
