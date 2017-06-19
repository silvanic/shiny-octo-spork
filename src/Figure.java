import java.util.Collection;
import java.util.Optional; 

public abstract class Figure  implements Comparable<Figure> {
	
	private static int nextID = 1;
	private final String id = getType() + (nextID++);
	
	public String getId(){
		return id;
	}
	
	protected abstract String getType();

	public int hashCode(){
		int code = 0;
		for (Point point : getPoints()) {
			code += point.hashCode();
		}
		return code;
	}
	
	public abstract Point getCentre();
	
	public void affiche(){
		System.out.println(toString());
	};
	
	public abstract String toString();
	
	public abstract Collection<Point> getPoints();
	
	public void affichePoints(){		
		for (Point point : getPoints()) {
			point.affiche();
		}
	};
	
	public abstract boolean couvre(Point point);
	
//	public double distanceOrigine(){
//		Optional<Double> distance = getPoints().stream()
//				.map(p -> p.distanceOrigine())
//				.max((d1,d2) -> d1>d2 ? -1 : 1 );
//		if(distance.isPresent()){
//			return distance.get();
//		} else {
//			return Double.POSITIVE_INFINITY;
//		}
//	}
	
	public double distanceOrigine(){
		Optional<Double> distance = getPoints().stream()
				.map(p -> p.distanceOrigine())
				.max((d1,d2) -> d1>d2 ? -1 : 1 );
		if(distance.isPresent()){
			return distance.get();
		} else {
			return Double.POSITIVE_INFINITY;
		}
//		Double petit = Double.POSITIVE_INFINITY;
//		for (Point point : getPoints()) {
//			if(point.distanceOrigine()<petit){
//				petit = point.distanceOrigine();
//			}
//		}
//		return petit;
	}
	
	public int compareTo(Figure f){
		if(this.equals(f)){
			return 0;
		}
		double d1 = this.distanceOrigine();
		double d2 = f.distanceOrigine();
		return (d1-d2) > 0 ? -1 : 1;
	}
}
