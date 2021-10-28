import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Canvas;
import java.awt.Label;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TowarFormatka {

	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private TowarSQL TSQL = new TowarSQL();
	private StatusOperacji SO = new StatusOperacji();

	/**
	 * Launch the application.
	 */
	public static void towarF() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TowarFormatka window = new TowarFormatka();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public TowarFormatka() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 406);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 639, 369);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Ogólne", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nazwa");
		lblNewLabel.setBounds(10, 10, 45, 13);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(65, 7, 184, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Magazyn");
		lblNewLabel_1.setBounds(10, 49, 75, 13);
		panel.add(lblNewLabel_1);
		
		Choice choice = new Choice();
		choice.setBounds(91, 50, 184, 18);
			String magaz[] = TSQL.getMagazyny().clone();
			for(int i =TSQL.CountMAG()-1;i>=0;i--) {
				choice.add(magaz[i]);
			}
		panel.add(choice);
		
		Checkbox checkbox = new Checkbox("Czy aktywny");
		checkbox.setBounds(299, 7, 90, 21);
		if(SO.isCzyMagazyn_ADD()) {
		checkbox.setState(true);
		checkbox.setEnabled(false);
		}
		panel.add(checkbox);
		
		Label label = new Label("Jednostka miary");
		label.setBounds(294, 49, 105, 21);
		panel.add(label);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(399, 52, 225, 18);
		ResultSet rs =TSQL.getJM(choice.getSelectedItem());
		while(rs.next()) {
			choice_1.add(rs.getString("Skrót"));
		}
		panel.add(choice_1);
		
		Label label_1 = new Label("Opis");
		label_1.setBounds(10, 110, 59, 21);
		panel.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 137, 614, 160);
		panel.add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Zatwierd\u017A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(StatusOperacji.isCzyMagazyn_ADD() || StatusOperacji.isCzyMagazyn_EDIT()) {
				int i = 0;
					if(textField.getText().isBlank()) {
						lblNewLabel.setForeground(Color.RED);
						i++;
					}
					if(textPane.getText().isBlank()) {
						label_1.setForeground(Color.RED);
						i++;
					}
					if(i>0) {
						JOptionPane.showMessageDialog(frame, "Nie uzupe³niono danych w wymaganych polach. Proszê uzupe³niæ podœwietlone pola");
						i=0;
					}
			}}
		});
		btnNewButton.setBounds(525, 311, 99, 21);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Anuluj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(StatusOperacji.isCzyMagazyn_EDIT()) {
				SO.OdblokowanieObiektu("Towar", null);
				frame.dispose();
				}else {
				frame.dispose();
				}
				
			}
		});
		btnNewButton_1.setBounds(10, 311, 85, 21);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Dane historczyne", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 634, 342);
		panel_1.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		Label label_2 = new Label("Ilo\u015B\u0107 dost\u0119pna:");
		label_2.setBounds(10, 37, 89, 21);
		panel_2.add(label_2);
		
		Label label_3 = new Label("Ilo\u015B\u0107 Rzeczywista:");
		label_3.setBounds(10, 10, 112, 21);
		panel_2.add(label_3);
		
		Label label_4 = new Label("Zarezerwowane:");
		label_4.setBounds(10, 64, 96, 21);
		panel_2.add(label_4);
		
		Label label_5 = new Label("Wkr\u00F3tce dodane:");
		label_5.setBounds(10, 91, 102, 21);
		panel_2.add(label_5);
		
		Label label_6 = new Label("Brak danych");
		label_6.setBounds(122, 10, 84, 21);
		panel_2.add(label_6);
		
		Label label_6_1 = new Label("Brak danych");
		label_6_1.setBounds(122, 37, 84, 21);
		panel_2.add(label_6_1);
		
		Label label_6_2 = new Label("Brak danych");
		label_6_2.setBounds(122, 64, 84, 21);
		panel_2.add(label_6_2);
		
		Label label_6_3 = new Label("Brak danych");
		label_6_3.setBounds(122, 91, 84, 21);
		panel_2.add(label_6_3);
		
		if(SO.isCzyMagazyn_ADD()) {
			checkbox.setState(true);
			checkbox.setEnabled(false);
		}
		if(StatusOperacji.isCzyMagazyn_VIEW()) {
			btnNewButton.hide();
		}
	}
}
