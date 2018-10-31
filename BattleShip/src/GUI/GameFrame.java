package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Controler.Controler;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja pokrece prozor igre
 */
public class GameFrame extends Dialog implements ButtonListener {

	private final int gridSize=8;
	private final int goal = 19;
	private JLabel gameLabel;
	private JLabel backround;
	private JPanel bodyPanel;
	private JPanel midPanel;
	private JPanel bottomPanel;
	private JPanel infoPanel;
	private JLabel lblturnSign;
	private JLabel lblturnNo;
	private JLabel lblResult;
	private Ships legend;
	private String plSign;

	//// Player 1////
	private JPanel player1Panel;
	private BattleField bfPlayer1;
	private JPanel infoPl1Panel;
	private JLabel lblPlayer1;
	private JLabel lblPl1Hits;
	private JLabel lblPl1Demage;
	private JButton btnPl1Surrender;
	private String pl1Name;
	private String pl1Hits;
	private String pl1Demage;

	//// Player 2////
	private JPanel player2Panel;
	private BattleField bfPlayer2;
	private JPanel infoPl2Panel;
	private JLabel lblPlayer2;
	private JLabel lblPl2Hits;
	private JLabel lblPl2Demage;
	private JButton btnPl2Surrender;
	private String pl2Name;
	private String pl2Hits;
	private String pl2Demage;

	private final int gameName;
	private Controler cntr;
	private GameResults gameRes;
	private ResultsListener resListner;
	private UserListener userLstn;
	private ResultEvent re;
	
	/*
	 * Konstruktor klase
	 * @param frame Objekt tipa JFrame
	 * @param cntr Objekt tipa Controler
	 * @paarm gameName parametar tipa int je ime igre
	 * 
	 */

	public GameFrame(Frame frame, int gameName) {
		super(frame, "Game", null);

		
		this.gameName = gameName;
		
		setVisible(true);
		setSize(1024, 950);
		setResizable(false);
		setLocationRelativeTo(null);
		runCreate();
		runLayout();
		runAction();

	}
	/*
	 * Metoda koja postavlja ActionListenere na objekte prozora
	 */
	private void runAction() {

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Youre progress will be lost!", "Are you sure?", JOptionPane.OK_CANCEL_OPTION);
				
				DeploymentEvent de = new DeploymentEvent(this);

				userLstn.restartDeployment(de);

				dispose();

			}

