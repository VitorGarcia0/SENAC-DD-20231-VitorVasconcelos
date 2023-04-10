package controller;

import java.util.List;

import model.Exception.CampoInvalidoException;
import model.Exception.EnderecoInvalidoException;
import model.bo.EnderecoBO;
import model.vo.telefonia.EnderecoVO;

public class EnderecoController {

	private EnderecoBO bo = new EnderecoBO();

	public EnderecoVO inserir(EnderecoVO novoEndereco) throws CampoInvalidoException {
		this.validarCamposObrigatorios(novoEndereco);
		// NÃO PODE SER NULL OU SÓ ESPAÇO

		return bo.inserir(novoEndereco);
	}

	private void validarCamposObrigatorios(EnderecoVO endereco) throws CampoInvalidoException {
		String mensagemValidacao = "";

		mensagemValidacao += validarString(endereco.getCep(), "cep");
		mensagemValidacao += validarString(endereco.getRua(), "rua");
		mensagemValidacao += validarString(endereco.getNumero(), "número");
		mensagemValidacao += validarString(endereco.getBairro(), "bairro");
		mensagemValidacao += validarString(endereco.getCidade(), "cidade");
		mensagemValidacao += validarString(endereco.getEstado(), "estado");

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();
		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}

	}

	public boolean atualizar(EnderecoVO enderecoAlterado) throws CampoInvalidoException {
		validarCamposObrigatorios(enderecoAlterado);
		return bo.atualizar(enderecoAlterado);
	}

	public boolean excluir(int id) throws EnderecoInvalidoException {
		return bo.excluir(id);
	}

	public EnderecoVO consultarPorCep(String cep) {
		return bo.consultarPorCep(cep);
	}

	public EnderecoVO consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<EnderecoVO> consultarTodos() {
		return bo.consultarTodos();
	}

}
