package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.TelefoneController;
import model.vo.telefonia.TelefoneVO;

import javax.swing.JCheckBox;
import javax.swing.JButton;

public class TelaCadastroTelefone {

	private JFrame frmCadastroDeTelefone;
	private JTextField txtDDD;
	private JTextField txtNumero;
	private JCheckBox chckBxMovel;
	private JLabel movel;
	private JLabel numero;
	private JLabel DDD;
	private JButton btnSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroTelefone window = new TelaCadastroTelefone();
					window.frmCadastroDeTelefone.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroTelefone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeTelefone = new JFrame();
		frmCadastroDeTelefone.setBounds(100, 100, 450, 300);
		frmCadastroDeTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeTelefone.getContentPane().setLayout(null);

		DDD = new JLabel("DDD:");
		DDD.setBounds(20, 20, 46, 14);
		frmCadastroDeTelefone.getContentPane().add(DDD);

		numero = new JLabel("Número:");
		numero.setBounds(20, 60, 46, 14);
		frmCadastroDeTelefone.getContentPane().add(numero);

		movel = new JLabel("Móvel:");
		movel.setBounds(20, 100, 46, 14);
		frmCadastroDeTelefone.getContentPane().add(movel);

		txtDDD = new JTextField();
		txtDDD.setBounds(75, 20, 128, 20);
		frmCadastroDeTelefone.getContentPane().add(txtDDD);
		txtDDD.setColumns(10);

		txtNumero = new JTextField();
		txtNumero.setBounds(75, 60, 128, 20);
		frmCadastroDeTelefone.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		chckBxMovel = new JCheckBox("Sim/Não");
		chckBxMovel.setBounds(72, 96, 131, 23);
		frmCadastroDeTelefone.getContentPane().add(chckBxMovel);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(170, 140, 100, 30);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelefoneVO novoTelefone = new TelefoneVO();
				novoTelefone.setDdd(txtDDD.getText());
				novoTelefone.setNumero(txtNumero.getText());
				novoTelefone.setMovel(chckBxMovel.isSelected());
				novoTelefone.setAtivo(true);

				TelefoneController telController = new TelefoneController();
				telController.inserir(novoTelefone);
			}

		});
		frmCadastroDeTelefone.getContentPane().add(btnSalvar);
	}
}
