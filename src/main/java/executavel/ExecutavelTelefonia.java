package executavel;

import java.util.ArrayList;
import java.util.List;

import model.dao.telefonia.ClienteDAO;
import model.dao.telefonia.EnderecoDAO;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		
		
		
		
		EnderecoDAO dbaDeEnderecos = new EnderecoDAO();
		
		List<Endereco> enderecos = dbaDeEnderecos.consultarTodos();
		
		System.out.println("========== Lista de Endereços ==========");
		
		
//		Endereco endereco1 = new Endereco("88000123", "Mauro Ramos",  "10", "Centro", "Florianopolis", "SC");		
//		//nome, cpf, telefone, ativo, endereco (CLIENTES)
//		
//		
//		
//		if(dbaDeEnderecos.excluir(9)) {
//			System.out.println("Endereço foi excluído");
//		}else {
//			System.out.println("Erro ao excluir endereço");
//		}
//		
//	//EnderecoDAO dbaDeEnderecos = new EnderecoDAO();	
//		dbaDeEnderecos.inserir(endereco1);
//		
//		Endereco endereco2 = new Endereco("88004321", "Nereu Ramos",  "20", "Centro", "Florianopolis", "SC");
//		
//		dbaDeEnderecos.inserir(endereco2);
//		
//		if(endereco1.getId() != null) {
//			System.out.println("Novo endereço cadastrado");
//			
//		} else {
//			System.out.println("Erro ao cadastrar endereço");
//			
//		}
		
//		Endereco enderecoQueJaExiste = dbaDeEnderecos.consultarPorId(2);
//		
//		System.out.println(enderecoQueJaExiste);
//		enderecoQueJaExiste.setRua("Rua sem Fim");
//		boolean atualizou = dbaDeEnderecos.atualizar(enderecoQueJaExiste);
//		enderecoQueJaExiste = dbaDeEnderecos.consultarPorId(2);
//		
//		if(atualizou) {
//			System.out.println("Endereço foi atualizado ");
//		} else {
//			System.out.println("Erro ao atualizar endereço");
//		}
//		
//		System.out.println(enderecoQueJaExiste);
		
		
		
		
//		Telefone telefone1 = new Telefone(1, "48", "912345678", true, true);
//		
//		TelefoneDAO novoTelefone = new TelefoneDAO();
//		novoTelefone.inserir(telefone1);
//	
//		if(telefone1.getId() != null) {
//			System.out.println("Novo telefone cadastrado");
//		} else {
//			System.out.println("Erro ao cadastrar telefone");
//			
//		}
		

		//		List<Telefone> telefonesDoSocrates = new ArrayList<Telefone>();
//		
//		Telefone telefone1 = new Telefone(1, "48", "32328888", true, false);
//		System.out.println(telefone1);
//		telefonesDoSocrates.add(telefone1);
//		telefonesDoSocrates.add(new Telefone(2, "48", "98881234", true, true));
	
//		Endereco endereco1 = null;
//		Cliente pele = new Cliente(1, "Edson Arantes", "11122233344", null, true, endereco1);
//		Cliente socrates = new Cliente(2, "Socrates Brasileiro", "33322233344", telefonesDoSocrates, true, null);
		
		
		
//		List<Cliente> clientes = new ArrayList<Cliente>();
//		clientes.add(pele);
//		clientes.add(socrates);
		
//		
//		
//		System.out.println("------------- Clientes da Firma -------------");
//		for(Cliente c: clientes) {
//			// msm coisa do int, percorra a lista e vai armazenar na variavel C
//			System.out.println(c.toString());
//		}
//		
//	/*	for(int i = 0; i < clientes.size(); i++) {
//			Cliente c = clientes.get(i);
//			System.out.println(c.toString()); MESMA COISA DO DE CIMA
//		} */
	
	}
}
