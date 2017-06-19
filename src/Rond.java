import java.util.ArrayList;
import java.util.Collection;

public class Rond extends Figure implements Surfacable{
	private int r;
	private Point centre;
	
	public Rond(Point point, int rayon){
		if(rayon<0){
			rayon = -rayon;
		}
		r = rayon;
		centre = point;
	}
	
	public boolean equals(Object o){
		if(o instanceof Rond){
			Rond rond = (Rond) o;
			return this.getCentre().equals(rond.getCentre())
					&& (this.r == rond.r);
		} else {
			return false;
		}
	}
	
	public String toString(){
		return "[ROND "+centre+" de rayon "+r+" avec une surface de "+ surface()+"]\n";
	}

	@Override
	public Point getCentre() {
		return centre;
	}

	@Override
	public double surface() {
		return Math.PI*r*r;
	}

	@Override
	public Collection<Point> getPoints() {
		ArrayList<Point> pResult = new ArrayList<>();
		pResult.add(getCentre());
		return pResult;		
	}

	@Override
	public int hashCode(){
		int code = 0;
		for (Point point : getPoints()) {
			code += point.hashCode();
		}
		code=code*r;
		return code;
	}
	
	public boolean couvre(Point p) {
		int x2 = (p.getX() - centre.getX()) * (p.getX() - centre.getX());
		int y2 = (p.getY() - centre.getY()) * (p.getY() - centre.getY());
		int d = (int) Math.round(Math.sqrt( x2 + y2 ));
		return d <= r;
	}

	@Override
	protected String getType() {
		return "ROND ";
	}
	
}