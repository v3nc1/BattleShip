package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira prozor za unos novog korisnika
 * 
 */

public class NewPlayer extends Dialog {
	
	private JPanel mainPanel;
	private Font mainFont;
	private JLabel lblName;
	private JTextField txtName;
	private JButton btnConfirm;
	private JButton btnCancel;
	private UserListener nul;
	
	
	
	
	public NewPlayer(JFrame frame) {
		
		super(frame,"New Player",null);
		
		setVisible(true);
		setSize(300, 250);
		setLocationRelativeTo(null);
		runCreate();
		runLayout();
		runAction();
		
		
		
		
	}
	/*
	 * Metoda postavlja ActionListenere za objekte 
	 */
	private void runAction() {
		
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				NewUserEvent nue=new NewUserEvent(this);
				
				nue.setName(txtName.getText());
				
				nul.NewUserData(nue);
				setVisible(false);
				txtName.setText("");
				
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
		});
		addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				
			}
		});
			
			
	}
	
	public void setListener(UserListener nul) {
		
		this.nul=nul;
	}
	/*
	 * Metoda inicijalizira objekte klase
	 */
	private void runCreate() {
		
		setBackground(Color.BLACK);
		mainPanel=new JPanel();
		mainPanel.setBackground(Color.BLACK);
		mainFont=new Font("Arial", Font.BOLD, 24);
		lblName=new JLabel("ENTER NEW PLAYER",JLabel.CENTER);
		lblName.setFont(mainFont);
		lblName.setForeground(Color.GRAY);
		txtName=new JTextField(10);
		txtName.setFont(mainFont);
		btnCancel=new JButton("CANCEL");
		btnConfirm=new JButton("CONFIRM");
		
		
	}
	/*
	 * Metoda koja rasporedjuje objekate i izgled prozora 
	 */
	private void runLayout() {
		
		
		setLayout(new FlowLayout());
		
		mainPanel.setLayout(new FlowLayout());
		
		mainPanel.add(btnCancel);
		mainPanel.add(btnConfirm);
		
		
		add(lblName);
		add(txtName);
		add(mainPanel);
		
		
	}
	
	

}
