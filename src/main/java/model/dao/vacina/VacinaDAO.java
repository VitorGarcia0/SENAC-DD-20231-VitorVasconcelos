package model.dao.vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Banco;
import model.vo.vacina.Vacina;

public class VacinaDAO {
	
	
	
	
	public Vacina inserir(Vacina novaVacina) {
		//CONECTAR AO BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO VACINA (PAIS_ORIGEM, ESTAGIO_PESQUISA, DATA_INICIO_PESQUISA, "
				+ " NOME_RESPONSAVEL) "
				+ " VALUES (?, ?, ?, ?) ";
	
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		
		// EXECUTAR O INSERT
		try {
			query.setString(1, novaVacina.getPaisOrigem());
			query.setString(2, novaVacina.getEstagioPesquisa());
			query.setDate(3, novaVacina.getDataInicioPesquisa());
			query.setString(4, novaVacina.getNomeResponsavel());
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
	public boolean atualizar(Vacina enderecoEditado) { 
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE VACINA "
				+ " SET PAIS_ORIGEM = ?, ESTAGIO_PESQUISA = ?, DATA_INICIO_PESQUISA = ?, "
				+ " NOME_RESPONSAVEL = ? "
				+ " WHERE ID = ?";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
		query.setString(1, enderecoEditado.getPaisOrigem());
		query.setString(2, enderecoEditado.getEstagioPesquisa());
		query.setDate(3, enderecoEditado.getDataInicioPesquisa());
		query.setString(4, enderecoEditado.getNomeResponsavel());
		query.setInt(7, enderecoEditado.getId());			
		
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
	
	public Vacina consultarPorId(int id) {
		Vacina enderecoConsultado = null;
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
	
	public List<Vacina> consultarTodos() {
		Vacina vacinaConsultada = new Vacina();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINA ";
		
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		List<Vacina> vacinas = new ArrayList<Vacina>();
		try {
			ResultSet resultado = query.executeQuery();  //Conjuntos de Resultados
			while(resultado.next()) {		
				vacinaConsultada = converterDeResultSerParaEntidade(resultado);				
				vacinas.add(vacinaConsultada);
			}												
		} catch(SQLException e) {
			System.out.println("Erro ao buscar todos os endere�os "
					+ "\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return vacinas;	
	}
	
	
	private Vacina converterDeResultSerParaEntidade(ResultSet resultado) throws SQLException {
		Vacina vacinaConsultada = new Vacina();
		vacinaConsultada.setId(resultado.getInt("id"));
		vacinaConsultada.setPaisOrigem(resultado.getString("cep"));
		vacinaConsultada.setEstagioPesquisa(resultado.getString("rua"));
		vacinaConsultada.setDataInicioPesquisa(resultado.getDate("dataInicioPesquisa"));
		vacinaConsultada.setNomeResponsavel(resultado.getString("nomeResponsavel"));
		return vacinaConsultada;
	}
}
