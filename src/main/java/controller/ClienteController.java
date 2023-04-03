package controller;

import model.Exception.CampoInvalidoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.bo.ClienteBO;
import model.vo.telefonia.ClienteVO;

public class ClienteController {

	private ClienteBO bo = new ClienteBO();
	
	public ClienteVO inserir(ClienteVO novoCliente) throws CpfJaUtilizadoException, EnderecoInvalidoException, CampoInvalidoException {
		
		validarCamposObrigatorios(novoCliente);
		
		return bo.inserir(novoCliente);
		
	}
	
	
	
	
	private void validarCamposObrigatorios(ClienteVO cliente) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		
		if(cliente.getNome() != null || cliente.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido \n";
		} 
		
		if(cliente.getCpf() != null ) {
			if(cliente.getCpf().length() != 11) {
				System.out.println("Tem q ter 11 digitos");
			}
			try {
				Integer.valueOf(cliente.getCpf());
			} catch (NumberFormatException excep) {
				throw new CampoInvalidoException("CPF deve possuir somente números");
			}
		}
	}
}
