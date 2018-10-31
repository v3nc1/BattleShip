package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import Controler.Controler;
import Model.Game;
import Model.Player;

 /*
	 * @author Venci Ivos
	 * @since rujan, 2018.
	 * 
	 * Klasa za pokretanje igre.
	 * Ponudjen je izbor igraca i dodavanje novog igraca.
	 */
 
public class NewGame extends JFrame implements UserListener {
	
	
	private final int gridSize=8;
	private static int counter=0;
	private static boolean deployFlag=true;
	private JPanel mainPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	private JComboBox cmbPlayer1;
	private JComboBox cmbPlayer2;
	private DefaultComboBoxModel boxPlayer1;
	private DefaultComboBoxModel boxPlayer2;
	private JButton btnNewPlayer;
	private JButton btnCancel;
	private JButton btnStart;
	private JLabel lblSelect1;
	private JLabel lblSelect2;
	private Font fontTitle;
	private NewPlayer newPlayer;
	private Controler cntr;
	private DeployFleet dFleet;
	private GameFrame gFrame;
	
	/*
	 * Konstruktor klase 
	 * @param
	 * 		Objekt tipa Controler
	 */
	public NewGame() {

		

		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		runCreate();
		runLayout();
		runAction();

	}
	
	/*
	 * Metoda koja aktivira ActionListenere nad komponentama prozora.
	 */

