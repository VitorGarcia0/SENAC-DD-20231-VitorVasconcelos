package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.Exception.ClienteComTelefoneException;
import model.Exception.CpfAlteradoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.dao.telefonia.ClienteDAO;
import model.seletor.ClienteSeletor;
import model.vo.telefonia.ClienteVO;

public class ClienteBO {
	/**
	 * Insere um novo cliente, mas faz validações que podem gerar exceções
	 * 
	 * @param novoCliente
	 * @return o novoCliente inserido, com a PK gerada
	 * @throws CpfJaUtilizadoException
	 * @throws EnderecoInvalidoException
	 */

	private ClienteDAO dao = new ClienteDAO();

	public ClienteVO inserir(ClienteVO novoCliente) throws CpfJaUtilizadoException, EnderecoInvalidoException {

		if (dao.cpfJaUtilizado(novoCliente.getCpf())) {
			// CASO CPF JÁ FOI UTILIZADO -- NÃO SALVAR E DEVOLVER EXCEÇÃO
			// NÃO SALVAR -- LANÇAR EXCEÇÃO
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");

		}
		validarEndereco(novoCliente);

		novoCliente = dao.inserir(novoCliente);
		// CASO CPF NÃO UTILIZADO -- SALVAR E DEVOLVER O CLIENTE
		// SALVAR

		return novoCliente;
	}

	public boolean atualizar(ClienteVO clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		ClienteVO clienteOriginal = dao.consultarPorId(clienteAlterado.getId());

		if (clienteAlterado.getCpf() != clienteOriginal.getCpf()) {
			throw new CpfAlteradoException("CPF não pode ser alterado");
		}

		validarEndereco(clienteAlterado);

		return dao.atualizar(clienteAlterado);
	}

	/**
	 * não deixar excluir cliente que possua telefone associado Criar exceção
	 * ClienteComTelefoneException Caso cliente possua telefone(s): lançar
	 * ClienteComTelefoneException Caso contrário: chamar dao.excluir(id)
	 * 
	 * @param id
	 * @return se excluiu ou não o cliente
	 * @throws ClienteComTelefoneException
	 */
	public boolean excluir(int id) throws ClienteComTelefoneException {
		// FAZER EM CASA
		// não deixar excluir cliente que possua telefone associado
		// Criar exceção ClienteComTelefoneException
		ClienteVO clienteBuscado = new ClienteVO();

		if (!clienteBuscado.getTelefones().isEmpty()) {
			throw new ClienteComTelefoneException("O cliente possui telefone(s), não pode excluir ");

		}

		return dao.excluir(id);

	}

	public ClienteVO consultarPorId(int id) {

		return dao.consultarPorId(id);
	}

	public List<ClienteVO> consultarTodos() {

		return dao.consultarTodos();
	}

		private void validarEndereco(ClienteVO cliente) throws EnderecoInvalidoException {
		if (cliente.getEndereco() == null || cliente.getEndereco().getId() == null) {
			throw new EnderecoInvalidoException("Endereço não informado");
		}
	}

	public List<ClienteVO> consultarComFiltros(ClienteSeletor seletor) {
		// TODO validar CPF e as datas informadas

		return dao.consultarComFiltros(seletor);
	}
	
	public int contarTotalRegistrosComFiltros(ClienteSeletor seletor) {
		return dao.contarTotalRegistrosComFiltros(seletor);
	}

}
