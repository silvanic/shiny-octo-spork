import java.util.ArrayList;
import java.util.Collection;

public class Rectangle extends Figure implements Surfacable {
	private Point pHG;
	private Point pHD;
	private Point pBG;
	private Point pBD;

	public boolean equals(Object o){
		if(o instanceof Rectangle){
			Rectangle r = (Rectangle) o;
			return (this.getPointBasDroite().equals(r.getPointBasDroite()))
					&&(this.getPointHautGauche().equals(r.getPointHautGauche()))
					&&(this.getCouleur().equals(r.getCouleur()));
		} else {
			return false;
		}
	}
	
	public Rectangle(Point point, int haut, int larg){
//		pHG = point;
//		pBG = new Point(pHG.getX(),pHG.getY()-haut);
//		pHD = new Point(pHG.getX()+larg,pHG.getY());
//		pBD = new Point(pHD.getX(),pBG.getY());
		this(point, haut, larg, Couleur.getCouleurDefaut());
	}
	
	public Rectangle(Point point, int haut, int larg, Couleur c){
		super(c);
		pHG = point;
		pBG = new Point(pHG.getX(),pHG.getY()-haut);
		pHD = new Point(pHG.getX()+larg,pHG.getY());
		pBD = new Point(pHD.getX(),pBG.getY());
	}

	public Point getPointHautGauche(){		
		return pHG;
	}
	
	public Point getPointBasGauche(){		
		return pBG;
	}
	
	public Point getPointBasDroite(){		
		return pBD ;
	}
	
	public Point getPointHautDroite(){		
		return pHD ;
	}
	
	public String toString(){
		return "["+ getType() +"\nHG "
			+getPointHautGauche()
			+"HD "+getPointHautDroite()
			+"\nBG "+getPointBasGauche()
			+"BD "+getPointBasDroite()
			+"\nSurface : "+surface()+ "\nCouleur : "+getCouleur()+" ]\n";
	}
	
	protected String getType(){
		return "Rectangle ";
	}

	@Override
	public Point getCentre() {
		int x = (getPointHautGauche().getX()+getPointHautDroite().getX())/2;
		int y = (getPointHautGauche().getY()+getPointBasGauche().getY())/2;
		return new Point(x,y);
	}

	@Override
	public double surface() {
		return ((getPointHautDroite().getX()-getPointHautGauche().getX())*(getPointHautGauche().getY()-getPointBasGauche().getY()));
	}

	@Override
	public Collection<Point> getPoints() {
		ArrayList<Point> p = new ArrayList<>();
		p.add(getPointBasDroite()); 
		p.add(getPointBasGauche());
		p.add(getPointHautDroite());
		p.add(getPointHautGauche());
		return p;
	}
	
	public boolean couvre(Point p){
		if(p.getX()>=pHG.getX()
				&&p.getX()<=pHD.getX()
				&&p.getY()<=pHG.getY()
				&&p.getY()>=pBG.getY()){
			return true;
		}
		return false;
	}
	
	public Collection<Point> getPointsExtremes() {
		return getPoints();
	}
}
