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
import model.vo.vacina.PessoaDAO;
import model.vo.vacina.PessoaDAO;
import model.vo.vacina.VacinaVO;

public class VacinaDAO {
	
	
	public VacinaVO inserir(VacinaVO novaVacina) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO VACINA (PAIS_ORIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA, "
				+ " NOME_RESPONSAVEL) "
				+ " VALUES (?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			query.setString(1, novaVacina.getPaisOrigem());
			query.setInt(2, novaVacina.getEstagioPesquisa());
			query.setDate(3, Date.valueOf(novaVacina.getDataInicioPesquisa()));
			query.setInt(4, novaVacina.getIdPessoa());
			query.execute();
			
			// Prencher o id gerado no banco no objeto 
			ResultSet resultado = query.getGeneratedKeys();
			if(resultado.next()) {
				novaVacina.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir vacina. "
					+ "\nCausa " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);	
		}
		
		return novaVacina;
	}
	//UPDATE ENDERECO
	//SET CEP = ?, RUA = ?, NUMERO = ?, BAIRRO = ?, 
	//CIDADE = ?, ESTADO = ?
	// WHERE ID = ?
	public boolean atualizar(VacinaVO vacinaEditada) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE VACINA "
				+ " SET PAIS_ORIGEM = ?, ESTAGIO_PESQUISA = ?, DATA_INICIO_PESQUISA = ?, "
				+ " NOME_RESPONSAVEL = ? "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setString(1, vacinaEditada.getPaisOrigem());
		query.setInt(2, vacinaEditada.getEstagioPesquisa());
		query.setDate(3, Date.valueOf(vacinaEditada.getDataInicioPesquisa()));
		query.setInt(4, vacinaEditada.getIdPessoa());
		query.setInt(5, vacinaEditada.getId());			
		
		int quantidadeLinhasAtualizadas = query.executeUpdate();
		
		atualizou = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar Vacina"
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
		String sql = " DELETE FROM VACINA "
				+ " WHERE ID = ?";
			
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;
		
		} catch (SQLException excecao) {
			System.out.println("Erro ao apagar o Vacina "
					+ "\nCausa " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
			
		}
		return excluiu;
	}
	
	public VacinaVO consultarPorId(int id) {
		VacinaVO enderecoConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			if(resultado.next()) {
				enderecoConsultado = converterDeResultSerParaEntidade(resultado);
			}		
			
		} catch(SQLException e) {
			System.out.println("Erro ao buscar vacina com id: " + id
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return enderecoConsultado;
	}
	
	public List<VacinaVO> consultarTodos() {
		VacinaVO vacinaConsultada = new VacinaVO();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<VacinaVO> vacinas = new ArrayList<VacinaVO>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				vacinaConsultada = converterDeResultSerParaEntidade(resultado);				
				vacinas.add(vacinaConsultada);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todas as vacinas "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinas;	
	}
	
	
	private VacinaVO converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		VacinaVO vacinaConsultada = new VacinaVO();
		PessoaDAO pessoaConsultada = new PessoaDAO();
		vacinaConsultada.setId(resultado.getInt("id"));
		vacinaConsultada.setPaisOrigem(resultado.getString("cep"));
		vacinaConsultada.setEstagioPesquisa(resultado.getInt("rua"));
		vacinaConsultada.setDataInicioPesquisa(LocalDate.parse(resultado.getString("dataInicioPesquisa")));
		//vacinaConsultada(resultado.getInt("idPessoa"));
		vacinaConsultada.getResponsavelPesquisa().getId();
		
		//pessoaConsultada.consultarPorId(0))
		//pessoaConsultada
		//VOU PEGAR O ID DA PESSOA E VOU JOGAR PRA PESSOAdao PARA ME RETORNAR O NOME
		
		return vacinaConsultada;
	}
}
