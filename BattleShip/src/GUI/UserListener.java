package GUI;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Sucelje koje sadrzi metode primanja dogadjaja korisnika
 * 
 * 
 */

public interface UserListener {
	
	/*
	 * Metoda koja se koristi kod stvaranja novog korisnika
	 * @param 
	 * 		Objekt tipa NewUserEvent koji sadrzi podatke o korisniku
	 */
	public void NewUserData(NewUserEvent nue);
	
	/*
	 * Metoda koje se koristi kod postavljanja brodova na plocu
	 * @param
	 * 		Objekt tipa DeploymentEvent koji sadrzi podatke o rasporedu brodova
	 */
	
	public void DeploymentData(DeploymentEvent de);
	
	/*
	 * 
	 * Metoda koja se koristi za restartanje slijeda igraca
	 * koji postavljanja brodove na plocu
	 * 
	 * @param
	 * 		Objekt tipa DeploymentEvent koji sadrzi podatak o prekidu
	 */
	
	public void restartDeployment(DeploymentEvent de);
	
	/*
	 * Metoda koja sprema podatke o listi Igraca
	 * 
	 * @param 
	 * 		Objekt tipa PlayerListEvent
	 */
	
	public void playerList(PlayerListEvent ule);
	
	
	
	
}

	
