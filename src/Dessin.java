import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Dessin implements Serializable {
	
	public HashSet<Figure> figures = new HashSet<Figure>();
	
	public Dessin(){
	}
	
	public Collection<Point> getPointsExtremes(){
		Collection<Point> extremes = new HashSet<Point>();
		getFigures().stream()
					.forEach(f -> extremes.addAll(f.getPointsExtremes()));
		return extremes;
	}
	
	public boolean add(Figure fig){
		return figures.add(fig);		
	}
	
	public Collection<Figure> getFigures(){
		return figures;
	}
	
	public int size(){
		return figures.size();
	}
}
