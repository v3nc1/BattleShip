package GUI;

import java.util.EventObject;

/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja sprema podatke pri dogadjaju kreiranju korisnika
 * 
 * 
 */

public class NewUserEvent extends EventObject {
	
	private String name;
	
	public NewUserEvent(Object ob) {
		
		super(ob);
		
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
	}
	
	

}
