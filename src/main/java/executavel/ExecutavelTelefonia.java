package executavel;

import model.dao.telefonia.EnderecoDAO;
import model.vo.telefonia.Endereco;

public class ExecutavelTelefonia {

	public static void main(String[] args) {
		
		Endereco endereco1 = new Endereco("Mauro Ramos", "88000123", "10", "Centro", "Florianopolis", "SC");		
		//nome, cpf, telefone, ativo, endereco (CLIENTES)
		
		EnderecoDAO novoEndereco = new EnderecoDAO();	
		novoEndereco.inserir(endereco1);
		
		if(endereco1.getId() != null) {
			System.out.println("Novo endereço cadastrado");
			
		} else {
			System.out.println("Erro ao cadastrar endereço");
			
		}
//		Telefone telefone1 = new Telefone("48", "912345678", true, true);
//		
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
//		Telefone telefone1 = new Telefone(1, 1, "48", "32328888", true, false);
//		telefonesDoSocrates.add(telefone1);
//		telefonesDoSocrates.add(new Telefone(2, 2, "48", "98881234", true, true));
//		
//		Cliente pele = new Cliente(1, "Edson Arantes", "11122233344", null, true, endereco1);
//		Cliente socrates = new Cliente(2, "Socrates Brasileiro", "33322233344", telefonesDoSocrates, true, null);
//		
//		
//		
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
