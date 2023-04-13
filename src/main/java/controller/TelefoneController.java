package controller;

import java.util.List;

import model.Exception.CampoInvalidoException;
import model.bo.TelefoneBO;
import model.vo.telefonia.TelefoneVO;

public class TelefoneController {
	
	private TelefoneBO bo = new TelefoneBO(); 

	public TelefoneVO inserir(TelefoneVO novoTelefone) {
		// TODO validar o preenchimento dos campos obrigatórios
		return bo.inserir(novoTelefone);
	}

	public boolean atualizar(TelefoneVO telefoneAlterado) {
		// TODO validar o preenchimento dos campos obrigatórios
		return bo.atualizar(telefoneAlterado);
	}

	public boolean excluir(int id) {
		return bo.excluir(id);
	}

	public TelefoneVO consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<TelefoneVO> consultarTodos() {
		return bo.consultarTodos();
	}
	
//	private void validarCamposObrigatorios(TelefoneVO telefone) throws CampoInvalidoException {
//		String mensagemValidacao = "";
//
//		mensagemValidacao += validarString(telefone.getDdd(), "cep");
//		mensagemValidacao += validarString(telefone.getNumero(), "número");
//
//		if (!mensagemValidacao.isEmpty()) {
//			throw new CampoInvalidoException(mensagemValidacao);
//		}
//	}
	
//	private String validarString(String texto, String nomeCampo) {
//		boolean valido = (texto != null) && !texto.trim().isEmpty();
//		if (valido) {
//			return "";
//		} else {
//			return "- " + nomeCampo + "\n";
//		}
//	}
	
	
}
