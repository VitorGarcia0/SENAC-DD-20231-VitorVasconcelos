package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class MenuTelefonia extends JPanel {

	private JFrame frmSistemaDeTelefonia;
	private PainelListagemCliente painelListagemCliente;
	private PainelCadastroCliente painelCadastroCliente;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuTelefonia window = new MenuTelefonia();
					// Comando para iniciar a tela maximizada
					// window.frmSistemaDeTelefonia.setExtendedState(JFrame.MAXIMIZED_BOTH);
					window.frmSistemaDeTelefonia.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuTelefonia() {
		initialize();
	}

	private void initialize() {
		frmSistemaDeTelefonia = new JFrame();
		frmSistemaDeTelefonia.setTitle("Sistema de Telefonia");
		frmSistemaDeTelefonia.setBounds(100, 100, 522, 471);
		frmSistemaDeTelefonia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeTelefonia.setJMenuBar(menuBar);

		JMenu mnCliente = new JMenu("Cliente");
		mnCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/IconeCliente.png")));
		menuBar.add(mnCliente);

		JMenuItem mntmCadastroCliente = new JMenuItem("Cadastro");
		mntmCadastroCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroCliente = new PainelCadastroCliente(null);
				painelCadastroCliente.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroCliente();

				// Atuliza a tela principal
				frmSistemaDeTelefonia.setContentPane(painelCadastroCliente);
				frmSistemaDeTelefonia.pack();
			}
		});
		mntmCadastroCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mntmCadastroCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/IconeCliente.png")));
		mnCliente.add(mntmCadastroCliente);

		JMenuItem mntmListarCliente = new JMenuItem("Listagem");
		mntmListarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				painelListagemCliente = new PainelListagemCliente();
				painelListagemCliente.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemCliente();

				frmSistemaDeTelefonia.setContentPane(painelListagemCliente);
				// Atualiza a tela principal
				frmSistemaDeTelefonia.revalidate();
			}
		});
		mntmListarCliente.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/IconeListagemClientes.png")));
		mnCliente.add(mntmListarCliente);

		JMenu mnTelefone = new JMenu("Telefone");
		mnTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/IconeTelefone01.png")));
		menuBar.add(mnTelefone);

		JMenuItem mntmCadastroTelefone = new JMenuItem("Cadastro");
		mntmCadastroTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/TelefoneAdicionar.png")));
		mnTelefone.add(mntmCadastroTelefone);

		JMenuItem mntmListagemTelefone = new JMenuItem("Listagem");
		mntmListagemTelefone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Evento de clique no botão -> instancia o painel e troca
				PainelListagemTelefone painelListagemTelefone = new PainelListagemTelefone();
				frmSistemaDeTelefonia.setContentPane(painelListagemTelefone);
				// Atualiza a tela principal
				frmSistemaDeTelefonia.revalidate();
			}
		});
		mntmListagemTelefone.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/IconeTelefone.png")));
		mnTelefone.add(mntmListagemTelefone);

		JMenu mnEndereco = new JMenu("Endereço");
		mnEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/EnderecoIcone.png")));
		menuBar.add(mnEndereco);

		JMenuItem mntmCadastroEndereco = new JMenuItem("Cadastro");
		mntmCadastroEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/EnderecoCadastrar.gif")));
		mnEndereco.add(mntmCadastroEndereco);

		JMenuItem mntmListagemEndereco = new JMenuItem("Listagem");
		mntmListagemEndereco.setIcon(new ImageIcon(MenuTelefonia.class.getResource("/icones/EnderecoListagem.png")));
		mnEndereco.add(mntmListagemEndereco);

		JMenu mnSobre = new JMenu("Sobre");
		mnSobre.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				// TODO chamar TelaSobreAutor
				JOptionPane.showMessageDialog(null, "Olá");
			}
		});
		// mnSobre.setIcon(new
		// ImageIcon(MenuTelefonia.class.getResource("/icones/icons8-sobre-48.png")));
		menuBar.add(mnSobre);
	}

	protected void registrarCliqueBotaoEditarDoPainelListagemCliente() {
		// Registro de ouvinte para o clique em um botão de um painel
		painelListagemCliente.getBtnEditar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				painelCadastroCliente = new PainelCadastroCliente(painelListagemCliente.getClienteSelecionado());
				painelCadastroCliente.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelCadastroCliente();

				// Atualiza a tela principal
				frmSistemaDeTelefonia.setContentPane(painelCadastroCliente);
				frmSistemaDeTelefonia.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelCadastroCliente() {
		if (painelCadastroCliente == null) {
			painelCadastroCliente = new PainelCadastroCliente(null);
		}

		// Registrar o evento de clique no voltar do PainelCadastroCliente
		painelCadastroCliente.getBtnVoltar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra o painel de listagem de clientes
				painelListagemCliente = new PainelListagemCliente();
				painelListagemCliente.setVisible(true);
				registrarCliqueBotaoEditarDoPainelListagemCliente();
				frmSistemaDeTelefonia.setContentPane(painelListagemCliente);
				frmSistemaDeTelefonia.revalidate();
			}
		});

	}

}
