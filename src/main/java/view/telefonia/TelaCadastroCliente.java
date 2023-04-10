package view.telefonia;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import controller.ClienteController;
import controller.EnderecoController;
import model.Exception.CampoInvalidoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;

import javax.swing.JFormattedTextField;
import javax.swing.JButton;

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
	 */
	public TelaCadastroCliente() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeCliente = new JFrame();
		
		frmCadastroDeCliente.setTitle("Novo Cliente");
		frmCadastroDeCliente.setBounds(100, 100, 450, 300);
		frmCadastroDeCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeCliente.getContentPane().setLayout(null);
		
		EnderecoController controller = new EnderecoController();
		controller.consultarTodos();
		List<EnderecoVO> listaEnderecos = new ArrayList<EnderecoVO>();
		listaEnderecos = controller.consultarTodos();
		cbEndereco = new JComboBox();
		cbEndereco.setToolTipText("Selecione");
		cbEndereco.setSelectedIndex(-1);
		cbEndereco.setBounds(95, 85, 260, 20);
		
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
		
		txtCPF = new JFormattedTextField();
		txtCPF.setBounds(75, 56, 200, 20);
		frmCadastroDeCliente.getContentPane().add(txtCPF);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteVO novoCliente = new ClienteVO();
				novoCliente.setNome(txtNome.getText());
				novoCliente.setCpf(txtCPF.getText());
				novoCliente.setEndereco((EnderecoVO)cbEndereco.getSelectedItem());
				
				ClienteController controller = new ClienteController();
				
				try {
					controller.inserir(novoCliente);
				} catch (CpfJaUtilizadoException e1) {
					JOptionPane.showMessageDialog(null, 
							"Cpf já foi utilizado: \n\n" + e1.getMessage(), 
							"Atenção", JOptionPane.WARNING_MESSAGE);
				} catch (EnderecoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, 
							"Endereço inválido: \n\n" + e1.getMessage(), 
							"Atenção", JOptionPane.WARNING_MESSAGE);
				} catch (CampoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, 
							"Preencha os seguintes campos: \n\n" + e1.getMessage(), 
							"Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(129, 140, 90, 25);
		frmCadastroDeCliente.getContentPane().add(btnSalvar);
	}
}
