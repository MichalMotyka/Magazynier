import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.sql.SQLException;
import java.awt.Choice;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class JednoskiMiary {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private static Choice choice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void JM() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JednoskiMiary window = new JednoskiMiary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JednoskiMiary() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 586, 546);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Label label = new Label("Magazyn");
		label.setBounds(310, 10, 62, 22);
		frame.getContentPane().add(label);
		
		Choice choice = new Choice();	
		choice.setBounds(378, 10, 162, 20);
		frame.getContentPane().add(choice);
		
		textField = new JTextField();
		textField.setBounds(128, 10, 162, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		Label label_1 = new Label("Nazwa Jednoski");
		label_1.setBounds(28, 10, 94, 22);
		frame.getContentPane().add(label_1);

		Choice choice_1 = new Choice();
		choice_1.setBounds(95, 147, 168, 20);
		frame.getContentPane().add(choice_1);
		
		Label label_2 = new Label("Symbol Jednoski");
		label_2.setBounds(28, 38, 94, 22);
		frame.getContentPane().add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(128, 40, 162, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Weryfikator wer = new Weryfikator();
				if(!wer.checkValuesIsNULL(textField.getText()) && !wer.checkValuesIsNULL(textField_1.getText())) {
					try {
						if(!wer.IsExist(textField.getText(), "Nazwa", "JednostkiMiary") && !wer.IsExist(textField_1.getText(), "skrot", "JednostkiMiary")) {
							TowarSQL TSQL = new TowarSQL();
							TSQL.ADDJM(textField.getText(), textField_1.getText(), choice.getSelectedItem());
							table.setModel(DbUtils.resultSetToTableModel(TSQL.getJM(choice.getSelectedItem())));
							choice_1.select(choice.getSelectedItem());
							textField.setText(null);
							textField_1.setText(null);
							label_1.setForeground(Color.BLACK);
							label_2.setForeground(Color.BLACK);
							JOptionPane.showMessageDialog(null, "Pomyœlnie dodano now¹ jednostke");
						}else {
							if(wer.IsExist(textField.getText(), "Nazwa", "JednostkiMiary")) {
								label_1.setForeground(Color.RED);
							}
							if(wer.IsExist(textField_1.getText(), "skrot", "JednostkiMiary")) {
								label_2.setForeground(Color.RED);
							}
							JOptionPane.showMessageDialog(null, "Jednostka o tym skrócie lub nazwie ju¿ istnieje");
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}}else {
					
					if(wer.checkValuesIsNULL(textField.getText())) {
						label_1.setForeground(Color.RED);
					}
					if(wer.checkValuesIsNULL(textField_1.getText())) {
						label_2.setForeground(Color.RED);
					}
					JOptionPane.showMessageDialog(null, "Proszê uzupe³niæ wszystkie wymagane pola");
				}
				}
		});
		btnNewButton.setBounds(28, 83, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 192, 512, 304);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Wyszukaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			TowarSQL TSQL = new TowarSQL();
			table.setModel(DbUtils.resultSetToTableModel(TSQL.getJM(choice_1.getSelectedItem())));
			}
		});
		btnNewButton_1.setBounds(293, 147, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		
		Label label_3 = new Label("Magazyn");
		label_3.setBounds(28, 145, 58, 22);
		frame.getContentPane().add(label_3);
		TowarSQL TSQL = new TowarSQL();
		String[] magazyny = TSQL.getMagazyny().clone();
		for(int licz = TSQL.CountMAG()-1; licz >=0; licz--) {
			choice.add(magazyny[licz]);
			choice_1.add(magazyny[licz]);
		}
		table.setModel(DbUtils.resultSetToTableModel(TSQL.getJM(choice_1.getSelectedItem())));
		frame.setTitle("Jednostki Miary");
	}
	
}
