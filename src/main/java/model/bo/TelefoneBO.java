package model.bo;

import java.util.List;

import model.Exception.TelefoneJaUtilizadoException;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.TelefoneVO;

public class TelefoneBO {
	
	private TelefoneDAO dao = new TelefoneDAO();

	public TelefoneVO inserir(TelefoneVO novoTelefone) throws TelefoneJaUtilizadoException {
		novoTelefone.setAtivo(novoTelefone.getIdCliente() != null);
		
		if(dao.telefoneJaCadastrado(novoTelefone.getDdd(), novoTelefone.getNumero())) {
			throw new TelefoneJaUtilizadoException("Telefone informado já existe");
		}
		return dao.inserir(novoTelefone);
	}

	public boolean atualizar(TelefoneVO telefoneAlterado) {
		telefoneAlterado.setAtivo(telefoneAlterado.getIdCliente() != null);

		return dao.atualizar(telefoneAlterado);
	}

	public boolean excluir(int id) {
		return dao.excluir(id);
	}

	public TelefoneVO consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<TelefoneVO> consultarTodos() {
		return dao.consultarTodos();
	}
}
