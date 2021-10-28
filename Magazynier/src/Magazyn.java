import java.awt.EventQueue;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.text.DateFormatter;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class Magazyn {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private Date dataD;
	private int IDMAG;
	private Weryfikator wer = new Weryfikator();
	Date data ;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void magazyny() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Magazyn window = new Magazyn();
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
	public Magazyn() {
		try {
			initialize();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.setBounds(100, 100, 459, 333);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
		);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Ogólne", null, panel, null);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nazwa");
		
		JLabel lblNewLabel_1 = new JLabel("Lokalizacja");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Czy aktywny");
		
		chckbxNewCheckBox.setSelected(true);
		
		
		JDateChooser dateChooser = new JDateChooser();
		
		JLabel lblNewLabel_2 = new JLabel("Data Aktywacji");
		
		JLabel lblNewLabel_2_1 = new JLabel("Data Dezaktywacji");
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setEnabled(false);
		
		JButton btnNewButton = new JButton("Zatwierd\u017A");
		JButton btnNewButton_1 = new JButton("Anuluj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusOperacji SO = new StatusOperacji();
				SO.setCzyMagazyn_ADD(false);
				SO.OdblokowanieObiektu(MagazynyLista.TYP, MagazynyLista.Nazwa);
				frame.dispose();
				
			}
			
		});
		StatusOperacji status = new StatusOperacji();
		if(status.isCzyMagazyn_ADD()) {
			dateChooser_1.setEnabled(false);
			chckbxNewCheckBox.setEnabled(false);
		}
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(chckbxNewCheckBox, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
									.addGap(302))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
											.addGap(17)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(dateChooser_1, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
										.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
									.addGap(169))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
										.addComponent(lblNewLabel))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
										.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE))))
							.addGap(19))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
							.addGap(256)
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(textField))
							.addGap(38))
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(chckbxNewCheckBox, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(6)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2_1, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
							.addGap(6))
						.addComponent(dateChooser_1, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
					.addGap(33)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		StatusOperacji SO = new StatusOperacji();
		if(SO.isCzyMagazyn_VIEW()) {
			TowarSQL TSQL = new TowarSQL();
			String[] Values = TSQL.GETVALUES_MAG(MagazynyLista.Nazwa).clone();
			IDMAG = Integer.parseInt(Values[5]);
			textField.setText(Values[0]);
			textField_1.setText(Values[1]);
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(Values[3]);
			dateChooser.setDate(data);
			if(Values[4] != null) {
		     dataD = new SimpleDateFormat("yyyy-MM-dd").parse(Values[4]);
			dateChooser_1.setDate(data);
			}
			if(Values[2].equals("1")) {
				chckbxNewCheckBox.setSelected(true);
			}else {
				chckbxNewCheckBox.setSelected(false);
			}
			textField.setEnabled(false);
			textField_1.setEnabled(false);
			dateChooser.setEnabled(false);
			dateChooser_1.setEnabled(false);
			chckbxNewCheckBox.setEnabled(false);
			btnNewButton.hide();
	
			
		}
		if(SO.isCzyMagazyn_EDIT()) {
			TowarSQL TSQL = new TowarSQL();
			String[] Values = TSQL.GETVALUES_MAG(MagazynyLista.Nazwa).clone();
			IDMAG = Integer.parseInt(Values[5]);
			textField.setText(Values[0]);
			textField_1.setText(Values[1]);
			 data = new SimpleDateFormat("yyyy-MM-dd").parse(Values[3]);
			dateChooser.setDate(data);
			if(Values[4] != null) {
			dataD = new SimpleDateFormat("yyyy-MM-dd").parse(Values[4]);
			dateChooser_1.setDate(data);
			}
			if(Values[2].equals("1")) {
				chckbxNewCheckBox.setSelected(true);
			}else {
				chckbxNewCheckBox.setSelected(false);
			}
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
					if(dateChooser_1.getDate() !=null && !chckbxNewCheckBox.isSelected()) {
					if(dateChooser_1.getDate() != null) {
						dataD = dateChooser_1.getDate();
					if(wer.CompareTwoDate(data, dataD)) {
					TowarSQL TSQL = new TowarSQL();
					TSQL.ADD_MAG_HIS(Values[0]);
					TSQL.UPDATEMAGAZYN(textField.getText(), textField_1.getText(), chckbxNewCheckBox.isSelected(), formatter.format(data),formatter.format(dataD),Values[0]);
					StatusOperacji SO = new StatusOperacji();
					SO.setCzyMagazyn_ADD(false);
					SO.OdblokowanieObiektu(MagazynyLista.TYP, MagazynyLista.Nazwa);
					MagazynyLista.table.setModel(DbUtils.resultSetToTableModel(TSQL.GETMAGAZYN()));
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(frame,"Data dezaktywacji jest wczeœniejsza ni¿ aktywacji");
				}}else {
					TowarSQL TSQL = new TowarSQL();
					TSQL.UPDATEMAGAZYND(textField.getText(), textField_1.getText(), chckbxNewCheckBox.isSelected(), formatter.format(dateChooser.getDate()),Values[0]);
					StatusOperacji SO = new StatusOperacji();
					SO.setCzyMagazyn_ADD(false);
					SO.OdblokowanieObiektu(MagazynyLista.TYP, MagazynyLista.Nazwa);
					MagazynyLista.table.setModel(DbUtils.resultSetToTableModel(TSQL.GETMAGAZYN()));
					frame.dispose();
					}}else {
						JOptionPane.showMessageDialog(frame, "Proszê uzupe³niæ datê dezaktywacji!");
					}}
			});
		}
		if(SO.isCzyMagazyn_ADD()) {
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					chckbxNewCheckBox.setEnabled(false);
					try {
						if(!wer.IsExist(textField.getText(),"Nazwa", "Magazyny")) {
							if(!wer.checkValuesIsNULL(textField.getText()) && !wer.checkValuesIsNULL(textField_1.getText()) && !wer.checkValuesIsNULL(dateChooser.getDate().toString())) {
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); 
						Date data = dateChooser.getDate();
						TowarSQL TSQL = new TowarSQL();
						TSQL.ADDMAGAZYN(textField.getText(), textField_1.getText(), chckbxNewCheckBox.isSelected(), formatter.format(data));
						StatusOperacji SO = new StatusOperacji();
						SO.setCzyMagazyn_ADD(false);
						SO.OdblokowanieObiektu(MagazynyLista.TYP, MagazynyLista.Nazwa);
						MagazynyLista.table.setModel(DbUtils.resultSetToTableModel(TSQL.GETMAGAZYN()));
						frame.dispose();
							}else {
								if(wer.checkValuesIsNULL(textField.getText())) {
									lblNewLabel.setForeground(Color.RED);
								}if(wer.checkValuesIsNULL(textField_1.getText())) {
									lblNewLabel_1.setForeground(Color.RED);
								}if(wer.checkValuesIsNULL(dateChooser.getDateFormatString())) {
									lblNewLabel_2.setForeground(Color.RED);
								}
								JOptionPane.showMessageDialog(frame, "Nie wszystkie wymagane pola zosta³a uzupe³nione!");
							}
							}else {
							textField.setForeground(Color.RED);
							JOptionPane.showMessageDialog(frame, "Magazyn o wybranej nazwie ju¿ istnieje");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}}
			});
		}
		if(dateChooser.getDate() != null) {
			dateChooser.setEnabled(false);
		}else {
			dateChooser.setEnabled(true);
		}
		if(dateChooser_1.getDate() == null &&  SO.isCzyMagazyn_ADD() == false) {
			chckbxNewCheckBox.setEnabled(true);
		}else {
			chckbxNewCheckBox.setEnabled(false);
		}
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected() && dateChooser_1.getDate() == null || SO.isCzyMagazyn_ADD() == true ) {
					
					dateChooser_1.setEnabled(false);
				}else {
					dateChooser_1.setEnabled(true);
				}
			}
		});
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Dane historyczne", null, panel_1, null);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		TowarSQL TSQL = new TowarSQL();
		table.setModel(DbUtils.resultSetToTableModel(TSQL.GET_MAG_HIS(IDMAG)));
		panel_1.setLayout(gl_panel_1);
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
