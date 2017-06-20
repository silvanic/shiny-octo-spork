
public class Carre extends Rectangle{
	
	public Carre(Point point, int cote){
		super(point, cote, cote, Couleur.getCouleurDefaut());		
	}
	
	public Carre(Point point, int cote, Couleur couleur){
		super(point, cote, cote, couleur);		
	}
	
	protected String getType(){
		return "Carré ";
	}
}
