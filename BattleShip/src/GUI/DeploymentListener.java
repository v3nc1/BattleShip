package GUI;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Sucelje koje sadrzi metodu o statusu rasporeda brodova 
 * na ploci za igraca koji je postavljao 
 */

public interface DeploymentListener {
	
	/*
	 * @param flag
	 * 		Tipa boolean koji odredjuje igraca 1 ili 2
	 */
	public void deploymentStatus(boolean flag);

}
