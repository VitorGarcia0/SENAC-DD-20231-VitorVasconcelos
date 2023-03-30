package model.bo;

import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.dao.telefonia.ClienteDAO;
import model.vo.telefonia.ClienteVO;

public class ClienteBO {
	
	private ClienteDAO dao = new ClienteDAO();
	
	public ClienteVO inserir(ClienteVO novoCliente) throws CpfJaUtilizadoException,
			EnderecoInvalidoException {
	
		if(dao.cpfJaUtilizado(novoCliente.getCpf())) {
			//CASO CPF JÁ FOI UTILIZADO -- NÃO SALVAR E DEVOLVER EXCEÇÃO
			// NÃO SALVAR -- LANÇAR EXCEÇÃO
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
			
		}
		if(novoCliente.getEndereco() == null || novoCliente.getEndereco().getId() == null) {
			throw new EnderecoInvalidoException("Endereço não informado");
		}
		
			
		
		novoCliente = dao.inserir(novoCliente);
			//CASO CPF NÃO UTILIZADO --  SALVAR E DEVOLVER O CLIENTE
			//SALVAR
		
		
		return novoCliente;
	}
	
	
	public boolean excluir(int id) {
		// FAZER EM CASA
		
		
		return false;
	
	}
}	
