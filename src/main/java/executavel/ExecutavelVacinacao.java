package executavel;

import java.time.LocalDate;

import model.dao.telefonia.EnderecoDAO;
import model.dao.vacinacao.PessoaDAO;
import model.dao.vacinacao.VacinaDAO;
import model.vo.vacina.PessoaVO;
import model.vo.vacina.TipoPessoaVO;
import model.vo.vacina.VacinaVO;

public class ExecutavelVacinacao {

	public static void main(String[] args) {
			
		PessoaDAO pessoaDAO = new PessoaDAO();
		LocalDate data = LocalDate.of(2010, 3, 27);
		PessoaVO pessoaVO = new PessoaVO(1, "Vitor", data, "Masculino", "11122233344", 2);
		pessoaDAO.inserir(pessoaVO);
		
		if(pessoaVO.getId() != 0) {
			System.out.println("Nova pessoa cadastrada");		
		} else {
			System.out.println("Erro ao cadastrar pessoa");
			
		}
		System.out.println(pessoaVO);
		
		
		VacinaDAO vacinaDAO = new VacinaDAO();
		LocalDate dataPesquisa = LocalDate.of(2020, 01, 25);
		VacinaVO vacinaVO = new VacinaVO();
		//vacinaVO(1, "Brasil", 1, dataPesquisa, vacinaVO.setResponsavelPesquisa(pessoaVO.getNome()));
		
		
		// EXCLUIR
		PessoaDAO dbaDePessoa = new PessoaDAO();
		if(dbaDePessoa.excluir(2)) {
		System.out.println("Pessoa foi excluida");
		}else {
			System.out.println("Erro ao excluir pessoa");
		}
		
		VacinaDAO dbaDeVacinaDAO = new VacinaDAO();
		if(dbaDeVacinaDAO.excluir(3)) {
			System.out.println("Vacina foi excluida");
		}else {
			System.out.println("Erro ao excluir a vacina");
		}
		
	
		
		
		
		
		
	
		
		
		
		
		
	}

}
