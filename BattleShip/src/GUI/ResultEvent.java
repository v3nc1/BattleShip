package GUI;

import java.util.EventObject;


/*
 * @author Venci Ivos
 * @since rujan, 2018.
 * 
 * Klasa koja sprema podatke o igri pri dogadjaju zavrsetka igre
 */
public class ResultEvent extends EventObject {
	
		private String p1Name;
		private int p1lvl;
		private double p1Accuracy;
		
		private String p2Name;
		private int p2lvl;
		private double p2Accuracy;
		
		private boolean winerFlag;
	

	public ResultEvent(Object ob) {
		super(ob);
		
		
		
	}
	
	public boolean getWinerFlag() {
		return winerFlag;
	}
	
	public void setWinerFlag(boolean winerFlag) {
		this.winerFlag = winerFlag;
	}

	public String getP1Name() {
		return p1Name;
	}

	public void setP1Name(String p1Name) {
		this.p1Name = p1Name;
	}

	public int getP1lvl() {
		return p1lvl;
	}

	public void setP1lvl(int p1lvl) {
		this.p1lvl = p1lvl;
	}

	public double getP1Accuracy() {
		return p1Accuracy;
	}

	public void setP1Accuracy(double p1Accuracy) {
		this.p1Accuracy = p1Accuracy;
	}

	public String getP2Name() {
		return p2Name;
	}

	public void setP2Name(String p2Name) {
		this.p2Name = p2Name;
	}

	public int getP2lvl() {
		return p2lvl;
	}

	public void setP2lvl(int p2lvl) {
		this.p2lvl = p2lvl;
	}

	public double getP2Accuracy() {
		return p2Accuracy;
	}

	public void setP2Accuracy(double p2Accuracy) {
		this.p2Accuracy = p2Accuracy;
	}

}
