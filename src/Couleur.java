
public enum Couleur {
	Rouge("R"), Vert("V"), Bleu("B"), Jaune("J"), Noir("N");
	
	public static Couleur getCouleurDefaut(){
		return Noir;
	}
	
	private Couleur(String a){
		abrege = a;
	}
	
	public String abreviation(){
		return abrege;
	}
	private String abrege;
	
}
