package controller;

import model.Exception.CampoInvalidoException;
import model.vo.telefonia.EnderecoVO;

public class EnderecoController {
	
	
public EnderecoVO inserir(EnderecoVO novoEndereco) throws CampoInvalidoException  {
		validarCamposObrigatorios(novoEndereco);
		// NÃO PODE SER NULL OU SÓ ESPAÇO
		
		return (novoEndereco); //bo.inserir(novoEndereco)
		
	}
	
	
	private void validarCamposObrigatorios(EnderecoVO endereco) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		
		mensagemValidacao += validarString(endereco.getCep(), + "cep");
		mensagemValidacao += validarString(endereco.getRua(), + "rua");
		mensagemValidacao += validarString(endereco.getNumero(), + "número");
		mensagemValidacao += validarString(endereco.getRua(), + "rua");
		mensagemValidacao += validarString(endereco.getRua(), + "rua");
		mensagemValidacao += validarString(endereco.getRua(), + "rua");
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}
	
	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();
		if(valido) {
			return  "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}
	
	

}
