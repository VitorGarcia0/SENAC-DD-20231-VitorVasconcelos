package view.telefonia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

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
		menuBar.add(mnClientes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cadastro");
		mnClientes.add(mntmNewMenuItem);
		
		}

}
