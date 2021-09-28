import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.LayoutStyle.ComponentPlacement;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class MagazynyLista {

	public JFrame frame;
	public static JTable table;
	public static String TYP = "Magazyny";
	public static String Nazwa;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MagazynyLista window = new MagazynyLista();
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
	public MagazynyLista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		TowarSQL TSQL = new TowarSQL();
		frame = new JFrame();
		frame.setBounds(100, 100, 806, 396);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StatusOperacji.setCzyMagazyn_ADD(true);
				StatusOperacji.setCzyMagazyn_EDIT(false);
				StatusOperacji.setCzyMagazyn_VIEW(false);
				Magazyn magaz = new Magazyn();
				magaz.magazyny();
				this.setEnable(false);
			}

			private void setEnable(boolean b) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton btnNewButton_1 = new JButton("Edytuj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusOperacji SO = new StatusOperacji();
				Login log = new Login();
				try {
					if(!SO.isBlokadaObiektu(TYP, Nazwa)) {
					SO.BlokadaObiektu(TYP, Nazwa, log.getLogin());
					StatusOperacji.setCzyMagazyn_ADD(false);
					StatusOperacji.setCzyMagazyn_EDIT(true);
					StatusOperacji.setCzyMagazyn_VIEW(false);
					Magazyn mag = new Magazyn();
					mag.magazyny();
					}else {
						JOptionPane.showMessageDialog(frame, "Obiekt jest ju¿ edytowany przez u¿ytkownika");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("Podgl\u0105d");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusOperacji.setCzyMagazyn_ADD(false);
				StatusOperacji.setCzyMagazyn_EDIT(false);
				StatusOperacji.setCzyMagazyn_VIEW(true);
				Magazyn mag = new Magazyn();
				mag.magazyny();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 747, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ROW = table.getSelectedRow();
				TableModel model = table.getModel();
				Nazwa = (String) model.getValueAt(ROW, 0);
				TSQL.setNazwa(Nazwa);
				
			}
		});
		table.setModel(DbUtils.resultSetToTableModel(TSQL.GETMAGAZYN()));
		scrollPane.setViewportView(table);
		frame.getContentPane().setLayout(groupLayout);
	}
}
