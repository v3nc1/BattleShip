package GUI;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Sucelje koje sadrzi metodu primanja dogadjaja kod ispisa rezultata
 */
public interface ResultsListener {
	
	/*
	 * Metoda koja se koristi kod dohvacanja podataka o zavrsenoj igri
	 * 
	 * @param
	 * 		Objekt tipa ResultEvent koji sadrzi podatke o odigranoj igri
	 */
	public void gameData(ResultEvent re);

}
