package view.telefonia;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ClienteController;
import controller.EnderecoController;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaListagemClientes {
	// Começar uma Tela de Listagem, construir a tela no modo visual, fazer os
	// metódos de LimparTela e Atualizar Tela;

	private JFrame frmListagemClientes;
	private JTable tblClientes;
	private String[] nomesColunas = { "Nome", "CPF", "Endereço", "Total de Telefones", "Ativo?" };

	// LISTAR OS CLIENTES PARA PESQUISAR
	private ArrayList<ClienteVO> clientes;
	// PEGAR O CLIENTE SELECIONADO E ARMAZENAR
	private ClienteVO clienteSelecionado;
	private ClienteController clienteController = new ClienteController();
	private JButton btnBuscarTodos;
	private JButton btnEditar;
	private JButton btnExcluir;

	private void limparTabelaClientes() {
		tblClientes.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
		tblClientes = new JTable(tblClientes.getModel()) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		

	}

	// CHAMAR SEMPRE NO BUSCAR TODOS OS CLIENTES
	private void atualizarTabelaClientes() {
		this.limparTabelaClientes();

		clienteController = new ClienteController();
		clientes = (ArrayList<ClienteVO>) clienteController.consultarTodos();

		DefaultTableModel model = (DefaultTableModel) tblClientes.getModel();
		// Preenche os valores na tabela linha a linha
		for (ClienteVO c : clientes) {
			Object[] novaLinhaDaTabela = new Object[4];

			novaLinhaDaTabela[0] = c.getNome();
			novaLinhaDaTabela[1] = c.getCpf();
			novaLinhaDaTabela[2] = c.getEndereco().toString();
			novaLinhaDaTabela[3] = c.getTelefones().size();
			novaLinhaDaTabela[4] = c.isAtivo() ? "Sim" : "Não";

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagemClientes window = new TelaListagemClientes();
					window.frmListagemClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemClientes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemClientes = new JFrame();
		frmListagemClientes.setTitle("Listagem de Clientes ");
		frmListagemClientes.setBounds(100, 100, 500, 400);
		frmListagemClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemClientes.getContentPane().setLayout(null);

		tblClientes = new JTable();
		this.limparTabelaClientes();
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblClientes.getSelectedRow();
				
				if(indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
				} else {
					btnEditar.setEnabled(false);
				}
				
			}
		});

		tblClientes.setBounds(20, 80, 444, 220);
		frmListagemClientes.getContentPane().add(tblClientes);

		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarTabelaClientes();
			}
		});
		btnBuscarTodos.setBounds(175, 25, 100, 35);
		frmListagemClientes.getContentPane().add(btnBuscarTodos);

		btnEditar = new JButton("Editar");
		btnEditar.setBounds(129, 311, 90, 30);
		frmListagemClientes.getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(250, 311, 90, 30);
		frmListagemClientes.getContentPane().add(btnExcluir);

	}
}
