package GUI;

import java.awt.BorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controler.Controler;


/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira pocetni ekran igre
 */

public class StartFrame extends JFrame {
	
	private JLabel gameLabel;
	private JButton btnNewGame;
	private JButton btnScoreboard;
	private JButton btnExit;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JLabel backround;
	private Controler cntr;
	private NewGame newGame;
	private UserListener userLstn;
	private PlayerListEvent plEvent;
	private Scoreboard scBoard;
	
	
	
	
	
	public StartFrame() {
		
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		runCreate();
		runLayout();
		runAction();
		cntr.openUsers();
		
	}
	
	public void setListener(UserListener ul) {
		
		userLstn=ul;
	}
	/*
	 * Metoda u kojoj su aktivirani ActionListeneri nad komponentama
	 * 
	 */
	
	private void runAction() {
		
		btnNewGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				newGame=new NewGame();
				
				
			}
		});
		
		btnScoreboard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				scBoard=new Scoreboard();
				
				plEvent=new PlayerListEvent(this);
				
				plEvent.setPlayerList(cntr.getPlayerDB());
				
				scBoard.playerList(plEvent);
				
				
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				cntr.saveUsers();
				System.exit(0);
				
			}
		});
		
	}
	/*
	 * Metoda koja kreira komponente klase
	 * 
	 */
	private void runCreate() {
		
		cntr=new Controler();
		backround=new JLabel(new ImageIcon(".\\TitleImage.jpg"));
		topPanel=new JPanel();
		bottomPanel=new JPanel();
		gameLabel=new JLabel("BATTLESHIP",JLabel.CENTER);
		btnNewGame=new JButton("NEW GAME");
		btnNewGame.setFocusable(false);
		btnNewGame.setFont(new Font("Arial",Font.BOLD, 30));
		btnNewGame.setBackground(Color.black);
		btnNewGame.setForeground(Color.gray);
		
		btnScoreboard=new JButton("SCOREBOARD");
		btnScoreboard.setFocusable(false);
		btnScoreboard.setFont(new Font("Arial",Font.BOLD, 30));
		btnScoreboard.setBackground(Color.black);
		btnScoreboard.setForeground(Color.gray);
		
		btnExit=new JButton("EXIT");
		btnExit.setFocusable(false);
		btnExit.setFont(new Font("Arial",Font.BOLD, 30));
		btnExit.setBackground(Color.black);
		btnExit.setForeground(Color.gray);
		
		
		
	}
	/*
	 * Metoda koja definira izgled i raspored komponenti
	 * 
	 */
		private void runLayout() {
		GridBagConstraints gc=new GridBagConstraints();
		
		
		setLayout(new BorderLayout());
		topPanel.setLayout(new BorderLayout());
		bottomPanel.setLayout(new BorderLayout());
		backround.setLayout(new GridBagLayout());

		gameLabel.setFont(new Font("Arial",Font.BOLD, 50));
		gameLabel.setOpaque(true);
		gameLabel.setBackground(new Color(0,0,0));
		gameLabel.setForeground(Color.gray);
		
		
		
		topPanel.add(gameLabel);
	
		gc.gridx=0;
		gc.gridy=0;
		gc.insets=new Insets(15, 30, 15, 30);
		gc.ipady=30;
		gc.ipadx=50;
		gc.anchor=gc.BASELINE;
		gc.fill=gc.BOTH;
		
		backround.add(btnNewGame,gc);
		
		gc.gridy=2;
		backround.add(btnScoreboard,gc);
		
		gc.gridy=3;
		backround.add(btnExit,gc);
		
		bottomPanel.add(backround);
		
		topPanel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		add(topPanel,BorderLayout.NORTH);
		add(bottomPanel,BorderLayout.CENTER);
		
		
	}

}
