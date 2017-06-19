
public class Carre extends Rectangle{
	
	public Carre(Point point, int cote){
		super(point, cote, cote);		
	}
	
	protected String getType(){
		return "Carré ";
	}
}
