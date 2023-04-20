package view.telefonia;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.EnderecoController;
import model.Exception.EnderecoInvalidoException;
import model.vo.telefonia.EnderecoVO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class TelaListagemEnderecos {

	private JFrame frmListagemEnderecos;
	private JTable tblEnderecos;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnBuscarTodos;
	private String[] nomesColunas = { "#", "CEP", "Rua", "Número", "Bairro", "Cidade", "Estado" };

	// LISTA PARA ARMAZENAR OS ENDEREÇOS CONSULTADOS
	private ArrayList<EnderecoVO> enderecos;
	// OBJ USADO PARA ARMAZENAR O ENDEREÇO QUE FOR SELECIONADO NA (TBLENDERECOS);
	private EnderecoVO enderecoSelecionado;
	private EnderecoController enderecoController = new EnderecoController();

	private void limparTabela() {
		tblEnderecos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaEnderecos() {
		this.limparTabela();

		EnderecoController controller = new EnderecoController();
		enderecos = (ArrayList<EnderecoVO>) controller.consultarTodos();

		DefaultTableModel model = (DefaultTableModel) tblEnderecos.getModel();
		// Preenche os valores na tabela linha a linha
		for (EnderecoVO e : enderecos) {
			Object[] novaLinhaDaTabela = new Object[7];

			novaLinhaDaTabela[0] = e.getId();
			novaLinhaDaTabela[1] = e.getCep();
			novaLinhaDaTabela[2] = e.getRua();
			novaLinhaDaTabela[3] = e.getNumero();
			novaLinhaDaTabela[4] = e.getBairro();
			novaLinhaDaTabela[5] = e.getCidade();
			novaLinhaDaTabela[6] = e.getEstado();

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
					TelaListagemEnderecos window = new TelaListagemEnderecos();
					window.frmListagemEnderecos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaListagemEnderecos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListagemEnderecos = new JFrame();
		frmListagemEnderecos.setTitle("Listagem de Endereços");
		frmListagemEnderecos.setBounds(100, 100, 500, 400);
		frmListagemEnderecos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListagemEnderecos.getContentPane().setLayout(null);

		tblEnderecos = new JTable();
		tblEnderecos.setBounds(25, 50, 430, 250);
		frmListagemEnderecos.getContentPane().add(tblEnderecos);

		btnBuscarTodos = new JButton("Buscar Todos");
		btnBuscarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnBuscarTodos.setBounds(175, 10, 100, 30);
		frmListagemEnderecos.getContentPane().add(btnBuscarTodos);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mostra a TelaCadastroEndereco, passando o enderecoSelecionado como parâmetro
				TelaCadastroEndereco telaEdicaoEndereco = new TelaCadastroEndereco(enderecoSelecionado);
			}
		});
		btnEditar.setBounds(120, 315, 100, 30);
		frmListagemEnderecos.getContentPane().add(btnEditar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int opcaoSelecionada = JOptionPane.showConfirmDialog(null,
						"Confirma a exclusão do endereço selecionado?");

				if (opcaoSelecionada == JOptionPane.YES_OPTION) {
					try {
						enderecoController.excluir(enderecoSelecionado.getId());
						JOptionPane.showMessageDialog(null, "Endereço excluído!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);

						atualizarTabelaEnderecos();
					} catch (EnderecoInvalidoException excecao) {
						JOptionPane.showMessageDialog(null, excecao.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		frmListagemEnderecos.getContentPane().add(btnExcluir);
		frmListagemEnderecos.getContentPane().add(btnEditar);
		// Botões iniciam bloqueados
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnExcluir.setBounds(250, 315, 100, 30);
		frmListagemEnderecos.getContentPane().add(btnExcluir);

		tblEnderecos = new JTable();
		this.limparTabela();
		tblEnderecos.setBounds(15, 70, 655, 350);

		// Evento de clique em uma linha da tabela
		// Habilita/desabilita os botões "Editar" e "Excluir"
		tblEnderecos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblEnderecos.getSelectedRow();

				if (indiceSelecionado > 0) {
					// Primeira linha da tabela contém o cabeçalho, por isso o '-1'
					enderecoSelecionado = enderecos.get(indiceSelecionado - 1);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					btnEditar.setEnabled(false);
					btnExcluir.setEnabled(false);
				}
			}
		});
		frmListagemEnderecos.getContentPane().add(tblEnderecos);

	}
}
