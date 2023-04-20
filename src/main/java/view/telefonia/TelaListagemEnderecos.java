	package view.telefonia;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;

import model.vo.telefonia.EnderecoVO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListagemEnderecos {

	private JFrame frmListagemEnderecos;
	private JTable tblEnderecos;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnBuscarTodos;
	private String[] nomesColunas = { "#", "CEP", "Rua", "Número", "Bairro", "Cidade", "Estado" };
	
	//LISTA PARA ARMAZENAR OS ENDEREÇOS CONSULTADOS
	private ArrayList<EnderecoVO> enderecos;
	// OBJ USADO PARA ARMAZENAR O ENDEREÇO QUE FOR SELECIONADO NA (TBLENDERECOS);
	private EnderecoVO enderecoSelecionado;

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
				
			}
		});
		btnEditar.setBounds(120, 315, 100, 30);
		frmListagemEnderecos.getContentPane().add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExcluir.setBounds(250, 315, 100, 30);
		frmListagemEnderecos.getContentPane().add(btnExcluir);
		
		
	}
}
