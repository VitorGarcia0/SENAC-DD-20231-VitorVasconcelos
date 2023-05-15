package view.telefonia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class TelaDeMenu {

	private JFrame frmPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeMenu window = new TelaDeMenu();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaDeMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Menu Principal");
		frmPrincipal.setBounds(100, 100, 602, 467);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/IconeCliente.png")));
		menuBar.add(mnClientes);
		
		JMenuItem mntmClienteCadastro = new JMenuItem("Cadastro");
		mntmClienteCadastro.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/IconeListagemClientes.png")));
		mnClientes.add(mntmClienteCadastro);
		
		JMenuItem mntmListagemCliente = new JMenuItem("Listagem");
		mntmListagemCliente.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/IconeListagemClientes.png")));
		mnClientes.add(mntmListagemCliente);
		
		JMenu mnTelefone = new JMenu("Telefone");
		mnTelefone.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/IconeTelefone01.png")));
		menuBar.add(mnTelefone);
		
		JMenuItem mntmTelefoneCadastro = new JMenuItem("Tel Cadastro");
		mntmTelefoneCadastro.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/TelefoneAdicionar.png")));
		mnTelefone.add(mntmTelefoneCadastro);
		
		JMenuItem mntmListagemTelefone = new JMenuItem("Listagem");
		mntmListagemTelefone.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/IconeTelefone.png")));
		mnTelefone.add(mntmListagemTelefone);
		
		JMenu mnEndereco = new JMenu("Endereço");
		mnEndereco.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/EnderecoIcone.png")));
		menuBar.add(mnEndereco);
		
		JMenuItem mntmEnderecoCadastro = new JMenuItem("Cadastro");
		mntmEnderecoCadastro.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/icons8-endereço.gif")));
		mnEndereco.add(mntmEnderecoCadastro);
		
		JMenuItem mntmEnderecoListagem = new JMenuItem("Listagem");
		mntmEnderecoListagem.setIcon(new ImageIcon(TelaDeMenu.class.getResource("/icones/EnderecoListagem.png")));
		mnEndereco.add(mntmEnderecoListagem);
		
		}

}
