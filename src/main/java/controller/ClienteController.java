package controller;

import java.util.List;

import model.Exception.CampoInvalidoException;
import model.Exception.ClienteComTelefoneException;
import model.Exception.CpfAlteradoException;
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
		
		mensagemValidacao += validarCpf(cliente);

		if(cliente.getEndereco() == null) {
			mensagemValidacao += "Informe um endereço: \n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
	
	private String validarCpf(ClienteVO c) throws CampoInvalidoException {
		String validacao = "";
		
		if(c.getCpf() == null) {
			validacao += "Informe um CPF \n" ;
		}else {
			String cpfSemMascara = c.getCpf().replace(".", "");
			cpfSemMascara = c.getCpf().replace("-", "");
			c.setCpf(cpfSemMascara);
			if(c.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 dígitos\n" ;	
			}
			
			try {
				Double.valueOf(c.getCpf());
			} catch (NumberFormatException ex) {
				
				//TODO conferir
				validacao += "CPF deve possuir somente números\n";
			}
		}
		
		return validacao;
	}

	public List<ClienteVO> consultarTodos() {
		return bo.consultarTodos();
	}
	
	public ClienteVO consultarPorId(int id) {
		return bo.consultarPorId(id);
	}
	
	public boolean atualizar(ClienteVO clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		return bo.atualizar(clienteAlterado);
	}
	
	public boolean excluir(int id) throws ClienteComTelefoneException {
		return bo.excluir(id);
	}
	
	
}
