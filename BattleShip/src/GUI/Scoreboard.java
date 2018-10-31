package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Model.Player;
/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira Dialog okvir s popisom igraca 
 * poredanih po uspjehu
 * 
 */

public class Scoreboard extends JDialog implements UserListener {

	private static final long serialVersionUID = 1L;

	private JPanel scorePanel;
	private JPanel bottomPanel;
	private JList scoreList;
	private DefaultListCellRenderer renderer;
	private DefaultListModel listModel;
	private JScrollPane scroll;
	private JButton btnOk;
	private LinkedHashMap<String, Integer> tempList;
	private ArrayList<String> boardList;

	public Scoreboard() {

		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		runCreate();
		runLayout();
		runAction();

	}

	/*
	 * Metoda koja popunjava model liste
	 */
	private void populateModel() {
		int br = 1;

		for (String p : boardList) {

			listModel.addElement(br + ". " + p);

			br++;
		}

	}

	/*
	 * Metoda koja sortira igrace po pobjedama i dodaje ih u listu
	 */
	private void sortList() {

		tempList.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).forEach((e) -> {

			boardList.add(e.getKey() + "................." + e.getValue());

		});

	}

	/*
	 * Metoda inicijalizira objekte klase
	 */
	private void runCreate() {

		scoreList = new JList<>();
		renderer = (DefaultListCellRenderer) scoreList.getCellRenderer();
		scoreList.setFont(new Font("Verdana", Font.BOLD, 18));
		scoreList.setBackground(Color.BLACK);
		scoreList.setForeground(Color.GRAY);
		scoreList.setEnabled(false);
		listModel = new DefaultListModel<>();
		scoreList.setModel(listModel);
		scroll = new JScrollPane(scoreList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		btnOk = new JButton("OK");
		tempList = new LinkedHashMap<>();
		boardList = new ArrayList<>();
		scorePanel = new JPanel();
		bottomPanel = new JPanel();

	}

	/*
	 * Metoda postavlja izgled panela, objekata i raspored polja
	 * 
	 */
	private void runLayout() {

		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		scoreList.setVisibleRowCount(JList.VERTICAL);

		setLayout(new BorderLayout());

		scorePanel.setLayout(new BorderLayout());
		bottomPanel.setLayout(new GridBagLayout());

		scorePanel.add(scroll);

		bottomPanel.add(btnOk);

		add(scorePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

	}

	/*
	 * Metoda koja postavlja ActionListener na objekt prozora
	 */
	private void runAction() {

		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GUI.UserListener#playerList(GUI.PlayerListEvent)
	 * 
	 * Metoda koja puni listu s podacima ime igraca i pobjede
	 */
	@Override
	public void playerList(PlayerListEvent ule) {

		for (Player p : ule.getPlayerList()) {

			tempList.put(p.getPlayerName(), p.getGameWon());

		}

		sortList();
		populateModel();

	}

	@Override
	public void NewUserData(NewUserEvent nue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DeploymentData(DeploymentEvent de) {
		// TODO Auto-generated method stub

	}

	@Override
	public void restartDeployment(DeploymentEvent de) {
		// TODO Auto-generated method stub

	}

}
