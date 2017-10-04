package Probeklausur;

public class Hund extends Tier{
	public String getLaut(){
		return "Wuff!";
	}
	
	public static void main(String args[]){
		Tier[] tiere={
				new Hund(),
				new Katze(),
				new Katze("Mauz!"),
				new Katze(2),
				new Katze(new Hund())
		};
		for(int i = 0; i<tiere.length;i++){
			System.out.println(tiere[i].getLaut());
		}
	}
}

abstract class Tier{
	public abstract String getLaut();
}

class Katze extends Tier{
	int anzahl;
	String laut="Miau!";
	
	public Katze(){
		this.laut=laut;
	}
	
	public Katze(String laut){
		this.laut=laut;
	}
	
	public Katze(int anzahl){
		this.anzahl=anzahl;
	}
	
	public Katze(Hund hund){
		this.laut="Wuaaamiauuu...";
	}
	
	public String getLaut(){
		String res=laut;
		for(int i =0; i<anzahl-1;i++){
			res = res+'\n'+laut;
		}
		return res;
	}
}