	private void runAction() {

		btnNewPlayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				newPlayer.setVisible(true);

			}
		});


		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cntr.saveUsers();
				
				dispose();

			}
		});
		/*
		 * 
		 * Dugme koje pokrece igru i provjerava dali su ispravno 
		 * odabrani korisnici, u suprotnom baca iznimku i daje obavjest.
		 * Pokrece metodu za pocetak igre.
		 * 
		 * @throw ExceptionInInitializerError
		 * 
		 * 
		 * 
		 */
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					if (cmbPlayer1.getSelectedItem().toString().equals(null)
							|| cmbPlayer2.getSelectedItem().toString().equals(null)) {

						throw new ExceptionInInitializerError();

					} else if (cmbPlayer1.getSelectedItem().toString()
							.equals(cmbPlayer2.getSelectedItem().toString())) {

						throw new ExceptionInInitializerError();

					} else {
						counter++;
						cntr.newGame(cntr.getPlayer(cmbPlayer1.getSelectedItem().toString()),
								cntr.getPlayer(cmbPlayer2.getSelectedItem().toString()), counter);
						startDeploy();
					}
				} catch (ExceptionInInitializerError error) {

					JOptionPane.showMessageDialog(null, "Niste ispravno odabrali korisnike", "Odaberite korisnike",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

	}
	
	/*
	 * (non-Javadoc)
	 * @see GUI.UserListener#DeploymentData(GUI.DeploymentEvent)
	 * 
	 * 
	 */

	@Override
	public void DeploymentData(DeploymentEvent de) {

		boolean[][] grid = de.getPlayerField();
		int[] field = new int[2];
		
		
		/*
		 * Podsavanje vrijednosti na igracevoj ploci
		 * @param deployFlag
		 * 		Parametar tipa boolean odredjuje dali se ploca postavlja za prvog
		 * 		odnosno drugog igraca
		 * 		
		 */
		if (deployFlag) {
			for (int x = 0; x < gridSize; x++) {

				for (int y = 0; y < gridSize; y++) {

					if (grid[x][y]) {

						field[0] = x;
						field[1] = y;

						cntr.setP1Boards(cntr.getGame(counter), field);
					}

				}

			}
			deployFlag = false;
			startDeploy();

		} else if (!deployFlag) {
			
			
			for (int x = 0; x < gridSize; x++) {

				for (int y = 0; y < gridSize; y++) {

					if (grid[x][y]) {

						field[0] = x;
						field[1] = y;

						cntr.setP2Boards(cntr.getGame(counter), field);
					}
				}
			}
			
			gFrame = new GameFrame(new JFrame(), counter);
			gFrame.setListener(this);
			
		}

	}
	
	/*
	 * (non-Javadoc)
	 * @see GUI.UserListener#restartDeployment(GUI.DeploymentEvent)
	 */
	@Override
	public void restartDeployment(DeploymentEvent de) {

		deployFlag=de.getFlag();
		
	}
	/*
	 * (non-Javadoc)
	 * @see GUI.UserListener#NewUserData(GUI.NewUserEvent)
	 */
	@Override
	public void NewUserData(NewUserEvent nue) {

		cntr.newPlayer(nue.getName());
		boxPlayer1.addElement(nue.getName());
		boxPlayer2.addElement(nue.getName());
		cntr.saveUsers();

	}
	
	/*
	 * Metoda koja pokrece prozor za postavljanje 
	 * brodova na plocu za pojedinog igraca
	 */
	private void startDeploy() {
		
			
		
		if (deployFlag) {
			
				
			dFleet = new DeployFleet(cntr.getP1(cntr.getGame(counter)).getPlayerName());
			dFleet.setListener(this);
				
			
		} else {
			
			dFleet = new DeployFleet(cntr.getP2(cntr.getGame(counter)).getPlayerName());
			dFleet.setListener(this);
			
		}
		
		
		
		

	}
	
	/*
	 * Metoda koja inicijalizira objekte klase
	 */

	private void runCreate() {

		cntr=new Controler();
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		rightPanel = new JPanel();
		bottomPanel = new JPanel();
		fontTitle = new Font("Verdana", Font.BOLD, 18);
		lblSelect1 = new JLabel("Select Player", JLabel.CENTER);
		lblSelect1.setFont(fontTitle);
		lblSelect1.setForeground(Color.GRAY);
		lblSelect2 = new JLabel("Select Player", JLabel.CENTER);
		lblSelect2.setFont(fontTitle);
		lblSelect2.setForeground(Color.GRAY);
		cmbPlayer1 = new JComboBox<>();
		cmbPlayer2 = new JComboBox<>();
		boxPlayer1 = new DefaultComboBoxModel<>();
		boxPlayer1.addElement("Select player");
		boxPlayer2 = new DefaultComboBoxModel<>();
		boxPlayer2.addElement("Select player");

		if (!(cntr.getPlayerDB().isEmpty())) {

			for (Player p : cntr.getPlayerDB()) {

				boxPlayer1.addElement(p.getPlayerName());
				boxPlayer2.addElement(p.getPlayerName());
			}
		}
		cmbPlayer1.setModel(boxPlayer1);
		cmbPlayer2.setModel(boxPlayer2);
		btnNewPlayer = new JButton("NEW PLAYER");
		btnNewPlayer.setFont(fontTitle);
		btnStart = new JButton("START GAME");
		btnStart.setFont(fontTitle);
		btnCancel = new JButton("CANCEL");
		btnCancel.setFont(fontTitle);
		newPlayer = new NewPlayer(new JFrame());
		newPlayer.setVisible(false);
		newPlayer.setListener(this);
		

	}
	/*
	 * Metoda koja definira raspored i izgled prozora
	 */
	private void runLayout() {

		setLayout(new BorderLayout());

		mainPanel.setLayout(new GridLayout(1, 2));
		leftPanel.setLayout(new GridBagLayout());
		rightPanel.setLayout(new GridBagLayout());
		bottomPanel.setLayout(new FlowLayout());

		TitledBorder top1 = BorderFactory.createTitledBorder("PLAYER 1");
		TitledBorder top2 = BorderFactory.createTitledBorder("PLAYER 2");

		top1.setTitleColor(Color.GRAY);
		top1.setTitleFont(fontTitle);
		top2.setTitleColor(Color.GRAY);
		top2.setTitleFont(fontTitle);
		leftPanel.setBorder(BorderFactory.createCompoundBorder(top1, BorderFactory.createRaisedSoftBevelBorder()));
		rightPanel.setBorder(BorderFactory.createCompoundBorder(top2, BorderFactory.createRaisedSoftBevelBorder()));

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;

		gc.anchor = gc.CENTER;
		gc.fill = gc.BOTH;
		gc.insets = new Insets(40, 20, 40, 20);

		leftPanel.add(lblSelect1, gc);

		gc.gridy = 1;

		leftPanel.add(cmbPlayer1, gc);

	

		gc.gridx = 0;
		gc.gridy = 0;

		gc.anchor = gc.CENTER;
		gc.fill = gc.BOTH;
		gc.insets = new Insets(40, 20, 40, 20);

		rightPanel.add(lblSelect2, gc);

		gc.gridy = 1;

		rightPanel.add(cmbPlayer2, gc);

		
		leftPanel.setBackground(Color.BLACK);
		leftPanel.setForeground(Color.WHITE);
		rightPanel.setBackground(Color.BLACK);

		mainPanel.add(leftPanel);
		mainPanel.add(rightPanel);
		bottomPanel.add(btnCancel);
		bottomPanel.add(btnNewPlayer);
		bottomPanel.add(btnStart);
		bottomPanel.setBackground(Color.BLACK);

		add(mainPanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

	}
	/*
	 * (non-Javadoc)
	 * @see GUI.UserListener#playerList(GUI.PlayerListEvent)
	 */
	@Override
	public void playerList(PlayerListEvent ule) {
		// TODO Auto-generated method stub
		
	}


}