			public void windowClosed(WindowEvent arg0) {
				
				
				DeploymentEvent de = new DeploymentEvent(this);

				userLstn.restartDeployment(de);

				dispose();

			}
		});

		btnPl1Surrender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				player2Won();

			}
		});
		btnPl2Surrender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				player1Won();

			}
		});

	}

	private void setListener(ResultsListener rl) {
		
		resListner = rl;
	}
	public void setListener(UserListener ul) {

		userLstn = ul;
	}
	/*
	 * Metoda koja na kraju igre otvara 
	 * neotkrivena polja
	 */
	private void openFields() {
		int[] temp = new int[2];

		bfPlayer1.setEnabled(true);
		bfPlayer2.setEnabled(true);

		for (int x = 0; x < gridSize; x++) {
			for (int y = 0; y < gridSize; y++) {

				temp[0] = x;
				temp[1] = y;
				if (cntr.getPl1Field(cntr.getGame(gameName), temp)) {

					bfPlayer2.setButtonHit(temp);

				} else {

					bfPlayer2.setButtonMiss(temp);
				}

				if (cntr.getPl2Field(cntr.getGame(gameName), temp)) {

					bfPlayer1.setButtonHit(temp);

				} else {

					bfPlayer1.setButtonMiss(temp);
				}

			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see GUI.ButtonListener#buttonClicked(GUI.ButtonEvent)
	 * Metoda koja sprema podatke o igri
	 */
	@Override
	public void buttonClicked(ButtonEvent be) {

		if (cntr.getTurn(cntr.getGame(gameName)) % 2 == 0) {

			if (cntr.getPl1Field(cntr.getGame(gameName), be.getXY())) {
				bfPlayer2.setButtonHit(be.getXY());
				cntr.doTurn(cntr.getGame(gameName));
				cntr.setPlayerHit(cntr.getGame(gameName), false);
				cntr.setPlayerDemage(cntr.getGame(gameName), true);
				lblPl2Hits.setText("Hits: " + cntr.getPlayerHit(cntr.getGame(gameName), false));
				lblPl1Demage.setText("Demage taken: " + cntr.getPlayerDemage(cntr.getGame(gameName), true));
				cntr.setPlayerShots(cntr.getGame(gameName), false);
				lblResult.setText("HIT");

			} else {
				bfPlayer2.setButtonMiss(be.getXY());
				cntr.doTurn(cntr.getGame(gameName));
				cntr.setPlayerShots(cntr.getGame(gameName), false);
				lblResult.setText("MISS");
			}
			bfPlayer1.unlockButtons();
			bfPlayer2.lockButtons();
			lblturnSign.setText("<");
		} else {

			if (cntr.getPl2Field(cntr.getGame(gameName), be.getXY())) {
				bfPlayer1.setButtonHit(be.getXY());
				cntr.doTurn(cntr.getGame(gameName));
				cntr.setPlayerShots(cntr.getGame(gameName), true);
				cntr.setPlayerHit(cntr.getGame(gameName), true);
				cntr.setPlayerDemage(cntr.getGame(gameName), false);
				lblPl1Hits.setText("Hits: " + cntr.getPlayerHit(cntr.getGame(gameName), true));
				lblPl2Demage.setText("Demage taken: " + cntr.getPlayerDemage(cntr.getGame(gameName), false));

				lblResult.setText("HIT");
			} else {
				bfPlayer1.setButtonMiss(be.getXY());
				cntr.doTurn(cntr.getGame(gameName));
				cntr.setPlayerShots(cntr.getGame(gameName), true);
				

				lblResult.setText("MISS");
			}
			lblturnSign.setText("  >");
			bfPlayer1.lockButtons();
			bfPlayer2.unlockButtons();
		}

		if (cntr.getPlayerHit(cntr.getGame(gameName), true) == goal) {

			player1Won();

		} else if (cntr.getPlayerHit(cntr.getGame(gameName), false) == goal) {

			player2Won();
		}

		lblturnNo.setText("Turn No.: " + cntr.getTurn(cntr.getGame(gameName)));

	}
	
	/*
	 * Metoda koja se pokrece ako je pobjedio igrac 1
	 */

	private void player1Won() {
		
		String pl1Name;
		int pl1Hits;
		double pl1Acc;
		
		String pl2Name;
		int pl2Hits;
		double pl2Acc;


		pl1Name=cntr.getPlayerName(cntr.getP1(cntr.getGame(gameName)));
		pl1Hits=cntr.getPlayerLvl(cntr.getP1(cntr.getGame(gameName)));
		pl1Acc=cntr.gameAccuracy(cntr.getPlayerShots(cntr.getGame(gameName), true),
				cntr.getPlayerHit(cntr.getGame(gameName), true));
		
		pl2Name=cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)));
		pl2Hits=cntr.getPlayer(cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)))).getLvl();
		pl2Acc=cntr.gameAccuracy(cntr.getPlayerShots(cntr.getGame(gameName), false),
				cntr.getPlayerHit(cntr.getGame(gameName), false));


		re = new ResultEvent(this);

		cntr.setShots(cntr.getPlayer(cntr.getPlayerName(cntr.getP1(cntr.getGame(gameName)))),
				cntr.getPlayerShots(cntr.getGame(gameName), true),
				cntr.getPlayer(cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)))),
				cntr.getPlayerShots(cntr.getGame(gameName), false));
		cntr.setHit(cntr.getP1(cntr.getGame(gameName)), cntr.getPlayerHit(cntr.getGame(gameName), true),
				cntr.getP2(cntr.getGame(gameName)), cntr.getPlayerHit(cntr.getGame(gameName), false));
		cntr.gameWon(cntr.getP1(cntr.getGame(gameName)), cntr.getP2(cntr.getGame(gameName)));

		re.setP1Name(pl1Name);
		re.setP1lvl(pl1Hits);
		re.setP1Accuracy(pl1Acc);

		
		re.setP2Name(pl2Name);
		re.setP2lvl(pl2Hits);
		re.setP2Accuracy(pl2Acc);
		
	
		re.setWinerFlag(true);

		gameRes.gameData(re);

		openFields();

		JOptionPane.showMessageDialog(null, "GAME OVER", "END", JOptionPane.PLAIN_MESSAGE);

		gameRes.setVisible(true);
		
		cntr.saveUsers();
		dispose();

	}
	
	/*
	 * Metoda koja se pokrece ako je pobjedio igrac 2
	 */
	private void player2Won() {

		re = new ResultEvent(this);

		cntr.setShots(cntr.getPlayer(cntr.getPlayerName(cntr.getP1(cntr.getGame(gameName)))),
				cntr.getPlayerShots(cntr.getGame(gameName), true),
				cntr.getPlayer(cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)))),
				cntr.getPlayerShots(cntr.getGame(gameName), false));
		cntr.setHit(cntr.getP1(cntr.getGame(gameName)), cntr.getPlayerHit(cntr.getGame(gameName), true),
				cntr.getP2(cntr.getGame(gameName)), cntr.getPlayerHit(cntr.getGame(gameName), false));
		cntr.gameWon(cntr.getP2(cntr.getGame(gameName)), cntr.getP1(cntr.getGame(gameName)));

		re.setP1Name(cntr.getPlayerName(cntr.getP1(cntr.getGame(gameName))));
		re.setP1lvl(cntr.getPlayerLvl(cntr.getP1(cntr.getGame(gameName))));
		re.setP1Accuracy(cntr.gameAccuracy(cntr.getPlayerShots(cntr.getGame(gameName), true),
				cntr.getPlayerHit(cntr.getGame(gameName), true)));

		re.setP2Name(cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName))));
		re.setP2lvl(cntr.getPlayer(cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)))).getLvl());
		re.setP2Accuracy(cntr.gameAccuracy(cntr.getPlayerShots(cntr.getGame(gameName), false),
				cntr.getPlayerHit(cntr.getGame(gameName), false)));
		re.setWinerFlag(false);

		gameRes.gameData(re);

		openFields();
		JOptionPane.showMessageDialog(null, "GAME OVER", "END", JOptionPane.PLAIN_MESSAGE);
		gameRes.setVisible(true);
		cntr.saveUsers();
		dispose();
	}
	/*
	 * Metoda koja inicijalizira objekte klase
	 */

	private void runCreate() {

		cntr=new Controler();
		backround = new JLabel(new ImageIcon(".\\TitleImage.jpg"));
		bodyPanel = new JPanel();
		midPanel = new JPanel();
		bottomPanel = new JPanel();
		gameRes = new GameResults(new JFrame());
		gameRes.setVisible(false);

		plSign = "  <";
		gameLabel = new JLabel("BATTLESHIP", JLabel.CENTER);

		gameLabel.setFont(new Font("Arial", Font.BOLD, 50));
		gameLabel.setOpaque(true);
		gameLabel.setBackground(new Color(0, 0, 0));
		gameLabel.setForeground(Color.gray);
		infoPanel = new JPanel();
		lblturnSign = new JLabel(plSign, JLabel.CENTER);
		lblturnSign.setFont(new Font("Arial", Font.BOLD, 50));
		lblturnNo = new JLabel("Turn No.: " + cntr.getTurn(cntr.getGame(gameName)), JLabel.CENTER);
		lblturnNo.setFont(new Font("Arial", Font.BOLD, 18));
		lblResult = new JLabel("", JLabel.CENTER);
		lblResult.setFont(new Font("Arial", Font.BOLD, 24));
		legend = new Ships();

		//// Player 1////
		pl1Name = cntr.getPlayerName(cntr.getP1(cntr.getGame(gameName)));

		pl1Hits = "" + cntr.getPlayerHit(cntr.getGame(gameName), true);
		pl1Demage = "" + cntr.getPlayerDemage(cntr.getGame(gameName), true);
		player1Panel = new JPanel();
		bfPlayer1 = new BattleField(new JPanel(),"Player 1");
		bfPlayer1.setListener(this);
		infoPl1Panel = new JPanel();
		lblPlayer1 = new JLabel("Player: " + pl1Name);
		lblPlayer1.setFont(new Font("Arial", Font.BOLD, 18));
		lblPl1Hits = new JLabel("Hits: " + pl1Hits);
		lblPl1Hits.setFont(new Font("Arial", Font.BOLD, 18));
		lblPl1Demage = new JLabel("Demage taken: " + pl1Demage);
		lblPl1Demage.setFont(new Font("Arial", Font.BOLD, 18));
		btnPl1Surrender = new JButton("SURRENDER");
		//// Player 2////
		pl2Name = cntr.getPlayerName(cntr.getP2(cntr.getGame(gameName)));
		pl2Hits = "" + cntr.getPlayerHit(cntr.getGame(gameName), false);
		pl2Demage = "" + cntr.getPlayerDemage(cntr.getGame(gameName), false);
		player2Panel = new JPanel();
		bfPlayer2 = new BattleField(new JPanel(),"Player 2");
		bfPlayer2.setListener(this);
		bfPlayer2.lockButtons();
		infoPl2Panel = new JPanel();
		lblPlayer2 = new JLabel("Player: " + pl2Name);
		lblPlayer2.setFont(new Font("Arial", Font.BOLD, 18));
		lblPl2Hits = new JLabel("Hits: " + pl2Hits);
		lblPl2Hits.setFont(new Font("Arial", Font.BOLD, 18));
		lblPl2Demage = new JLabel("Demage taken: " + pl2Demage);
		lblPl2Demage.setFont(new Font("Arial", Font.BOLD, 18));
		btnPl2Surrender = new JButton("SURRENDER");

	}
	
	/*
	 * Metoda koja odredjuje raspored objekata prozora
	 */
	private void runLayout() {

		GridBagConstraints gc = new GridBagConstraints();
		setLayout(new BorderLayout());

		
		add(gameLabel, BorderLayout.NORTH);

		backround.setLayout(new BorderLayout());
		bodyPanel.setLayout(new BorderLayout());
		midPanel.setLayout(new FlowLayout());
		bottomPanel.setLayout(new GridBagLayout());
		player1Panel.setLayout(new GridBagLayout());
		player2Panel.setLayout(new GridBagLayout());
		infoPl1Panel.setLayout(new GridBagLayout());
		infoPl2Panel.setLayout(new GridBagLayout());
		infoPanel.setLayout(new GridBagLayout());

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.ipadx = 10;
		gc.anchor = gc.LINE_START;
		gc.insets = new Insets(10, 10, 10, 10);
		infoPl1Panel.add(lblPlayer1, gc);
		gc.gridy = 1;
		infoPl1Panel.add(lblPl1Hits, gc);
		gc.gridy = 2;
		infoPl1Panel.add(lblPl1Demage, gc);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.anchor = gc.LINE_START;
		player1Panel.add(infoPl1Panel, gc);
		gc.gridy = 1;
		gc.fill = gc.NONE;
		player1Panel.add(btnPl1Surrender, gc);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10, 10, 10, 10);

		infoPl2Panel.add(lblPlayer2, gc);
		gc.gridy = 1;

		infoPl2Panel.add(lblPl2Hits, gc);
		gc.gridy = 2;

		infoPl2Panel.add(lblPl2Demage, gc);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.anchor = gc.LINE_START;
		player2Panel.add(infoPl2Panel, gc);
		gc.gridy = 1;
		player2Panel.add(btnPl2Surrender, gc);

		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10, 10, 10, 10);
		gc.anchor = gc.CENTER;
		infoPanel.add(lblturnSign, gc);
		gc.gridy = 1;
		infoPanel.add(lblturnNo, gc);
		gc.gridy = 2;
		infoPanel.add(lblResult, gc);

		midPanel.add(bfPlayer1);
		midPanel.add(infoPanel);
		midPanel.add(bfPlayer2);

		player1Panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Player info"),
				BorderFactory.createRaisedSoftBevelBorder()));
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = gc.BOTH;
		gc.anchor = gc.LAST_LINE_START;
		bottomPanel.add(player1Panel, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.weighty = 1.0;
		gc.fill = gc.BOTH;
		bottomPanel.add(legend, gc);

		player2Panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Player info"),
				BorderFactory.createRaisedSoftBevelBorder()));

		gc.gridx = 2;
		gc.gridy = 0;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = gc.BOTH;
		gc.anchor = gc.LAST_LINE_END;
		bottomPanel.add(player2Panel, gc);

		bodyPanel.add(midPanel, BorderLayout.CENTER);
		bodyPanel.add(bottomPanel, BorderLayout.SOUTH);

		add(bodyPanel, BorderLayout.SOUTH);

	}

}
