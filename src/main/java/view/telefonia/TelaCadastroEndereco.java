package view.telefonia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.EnderecoController;
import model.Exception.CampoInvalidoException;
import model.vo.telefonia.EnderecoVO;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * Tela de Cadastro/ edição de Endereço O atributo 'endereco' que indica o que
 * será feito - está nulo: cadastro - já veio preenchido no construtor: edição
 * 
 * @author vitor.vasconcelos
 *
 */

public class TelaCadastroEndereco {

	private JFrame frmCadastroDeEndereco;
	private JTextField txtCEP;
	private JTextField txtRua;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JLabel lblRua;
	private JLabel lblBairro;
	private JLabel lblNumero;
	private JLabel lblCidade;
	private JLabel lblEstado;
	private JComboBox cbEstado;
	private JButton btnSalvar;

	private EnderecoVO endereco;

	// CHAMAR API ou BACKEND FUTURAMENTE
	private String[] estados = { "PR", "RS", "SC" };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroEndereco window = new TelaCadastroEndereco(null);
					window.frmCadastroDeEndereco.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastroEndereco(EnderecoVO enderecoSelecionado) {
		this.endereco = enderecoSelecionado;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastroDeEndereco = new JFrame();
		frmCadastroDeEndereco.getContentPane().setForeground(Color.WHITE);
		frmCadastroDeEndereco.setForeground(Color.BLACK);
		frmCadastroDeEndereco.getContentPane().setBackground(Color.WHITE);
		frmCadastroDeEndereco.setTitle("Cadastro De Endereco");
		frmCadastroDeEndereco.setBounds(100, 100, 535, 304);
		frmCadastroDeEndereco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeEndereco.getContentPane().setLayout(null);
		
		JLabel lblCEP = new JLabel("CEP:");
		lblCEP.setBounds(15, 15, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblCEP);
		
		txtCEP = new JTextField();
		txtCEP.setBounds(60, 12, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtCEP);
		txtCEP.setColumns(10);
		
		lblRua = new JLabel("Rua:");
		lblRua.setBounds(15, 40, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblRua);
		
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(15, 65, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblBairro);
		
		lblNumero = new JLabel("Número:");
		lblNumero.setBounds(15, 90, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblNumero);
		
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(15, 115, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblCidade);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(15, 140, 45, 14);
		frmCadastroDeEndereco.getContentPane().add(lblEstado);
		
		txtRua = new JTextField();
		txtRua.setBounds(60, 37, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtRua);
		txtRua.setColumns(10);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(60, 62, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtBairro);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(60, 87, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtNumero);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(60, 112, 300, 20);
		frmCadastroDeEndereco.getContentPane().add(txtCidade);
		
		cbEstado = new JComboBox(estados);
		cbEstado.setToolTipText("Selecione");
		cbEstado.setSelectedIndex(-1);
		cbEstado.setBounds(60, 136, 300, 22);
		frmCadastroDeEndereco.getContentPane().add(cbEstado);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean edicao = false;
				if(endereco == null) {
					//CADASTRO DE NOVO
					endereco = new EnderecoVO();
				} else {
					// EDIÇÃO DO ENDEREÇO
					edicao = true;
				}
				
				endereco.setCep(txtCEP.getText());
				endereco.setRua(txtRua.getText());
				endereco.setNumero(txtNumero.getText());
				endereco.setBairro(txtBairro.getText());;
				endereco.setCidade(txtCidade.getText());
				endereco.setEstado((String) cbEstado.getSelectedItem());
				
				EnderecoController controller = new EnderecoController();
				try {
					// SE FOR EDICAO, PARA ATUALIZAR OU CADASTRAR UM NOVO
					if(edicao) {
						controller.atualizar(endereco);
					} else {
						controller.inserir(endereco);
						limparTela();
					}
					JOptionPane.showMessageDialog(null, "Endereço: " + (edicao ? " atualizado " : " criado ") +  " com sucesso!",
							"Sucesso", JOptionPane.INFORMATION_MESSAGE);
				} catch (CampoInvalidoException e1) {
					JOptionPane.showMessageDialog(null, 
							"Preencha os seguintes campos: \n\n" + e1.getMessage(), 
							"Atenção", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(190, 170, 100, 23);
		frmCadastroDeEndereco.getContentPane().add(btnSalvar);
		
		
		//Preenche os campos na tela (binding) 
		if(endereco != null) {
			txtCEP.setText(endereco.getCep());
			txtRua.setText(endereco.getRua());
			txtNumero.setText(endereco.getNumero());
			txtCidade.setText(endereco.getCidade());
			txtBairro.setText(endereco.getBairro());
			
			cbEstado.setSelectedItem(endereco.getEstado());
				
		}
		frmCadastroDeEndereco.setVisible(true);
	}

	protected void limparTela() {
		this.endereco = null;
		txtCEP.setText("");
		txtRua.setText("");
		txtNumero.setText("");
		txtCidade.setText("");
		txtBairro.setText("");
		cbEstado.setSelectedItem(null);
	}
}
