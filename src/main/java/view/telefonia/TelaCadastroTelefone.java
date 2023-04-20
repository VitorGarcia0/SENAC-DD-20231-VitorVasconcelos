package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.TelefoneController;
import model.Exception.TelefoneJaUtilizadoException;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.TelefoneVO;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JRadioButton;
import java.awt.Color;

public class TelaCadastroTelefone {

	private JFrame frmCadastroDeTelefone;
	private JLabel lblNumero;
	private JLabel lblTipo;
	private JButton btnSalvar;
	private JLabel lblCliente;
	private JComboBox cbCliente;
	private JRadioButton rbFixo;
	private JRadioButton rbMovel;

	private MaskFormatter mascaraTelefoneFixo;
	private MaskFormatter mascaraTelefoneMovel;
	private JFormattedTextField txtTelefoneFixo;
	private JFormattedTextField txtTelefoneMovel;

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
	public TelaCadastroTelefone() throws ParseException {
		initialize();
		esconderTodosOsComponentes();
	}

	private void esconderTodosOsComponentes() {
		lblNumero.setVisible(false);
		txtTelefoneFixo.setVisible(false);
		lblCliente.setVisible(false);
		cbCliente.setVisible(false);
		btnSalvar.setEnabled(false);
	}

	private void mostrarComponentesComuns() {
		lblNumero.setVisible(true);
		lblCliente.setVisible(true);
		cbCliente.setVisible(true);
		btnSalvar.setEnabled(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws ParseException {
		frmCadastroDeTelefone = new JFrame();
		frmCadastroDeTelefone.setBounds(100, 100, 450, 300);
		frmCadastroDeTelefone.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeTelefone.getContentPane().setLayout(null);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(30, 20, 45, 14);
		frmCadastroDeTelefone.getContentPane().add(lblTipo);

		// mascaraTelefoneFixo;
		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(30, 60, 45, 14);
		frmCadastroDeTelefone.getContentPane().add(lblNumero);

		lblCliente = new JLabel("Cliente:");
		lblCliente.setBounds(30, 100, 45, 14);
		frmCadastroDeTelefone.getContentPane().add(lblCliente);

		ClienteController clienteController = new ClienteController();
		cbCliente = new JComboBox(clienteController.consultarTodos().toArray());
		cbCliente.setBounds(90, 100, 250, 20);
		// INICIA SEM ESTAR PREENCHIDO
		cbCliente.setSelectedItem(null);
		frmCadastroDeTelefone.getContentPane().add(cbCliente);

		// DECLARACAO MÁSCARAS
		mascaraTelefoneFixo = new MaskFormatter("(##)####-####");
		mascaraTelefoneMovel = new MaskFormatter("(##)9####-####");
		mascaraTelefoneFixo.setValueContainsLiteralCharacters(false);
		mascaraTelefoneMovel.setValueContainsLiteralCharacters(false);

		txtTelefoneFixo = new JFormattedTextField(mascaraTelefoneFixo);
		txtTelefoneFixo.setBackground(new Color(192, 192, 192));
		txtTelefoneFixo.setForeground(new Color(255, 0, 255));
		txtTelefoneFixo.setBounds(90, 60, 250, 20);
		frmCadastroDeTelefone.getContentPane().add(txtTelefoneFixo);

		txtTelefoneMovel = new JFormattedTextField(mascaraTelefoneMovel);
		txtTelefoneMovel.setForeground(Color.BLUE);
		txtTelefoneMovel.setBounds(90, 60, 250, 20);
		txtTelefoneMovel.setVisible(false);
		frmCadastroDeTelefone.getContentPane().add(txtTelefoneMovel);

		rbFixo = new JRadioButton("Fixo");
		rbFixo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(false);
				txtTelefoneFixo.setVisible(true);
			}
		});
		rbFixo.setBounds(75, 16, 110, 23);
		frmCadastroDeTelefone.getContentPane().add(rbFixo);

		rbMovel = new JRadioButton("Móvel");
		rbMovel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarComponentesComuns();
				txtTelefoneMovel.setVisible(true);
				txtTelefoneFixo.setVisible(false);
			}
		});
		rbMovel.setBounds(200, 16, 109, 23);
		frmCadastroDeTelefone.getContentPane().add(rbMovel);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(rbFixo);
		grupo.add(rbMovel);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(170, 150, 100, 30);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelefoneVO novoTelefone = new TelefoneVO();
				novoTelefone.setMovel(rbMovel.isSelected());

				preencherNumeroDdd(novoTelefone);

				ClienteVO clienteSelecionado = (ClienteVO) cbCliente.getSelectedItem();
				if (clienteSelecionado != null) {
					novoTelefone.setIdCliente(clienteSelecionado.getId());
				}

				TelefoneController telController = new TelefoneController();
				try {
					telController.inserir(novoTelefone);
					JOptionPane.showMessageDialog(null, "Telefone salvo!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (TelefoneJaUtilizadoException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Atenção!", JOptionPane.ERROR_MESSAGE);
				}
				
				limparTela();
			}

			private void preencherNumeroDdd(TelefoneVO novoTelefone) {
				String numeroCompletoDigitado = "";
				if (novoTelefone.isMovel()) {
					try {
						numeroCompletoDigitado = mascaraTelefoneMovel.stringToValue(txtTelefoneMovel.getText()) + "\n";

					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Informe um número válido", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					try {
						numeroCompletoDigitado = mascaraTelefoneFixo.stringToValue(txtTelefoneFixo.getText()) + "\n";
						
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Informe um número válido", "Atenção",
								JOptionPane.WARNING_MESSAGE);
					}
				}

				String ddd = numeroCompletoDigitado.substring(0, 2);
				String numero = numeroCompletoDigitado.substring(2);

				novoTelefone.setDdd(ddd);
				novoTelefone.setNumero(numero);

			}

		});

		// SÓ CADASTRO O CLIENTE SE FOR SELECIONADO
		frmCadastroDeTelefone.getContentPane().add(btnSalvar);
	}

	/**
	 * Limpa todos os campos da tela, depois de salvar uma tela
	 *
	 */

	protected void limparTela() {
		this.rbFixo.setSelected(false);
		this.rbMovel.setSelected(false);
		this.txtTelefoneFixo.setText("");
		this.txtTelefoneMovel.setText("");
		this.cbCliente.setSelectedIndex(-1);
	}
}
