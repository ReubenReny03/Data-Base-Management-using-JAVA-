package project;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Sql_Design {

	private JFrame frame;
	private JTextField Name;
	private JTextField ID;
	private JTextField Department;
	private JTable table;
	private JTable table_11;
	private JTextField user_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sql_Design window = new Sql_Design();
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
	public Sql_Design() {
		initialize();
		Connect();
		table_load();
		table2_load("a");
	}
	
	Connection con;
	PreparedStatement pat;
	ResultSet rs;
	private JTextField textField;
	private JTable table_1;
	
	public void Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testmysql","root",""); //add you password
		}
		catch (ClassNotFoundException ex) {
			
		
		}
		catch (SQLException ex) {
			
		}
	}
	public void table_load() {
		try {
			pat= con.prepareStatement("select * from REM0");
			rs = pat.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	public void table2_load(String a) {
		try {
			pat= con.prepareStatement("select * from REM0 where department = ?;");
			pat.setString(1, a);
			rs = pat.executeQuery();
			table_11.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(51, 51, 51));
		frame.getContentPane().setForeground(new Color(52, 52, 52));
		frame.setBounds(100, 100, 652, 549);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DB Manager");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(232, 10, 186, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51, 51, 51));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Register", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(10, 46, 247, 155);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1.setBounds(10, 22, 65, 25);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID: ");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1.setBounds(41, 67, 34, 25);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Dept:");
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_2.setBounds(17, 102, 58, 25);
		panel.add(lblNewLabel_1_2);
		
		Name = new JTextField();
		Name.setBounds(79, 29, 141, 19);
		panel.add(Name);
		Name.setColumns(10);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(79, 74, 141, 19);
		panel.add(ID);
		
		Department = new JTextField();
		Department.setColumns(10);
		Department.setBounds(79, 109, 141, 19);
		panel.add(Department);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(60, 60, 60));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,department;
				String id;
				name = Name.getText();
				id = ID.getText();
				department = Department.getText();
				try {
					pat = con.prepareStatement("select roll from REM0");
					
				}
				catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				try {
					if (id.equals("") || name.equals("") || department.equals("")) {
						JOptionPane.showMessageDialog(null, "fill the full form");
					}
					else if(Pattern.matches("[A-Za-z]{3,10}",name)==false){
						Name.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID Name: Do not add numbers in name and name should be 3 or more letters long");
						try {
							TimeUnit.SECONDS.sleep(1);
							Name.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(Pattern.matches("[A-Z]{3}+[2]{1}+[1-9]{1}+[A-Z]{2}+[0-9]{4}", id)== false){
						ID.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID ID: the ID should be in the format URK{year}{Department}{roll_ID} ");
						try {
							TimeUnit.SECONDS.sleep(1);
							ID.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(department.length()!=3 && Pattern.matches("[A-Z]", department)==false ){
						Department.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID Department: The department should be 3 letter long and no numbers");
						try {
							TimeUnit.SECONDS.sleep(1);
							Department.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						pat = con.prepareStatement("insert into REM0(roll,name,department)values(?,?,?)");
						pat.setString(1,id);
						pat.setString(2, name);
						pat.setString(3, department);
						pat.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Added");
					    Name.setText("");
					    ID.setText("");
					    Department.setText("");
					    Name.requestFocus();
					    table_load();
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Same ID detected");
				}
				
			}
		});
		btnNewButton.setBounds(37, 211, 76, 37);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(288, 57, 328, 206);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(48, 48, 48));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBounds(10, 272, 247, 122);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		user_id = new JTextField();
		user_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			try {
				String check_id = user_id.getText();
					pat = con.prepareStatement("select roll,name,department from REM0 where roll = ?");
					pat.setString(1, check_id);
					ResultSet rs = pat.executeQuery();
					if(rs.next() == true) {
						String id_w = rs.getString(1);
						String namme = rs.getString(2);
						String department = rs.getString(3);
						String id_s = String.valueOf(id_w);
						ID.setText(id_s);
						Name.setText(namme);
						Department.setText(department);	
					}
					else {
						ID.setText("");
						Name.setText("");
						Department.setText("");
					}
				
			}
			catch (Exception e1) { // SQLException
				// TDO Auto-generated catch block
				
			}
			
				
				
			}
		});
		user_id.setColumns(10);
		user_id.setBounds(48, 33, 141, 19);
		panel_1.add(user_id);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("ID: ");
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1_1.setBounds(10, 26, 34, 25);
		panel_1.add(lblNewLabel_1_1_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(48, 62, 76, 37);
		panel_1.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name,department;
				String id;
				String check_id = user_id.getText();
				name = Name.getText();
				id = ID.getText();
				department = Department.getText();
				try {
					if (id.equals("") || name.equals("") || department.equals("")){
						JOptionPane.showMessageDialog(null, "fill the full form");
					}
					else if(Pattern.matches("[A-Za-z]{3,10}",name)==false){
						Name.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID Name: Do not add numbers in name and name should be 3 or more letters long");
						try {
							TimeUnit.SECONDS.sleep(1);
							Name.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(Pattern.matches("[A-Z]{3}+[2]{1}+[1-9]{1}+[A-Z]{2}+[0-9]{4}", id)== false){
						ID.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID ID: the ID should be in the format URK{year}{Department}{roll_ID} ");
						try {
							TimeUnit.SECONDS.sleep(1);
							ID.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else if(department.length()!=3 && Pattern.matches("[A-Z]", department)==false ){
						Department.setForeground(new Color(128, 0, 0));
						JOptionPane.showMessageDialog(null, "INVALID Department: The department should be 3 letter long and no numbers");
						try {
							TimeUnit.SECONDS.sleep(1);
							Department.setForeground(new Color(0, 0, 0));
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						pat = con.prepareStatement("update REM0 set roll =?,name=?,department=? where roll=?");
						pat.setString(1,id);
						pat.setString(2, name);
						pat.setString(3, department);
						pat.setString(4,check_id);
						pat.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Updated");
					    Name.setText("");
					    ID.setText("");
					    Department.setText("");
					    Name.requestFocus();
					    table_load();
					}
				    
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setBackground(new Color(60, 60, 60));
		
		JButton remove = new JButton("Remove");
		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete from remo where roll=123;
				String id = ID.getText();
				try {
					pat = con.prepareStatement("delete from REM0 where roll=?");
					pat.setString(1,id);
					pat.executeUpdate();
					JOptionPane.showMessageDialog(null, "Removed Data");
				    Name.setText("");
				    ID.setText("");
				    Department.setText("");
				    Name.requestFocus();
				    table_load();
				    
					
				}
				catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
			}
		});
		remove.setBounds(146, 211, 86, 37);
		frame.getContentPane().add(remove);
		remove.setForeground(Color.WHITE);
		remove.setBackground(new Color(60, 60, 60));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sort", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1_1.setBackground(new Color(48, 48, 48));
		panel_1_1.setBounds(10, 404, 247, 80);
		frame.getContentPane().add(panel_1_1);
		
		textField = new JTextField(); //
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String text_find = String.valueOf(textField.getText());
						pat = con.prepareStatement("select roll,name,department from REM0 where department = ?");
						pat.setString(1, text_find);
						ResultSet rs = pat.executeQuery();
					
						
						if(rs.next() == true) {
							
							table2_load(text_find);
						}
						else {
							table2_load("A");
						}
					
				}
				catch (Exception e1) { // SQLException
					// TDO Auto-generated catch block
					
				}
				
				
				
			}
		});
		textField.setColumns(10);
		textField.setBounds(73, 33, 141, 19);
		panel_1_1.add(textField);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Dept:");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_1_1_1_1.setBounds(10, 26, 61, 25);
		panel_1_1.add(lblNewLabel_1_1_1_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(288, 301, 328, 187);
		frame.getContentPane().add(scrollPane_1);
		
		table_11 = new JTable();
		scrollPane_1.setViewportView(table_11);
	}
}
