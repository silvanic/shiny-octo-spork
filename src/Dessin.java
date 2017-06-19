import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Dessin {
	
	public HashSet<Figure> figures = new HashSet<Figure>();
	
	public Dessin(){
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
