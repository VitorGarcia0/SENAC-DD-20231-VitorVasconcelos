package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.ClienteController;
import controller.EnderecoController;
import model.Exception.CampoInvalidoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.Color;

public class TelaCadastroCliente {
	// PARA CRIAR UMA NOVA TELA, NEW -> OTHHER -> WINDOW - > SWING DESIGNER ->
	// APPLICATION
	private JFrame frmCadastroDeCliente;
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JComboBox cbEndereco;
	private JLabel lblCPF;
	private JLabel lblEndereco;
	private JLabel lblNome;
	private JButton btnSalvar;
	private MaskFormatter mascaraCpf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroCliente window = new TelaCadastroCliente();
					window.frmCadastroDeCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public TelaCadastroCliente() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmCadastroDeCliente = new JFrame();
		frmCadastroDeCliente.getContentPane().setBackground(new Color(255, 255, 255));
		frmCadastroDeCliente.getContentPane().setForeground(new Color(0, 0, 0));

		frmCadastroDeCliente.setTitle("Cadastro De Cliente");
		frmCadastroDeCliente.setBounds(100, 100, 380, 235);
		frmCadastroDeCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeCliente.getContentPane().setLayout(null);

		EnderecoController endController = new EnderecoController();
		List<EnderecoVO> listaEnderecos = endController.consultarTodos();
		cbEndereco = new JComboBox<>(listaEnderecos.toArray());

		cbEndereco.setToolTipText("Selecione");
		cbEndereco.setSelectedIndex(-1);
		cbEndereco.setBounds(75, 85, 253, 20);

		// List<EnderecoVO> endereco = new ArrayList<EnderecoVO>();
		// cbEndereco = new JComboBox<>(endereco.toArray());

		frmCadastroDeCliente.getContentPane().add(cbEndereco);

		txtNome = new JTextField();
		txtNome.setBounds(75, 25, 200, 20);
		frmCadastroDeCliente.getContentPane().add(txtNome);
		txtNome.setColumns(10);

		lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(20, 55, 46, 20);
		frmCadastroDeCliente.getContentPane().add(lblCPF);

		lblEndereco = new JLabel("Endereco:");
		lblEndereco.setBounds(20, 85, 76, 20);
		frmCadastroDeCliente.getContentPane().add(lblEndereco);

		lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 25, 46, 20);
		frmCadastroDeCliente.getContentPane().add(lblNome);

		// MASCARA DO CPF
		// # --> MÁSCARA PRA NÚMERO
		// $ --> MÁSCARA PARA LETRAS
		mascaraCpf = new MaskFormatter("###.###.###-##");

		txtCPF = new JFormattedTextField(mascaraCpf);
		txtCPF.setBounds(75, 55, 200, 20);
		frmCadastroDeCliente.getContentPane().add(txtCPF);

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteVO novoCliente = new ClienteVO();
				novoCliente.setNome(txtNome.getText());

				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
					novoCliente.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}

				novoCliente.setEndereco((EnderecoVO) cbEndereco.getSelectedItem()); // PEGA O OBJETO SELECIONADO
				ClienteController clienteController = new ClienteController();

				try {
					clienteController.inserir(novoCliente);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (CpfJaUtilizadoException | EnderecoInvalidoException | CampoInvalidoException excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(264, 128, 80, 30);
		frmCadastroDeCliente.getContentPane().add(btnSalvar);
	}
}
