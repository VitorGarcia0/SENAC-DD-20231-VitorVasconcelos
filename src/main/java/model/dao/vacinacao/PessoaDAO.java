package model.dao.vacinacao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.vo.vacina.PessoaVO;
import model.vo.vacina.TipoPessoaVO;

public class PessoaDAO {
	
	public PessoaVO inserir(PessoaVO novaPessoa) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO Pessoa (NOME, DT_NASCIMENTO, SEXO, "
				+ " CPF, TIPO_PESSOA) "
				+ " VALUES (?, ?, ?, ?, ? ) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			query.setString(1, novaPessoa.getNome());
			query.setDate(2, Date.valueOf(novaPessoa.getDt_Nascimento()));
			query.setString(3, novaPessoa.getSexo());
			query.setString(4, novaPessoa.getCpf());
			query.setObject(5, novaPessoa.getTipoPessoa());
			
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
	public boolean atualizar(PessoaVO pessoaEditada) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PESSOA "
				+ " SET NOME = ?, DT_NASCIMENTO = ?, SEXO = ?, "
				+ " CPF = ?, TIPO_PESSOA = ? "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setString(1, pessoaEditada.getNome());
		query.setDate(2, Date.valueOf(pessoaEditada.getDt_Nascimento()));
		query.setString(3, pessoaEditada.getSexo());
		query.setString(4, pessoaEditada.getCpf());
		query.setObject(5, pessoaEditada.getTipoPessoa());
		
		query.setInt(6, pessoaEditada.getId());			
		
		int quantidadeLinhasAtualizadas = query.executeUpdate();
		
		atualizou = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar pessoa "
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
	
	public PessoaVO consultarPorId(int id) {
		PessoaVO pessoaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			if(resultado.next()) {
				pessoaConsultada = converterDeResultSerParaEntidade(resultado);
			}		
			
		} catch(SQLException e) {
			System.out.println("Erro ao buscar pessoa pelo id: " + id
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return pessoaConsultada;
	}
	
	public List<PessoaVO> consultarTodos() {
		PessoaVO pessoaConsultada = new PessoaVO();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESSOA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<PessoaVO> pessoas = new ArrayList<PessoaVO>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				pessoaConsultada = converterDeResultSerParaEntidade(resultado);				
				pessoas.add(pessoaConsultada);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todas os pessoas "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return pessoas;	
	}
	
	
	private PessoaVO converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		PessoaVO pessoaConsultada = new PessoaVO();
		pessoaConsultada.setId(resultado.getInt("id"));
		pessoaConsultada.setNome(resultado.getString("cep"));
		pessoaConsultada.setDt_Nascimento(LocalDate.parse(resultado.getString("dt_Nascimento")));
		pessoaConsultada.setSexo(resultado.getString("sexo"));
		pessoaConsultada.setCpf(resultado.getString("cpf"));
		pessoaConsultada.setTipoPessoa(TipoPessoaVO.valueOf(resultado.getString("tipoPessoa")));	
		
		return pessoaConsultada;
	}

}
