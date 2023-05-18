package view.telefonia;

import javax.swing.JPanel;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.LayoutManager;


public class PainelListagemTelefone extends JPanel {
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public PainelListagemTelefone() {
		setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 0, 0);
		add(table_1);

	}

}
