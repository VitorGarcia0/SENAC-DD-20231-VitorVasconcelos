package model.bo;

import java.util.List;

import model.Exception.EnderecoInvalidoException;
import model.dao.telefonia.ClienteDAO;
import model.dao.telefonia.EnderecoDAO;
import model.vo.telefonia.EnderecoVO;

public class EnderecoBO {
	
private EnderecoDAO dao = new EnderecoDAO();
	
	public EnderecoVO inserir(EnderecoVO novoEndereco) {
		return dao.inserir(novoEndereco);
	}
	
	public boolean atualizar(EnderecoVO enderecoAlterado){
		return dao.atualizar(enderecoAlterado);
	}
	
	public boolean excluir(int id) throws EnderecoInvalidoException {
		ClienteDAO clienteDAO = new ClienteDAO();
		
		if(clienteDAO.contarClientesQueResidemNoEndereco(id) > 0) {
			throw new EnderecoInvalidoException("Endereço não pode ser excluído, pois possui cliente(s) associado(s)");
		}
		
		return dao.excluir(id);
	}
	
	public EnderecoVO consultarPorCep(String cep) {
		//TODO chamar viaCep
		// https://gitlab.com/parg/ViaCEP
		return null;
	}
	
	public EnderecoVO consultarPorId(int id) {
		return dao.consultarPorId(id);
	}
	
	public List<EnderecoVO> consultarTodos() {
		return dao.consultarTodos();
	}

}
