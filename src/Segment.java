import java.util.ArrayList;
import java.util.Collection;

public class Segment extends Figure {
		
	private Point point_debut ;
	private Point point_fin;
	private boolean hor;
	
	public Segment(Point p, int lng, boolean hori){
		point_debut = p;
		if(hori){
			point_fin = new Point(p.getX()+lng,p.getY());
		}
		else{
			point_fin = new Point(p.getX(),p.getY()+lng);
		}
		hor = hori;
		couleur = Couleur.getCouleurDefaut();
	}
	
	public Segment(Point p, int lng, boolean hori, Couleur c){
		point_debut = p;
		if(hori){
			point_fin = new Point(p.getX()+lng,p.getY());
		}
		else{
			point_fin = new Point(p.getX(),p.getY()+lng);
		}
		hor = hori;
		couleur = c;
	}
	
	@Override
	public Point getCentre() {
		if(hor){
			return new Point((point_debut.getX()+point_fin.getX())/2,point_debut.getY());
		}
		else{
			return new Point(point_debut.getX(),(point_debut.getY()+point_fin.getY())/2);
		}
	}

	@Override
	public String toString() {
		return new String("\n[Segment("+point_debut.getX()+";"+point_debut.getY()
			+") à ("+point_fin.getX()+";"+point_fin.getY()+")"
			+" Couleur : "+getCouleur()+"]\n");	
	}
	
	public void affiche(){
		System.out.println(toString());
	}
	
	public boolean equals(Object o){
		if(o instanceof Segment){
			Segment s = (Segment) o;
			return (
						(
								this.point_debut.equals(s.point_debut)
						)
						&&
						(
								this.point_fin.equals(s.point_fin)
						)
						&&
						(
								this.getCouleur().equals(s.getCouleur())		
						)
					)
					||
					(
						(
								this.point_debut.equals(s.point_fin)
						)
						&&
						(
								this.point_fin.equals(s.point_debut)
						)
						&&
						(
								this.getCouleur().equals(s.getCouleur())		
						)
					);
		} else {
			return false;
		}
	}
	
	@Override
	public Collection<Point> getPoints() {
		ArrayList<Point> p = new ArrayList<>();
		p.add(point_debut);
		p.add(point_fin);
		return p;
	}
	
	public boolean couvre(Point p){
		if( p.getX() >= point_debut.getX()
				&& p.getX() <= point_fin.getX()
				&& p.getY() >= point_debut.getY()
				&& p.getY() <= point_fin.getY()){
			return true;
		}
		return false;
	}

	@Override
	protected String getType() {
		return "SEGMENT ";
	}
}
