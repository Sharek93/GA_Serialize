import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class Window {

	private JFrame frame;
	private JTextField idTextField;
	private JTextField lastNameTextField;
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_5 = new JPanel();
		frame.getContentPane().add(panel_5);
		panel_5.setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_5.add(scrollPane);
		
		table = new JTable(new DefaultTableModel(new Object[]{"ID","Imię","Nazwisko","Wiek","Aktywny"}, 0));
		scrollPane.setViewportView(table);
		
		JButton loadButton = new JButton("Wczytaj");
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				List<Person> persons = MySQLConnector.LoadData();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				for (int i = model.getRowCount() - 1; i >= 0 ; i--) {
					model.removeRow(i);
				}
				
				for (Person person : persons) {
					model.addRow(new Object[]{person.getId(), person.getName(), person.getlastName(), person.getAge(), person.getActive()});
				}
				
			}
		});
		panel_5.add(loadButton);
		
		JPanel updatePanel = new JPanel();
		frame.getContentPane().add(updatePanel);
		updatePanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		updatePanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		idTextField = new JTextField();
		panel.add(idTextField);
		idTextField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		updatePanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Nazwisko:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1);
		
		lastNameTextField = new JTextField();
		panel_1.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		updatePanel.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Imię");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2);
		
		nameTextField = new JTextField();
		panel_2.add(nameTextField);
		nameTextField.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		updatePanel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Wiek:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_3);
		
		ageTextField = new JTextField();
		panel_3.add(ageTextField);
		ageTextField.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		updatePanel.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton saveButton = new JButton("Dodaj / Edytuj");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {					
					int id = Integer.parseInt(idTextField.getText());
					int age = Integer.parseInt(ageTextField.getText());
					
					if (idTextField.getText() != null) {
						Person person = new Person(id, nameTextField.getText(), lastNameTextField.getText(), age, true);
						
						if(MySQLConnector.ChechData(id)) {
							MySQLConnector.UpdateDate(person);
						}
						else {
							MySQLConnector.SaveData(person);	
						}
					}													
					
													
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
				idTextField.setText("");
				nameTextField.setText("");
				lastNameTextField.setText("");
				ageTextField.setText("");
			}
		});
		panel_4.add(saveButton);
	}

}
