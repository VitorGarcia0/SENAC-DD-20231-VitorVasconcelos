package controller;

import model.Exception.CampoInvalidoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.bo.ClienteBO;
import model.vo.telefonia.ClienteVO;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();

	public ClienteVO inserir(ClienteVO novoCliente)
			throws CpfJaUtilizadoException, EnderecoInvalidoException, CampoInvalidoException {

		this.validarCamposObrigatorios(novoCliente);

		return bo.inserir(novoCliente);

	}
	
	

	private void validarCamposObrigatorios(ClienteVO cliente) throws CampoInvalidoException {
		String mensagemValidacao = "";

		if (cliente.getNome() == null || cliente.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido \n";
		}
		
		mensagemValidacao += validarCPF(cliente);

		if(cliente.getEndereco() == null) {
			mensagemValidacao += "Informe um endereço: \n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}



	private String validarCPF(ClienteVO cliente) {
		// TODO Auto-generated method stub
		return null;
	}
}
