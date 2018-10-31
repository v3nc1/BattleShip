package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira prozor prikaz rezultata igre
 * 
 */
public class GameResults extends Dialog implements ResultsListener {

	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel p1WinerLbl;
	private JLabel p2WinerLbl;
	private Font winerFont;
	private Font bodyFont;
	
	private JLabel pl1Name;
	private JLabel pl1lvl;
	private JLabel pl1Accuracy;
	
	private JLabel pl2Name;
	private JLabel pl2lvl;
	private JLabel pl2Accuracy;
	private JButton btnOk;
	

	private boolean winerFlag;

	public GameResults(JFrame frame) {
		super(frame);

		setVisible(true);
		setSize(600, 500);
		setLocationRelativeTo(null);

		runCreate();
		runLayout();
		runAction();

	}
	/*
	 * Metoda postavlja ActionListenere za objekte 
	 */
	private void runAction() {

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent arg0) {


				dispose();

			}
			public void windowClosed(WindowEvent arg0) {


				dispose();

			}
			
		});

	
	}
	/*
	 * Metoda inicijalizira objekte klase
	 */
	private void runCreate() {
		
		winerFont=new Font("Verdana", Font.BOLD, 30);
		bodyFont=new Font("Verdana", Font.BOLD, 18);
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		p1WinerLbl=new JLabel();
		p1WinerLbl.setBackground(Color.GRAY);
		p1WinerLbl.setFont(winerFont);
		pl1Name = new JLabel("Player : ");
		pl1Name.setBackground(Color.GRAY);
		pl1Name.setFont(bodyFont);
		pl1lvl = new JLabel("Level: ");
		pl1lvl.setBackground(Color.GRAY);
		pl1lvl.setFont(bodyFont);
		pl1Accuracy = new JLabel("Accuracy:");
		pl1Accuracy.setBackground(Color.GRAY);
		pl1Accuracy.setFont(bodyFont);
		
		p2WinerLbl=new JLabel();
		p2WinerLbl.setBackground(Color.GRAY);
		p2WinerLbl.setFont(winerFont);
		pl2Name = new JLabel("Player : ");
		p2WinerLbl.setBackground(Color.GRAY);
		pl2Name.setFont(bodyFont);
		pl2lvl = new JLabel("Level: ");
		p2WinerLbl.setBackground(Color.GRAY);
		pl2lvl.setFont(bodyFont);
		pl2Accuracy = new JLabel("Accuracy:");
		p2WinerLbl.setBackground(Color.GRAY);
		pl2Accuracy.setFont(bodyFont);

		btnOk = new JButton("OK");

	}
	/*
	 * Metoda koja rasporedjuje objekate i izgled prozora 
	 */
	private void runLayout() {

		setLayout(new GridBagLayout());
		setBackground(Color.BLACK);
		
		leftPanel.setBackground(Color.GRAY);
		rightPanel.setBackground(Color.GRAY);

		GridBagConstraints gc = new GridBagConstraints();

		leftPanel.setLayout(new GridBagLayout());
		rightPanel.setLayout(new GridBagLayout());


		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = gc.BOTH;
		gc.anchor = gc.CENTER;
		gc.insets=new Insets(10, 10, 10, 10);
		leftPanel.add(p1WinerLbl, gc);
		
		
		gc.gridy=1;
		gc.fill = gc.BOTH;
		gc.anchor = gc.LINE_START;
		leftPanel.add(pl1Name, gc);

		gc.gridy = 2;

		leftPanel.add(pl1lvl, gc);

		gc.gridy = 3;

		leftPanel.add(pl1Accuracy, gc);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = gc.BOTH;
		gc.anchor = gc.CENTER;
		rightPanel.add(p2WinerLbl, gc);
		
		
		gc.gridy = 1;

		rightPanel.add(pl2Name, gc);

		gc.gridy = 2;

		rightPanel.add(pl2lvl, gc);

		gc.gridy = 3;

		rightPanel.add(pl2Accuracy, gc);
		
		runBorder(leftPanel, "Player 1");
		runBorder(rightPanel, "Player 2");
		
		gc.gridx=0;
		gc.gridy=0;
		gc.fill = gc.BOTH;
		gc.anchor = gc.LINE_START;
		gc.insets=new Insets(20, 20, 10, 10);
		
		add(leftPanel, gc);
		
		gc.gridx=1;
		gc.gridy=0;
		
		add(rightPanel, gc);
		
		gc.gridx=0;
		gc.gridy=1;
		gc.gridwidth=2;
		gc.weighty=1.0;
		gc.fill = gc.NONE;
		gc.anchor = gc.CENTER;
		
		add(btnOk,gc);

	}
	/*
	 * Metoda kreira okvir objekta
	 *@param panel Objekt tipa Jpanel na koji treba dodati okvir
	 *@param name Objekt tipa String odredjuje naslov ovira 
	 */
	private void runBorder(JPanel panel, String name) {
		
		TitledBorder top=BorderFactory.createTitledBorder(name);
		
		top.setTitleFont(bodyFont);
		
		Border bott=BorderFactory.createEmptyBorder(10, 10, 10, 10);
		
		
		panel.setBorder(BorderFactory.createCompoundBorder(top, bott));
		

	}
	/*
	 * (non-Javadoc)
	 * @see GUI.ResultsListener#gameData(GUI.ResultEvent)
	 */
	@Override
	public void gameData(ResultEvent re) {

		winerFlag=re.getWinerFlag();
		
		if(winerFlag) {
			
			p1WinerLbl.setText("WINNER");
			
		}else {
			
			p2WinerLbl.setText("WINNER");
		}
		
		pl1Name.setText(re.getP1Name());
		pl1lvl.setText("Level: " + re.getP1lvl());
		pl1Accuracy.setText("Accuracy: " + re.getP1Accuracy() + "%");

		pl2Name.setText(re.getP2Name());
		pl2lvl.setText("Level: " + re.getP2lvl());
		pl2Accuracy.setText("Accuracy: " + re.getP2Accuracy() + "%");

	}

}
