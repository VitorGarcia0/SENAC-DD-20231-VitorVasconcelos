package view.telefonia;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import model.Exception.CampoInvalidoException;
import model.Exception.CpfJaUtilizadoException;
import model.Exception.EnderecoInvalidoException;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.ClienteController;
import controller.EnderecoController;

public class PainelCadastroCliente extends JPanel {

	private ClienteVO cliente;
	private JTextField txtNome;
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JLabel lblCpf;
	private MaskFormatter mascaraCpf;
	private JFormattedTextField txtCPF;
	private JLabel lblEndereco;
	private JComboBox cbEndereco;
	private JButton btnSalvar;
	private JButton btnVoltar;

	public PainelCadastroCliente(ClienteVO clienteParaEditar) {
		if (clienteParaEditar != null) {
			this.cliente = clienteParaEditar;
		} else {
			this.cliente = new ClienteVO();
		}

		setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(47dlu;default)"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblTitulo = new JLabel(cliente.getId() == null ? "NOVO CLIENTE" : "EDIÇÃO DE CLIENTE");
		lblTitulo.setFont(new Font("Calibri", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		add(lblTitulo, "4, 2, 5, 1, center, default");

		lblNome = new JLabel("Nome");
		add(lblNome, "4, 4, right, default");

		txtNome = new JTextField();
		add(txtNome, "6, 4, 3, 1, fill, default");
		txtNome.setColumns(10);

		lblCpf = new JLabel("CPF");
		add(lblCpf, "4, 6, right, default");

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			// silent
		}

		txtCPF = new JFormattedTextField(mascaraCpf);
		add(txtCPF, "6, 6, 3, 1, fill, default");

		lblEndereco = new JLabel("Endereço");
		add(lblEndereco, "4, 8, right, default");

		EnderecoController endController = new EnderecoController();
		cbEndereco = new JComboBox(endController.consultarTodos().toArray());
		add(cbEndereco, "6, 8, 3, 1, fill, default");

		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.setNome(txtNome.getText());

				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
					cliente.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao converter o CPF", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				cliente.setEndereco((EnderecoVO) cbEndereco.getSelectedItem());

				ClienteController controller = new ClienteController();
				try {
					controller.inserir(cliente);
					JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CpfJaUtilizadoException | EnderecoInvalidoException | CampoInvalidoException excecao) {
					JOptionPane.showMessageDialog(null, excecao.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnSalvar, "6, 12");

		btnVoltar = new JButton("Voltar");
		add(btnVoltar, "8, 12");

		if (this.cliente.getId() != null) {
			preencherCamposDaTela();

		}
	}

	private void preencherCamposDaTela() {
		this.txtCPF.setText(this.cliente.getCpf());
		this.txtNome.setText(this.cliente.getNome());
		this.cbEndereco.setSelectedItem(this.cliente.getEndereco());
	}

}
