package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja kreira prozor za raspored flote za igru
 * 
 */
public class DeployFleet extends JFrame implements DeploymentListener {

	private final int gridSize = 8;
	private final int deployCount = 19;
	private String plName;
	private JLabel deployLabel;
	private DeploymentBattleField btField;
	private JPanel deployStatPanel;
	private JButton btnDeployStatus;
	private Ships shipLabel;
	private JPanel panel;
	private JButton btnDeploy;
	private JButton btnReset;
	private UserListener nul;
	/*
	 * Konstruktor klase
	 * 		@param name
	 * 			Objekt tipa String je ime igraca
	 */
	public DeployFleet(String name) {

		plName = name;
		setVisible(true);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		createRun();
		layoutRun();
		runAction();

	}
	/*
	 * Metoda koja odredjuje ActionListenere za komponente prozora
	 * 
	 */
	private void runAction() {

		btnDeploy.addActionListener(new ActionListener() {
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * 
			 * Metoda se koristi za spremanje dogadjaja o rasporedu brodova
			 * i provjerava dali je tocan broj polja zauzet
			 * 
			 * @throws ExecutionException
			 */
			@Override
			public void actionPerformed(ActionEvent e) {

				DeploymentEvent de = new DeploymentEvent(this);
				try {
					int br = 0;
					for (int x = 0; x < gridSize; x++) {

						for (int y = 0; y < gridSize; y++) {

							if (btField.getButton(x, y)) {

								de.setPlayerField(x, y, true);
								br++;
							}

						}

					}
					if (br < deployCount || br > deployCount) {
						throw new ExecutionException(null);
					}
					de.setPlayer(plName);
					nul.DeploymentData(de);

					dispose();
				} catch (ExecutionException error) {

					JOptionPane.showMessageDialog(null, "Niste unili ispravan broj polja", "Greška kod unosa",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		btnReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				btField.resetButtons();

			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 * 
			 * Metoda se koristi za resetiranje slijeda igraca koji postavlja brodove
			 */
			@Override
			public void windowClosing(WindowEvent e) {

				DeploymentEvent de = new DeploymentEvent(this);

				nul.restartDeployment(de);

			}
		});

	}

	public void setListener(UserListener nul) {

		this.nul = nul;
	}
	/*
	 * Metoda koja inicijalizira objekte klase
	 */
	private void createRun() {

		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		deployLabel = new JLabel("Deploy ships " + plName);
		deployLabel.setFont(new Font("Arial", Font.BOLD, 30));
		deployLabel.setBackground(Color.BLACK);
		deployLabel.setForeground(Color.GRAY);
		btnDeploy = new JButton("DEPLOY");
		btnReset = new JButton("RESET");
		deployStatPanel=new JPanel();
		deployStatPanel.setBackground(Color.BLACK);
		btnDeployStatus = new JButton();
		btnDeployStatus.setBackground(Color.RED);
		btnDeployStatus.setEnabled(false);
		btField = new DeploymentBattleField();
		btField.setListener(this);
		shipLabel = new Ships();

	}
	/*
	 * Metoda koja odredjuje raspored objekata prozora
	 */
	private void layoutRun() {

		setLayout(new BorderLayout());
		panel.setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;

		panel.add(deployLabel, gc);

		gc.gridx = 1;
		gc.anchor = gc.CENTER;
		gc.ipadx = 20;
		gc.fill = gc.NONE;
		gc.insets = new Insets(20, 0, 0, 0);

		panel.add(btnReset, gc);

		borderRun();
		
		deployStatPanel.setLayout(new BorderLayout());
		deployStatPanel.add(btnDeployStatus);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridheight = 1;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = gc.BOTH;
		panel.add(deployStatPanel, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = gc.CENTER;
		gc.ipadx = 20;
		gc.fill = gc.NONE;
		gc.insets = new Insets(20, 0, 0, 0);

		panel.add(btnDeploy, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridheight = 5;
		gc.weightx = 1.0;
		gc.weighty = 1.0;
		gc.fill = gc.BOTH;
		gc.insets=new Insets(0, 10, 20, 0);

		panel.add(btField, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.gridheight = 1;
		gc.weightx = 0.5;
		gc.weighty = 1;
		gc.fill = gc.NONE;
		panel.add(shipLabel, gc);
		

		add(panel);

	}
	/*
	 * Metoda kreira okvir objekta
	 */
	private void borderRun() {

		TitledBorder top = BorderFactory.createTitledBorder("Deployment status:");
		Border bott = BorderFactory.createEmptyBorder(10, 10, 10, 10);

		top.setTitleColor(Color.GRAY);
		top.setTitleFont(new Font("Arial", Font.BOLD, 20));

		deployStatPanel.setBorder(BorderFactory.createCompoundBorder(top, bott));
	}
	/*
	 * (non-Javadoc)
	 * @see GUI.DeploymentListener#deploymentStatus(boolean)
	 * 
	 */
	@Override
	public void deploymentStatus(boolean flag) {

		if (flag) {
			btnDeployStatus.setBackground(Color.GREEN);
		} else {
			btnDeployStatus.setBackground(Color.RED);

		}

	}

}
