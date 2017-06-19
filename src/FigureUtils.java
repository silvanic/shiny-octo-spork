import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class FigureUtils {
	public static Random rand = new Random();
	
	private static final Map<String, Figure> ids = new HashMap<String, Figure>();
	
	public static Rond getRandomRond(){
		Rond rond =new Rond(getRandomPoint(), getRandomInt());
		ids.put(rond.getId(), rond);		
		return rond;
	}
	
	public static Rectangle getRandomRectangle(){
		Rectangle rect = new Rectangle(getRandomPoint(), getRandomInt() , getRandomInt());
		ids.put(rect.getId(), rect);
		return rect;
	}
	
	public static Carre getRandomCarre(){
		Carre carre = new Carre(getRandomPoint(), getRandomInt());
		ids.put(carre.getId(), carre);
		return carre;
	}
	
	public static Segment getRandomSegment(){
		Segment segment= new Segment(getRandomPoint(), getRandomInt(), rand.nextBoolean());
		ids.put(segment.getId(), segment);
		return segment;
	}	
	
	public static Point getRandomPoint(){
		return new Point(getRandomInt(), getRandomInt());
	}
	
	public static int getRandomInt(){
		return rand.nextInt(100);
	}
	
	public static Figure getRandomFigure(){
		int i = rand.nextInt(4);
		switch (i) {
			case 0:
				return getRandomRond();
			case 1:
				return getRandomRectangle();
			case 2:
				return getRandomCarre();
			case 3:
				return getRandomSegment();
			default:
				System.out.print("Et c'est reparti!");
				return getRandomFigure();
		}
	}
	
	public static Surfacable getRandomSurfacable(){
		int i = rand.nextInt(3);
		switch (i) {
			case 0:
				return getRandomRond();
			case 1:
				return getRandomRectangle();
			case 2:
				return getRandomCarre();
			default:
				System.out.print("Et c'est reparti!");
				return getRandomSurfacable();
		}
	}
	
	public static Collection<Point> getPoints(Figure... aFig){
		HashSet<Point> p = new HashSet<Point>();		
		for (Figure figure : aFig) {
			for (Point point : figure.getPoints()) {
				p.add(point);
			}			
		}
		return p;
	}
	
    public static Collection<Figure> genere(int size){
    	HashSet<Figure> array = new HashSet<Figure>();
    	for (int i = 0; i < size; i++) {
			array.add(getRandomFigure());
		}
    	return array;
    }
    
    public static Figure getFigureEn(Point p, Dessin dessin){
    	Iterator<Figure> iter = dessin.getFigures().iterator();
    	while(iter.hasNext()){
    		Figure figure = iter.next();
    		if(figure!=null){
        		if(figure.couvre(p)){
        			return figure;
        		}       			
    		} 		
    	}
    	return null;
    }
    
    public static Collection<Figure> trieProcheOrigine(Dessin dessin){
//    	List<Figure> list = new ArrayList<>(dessin.figures);
//    	Collections.sort(list);
//    	return list;
    	return dessin.getFigures().stream()
        		.sorted()
        		.collect(Collectors.toList());
        	
    }

    public static List<Surfacable> trieDominant(Dessin dessin){
//    	List<Figure> list = new ArrayList<>();
//    	for (Figure figure : dessin.figures) {
//			if(figure instanceof Surfacable){
//				list.add(figure);
//			}
//		}
//    	
//    	Collections.sort(list, new Comparator<Figure>() {
//			public int compare(Figure surf1, Figure surf2) {
//			
//				double x1 = ((Surfacable) surf1).surface();
//				
//				double x2 = ((Surfacable) surf2).surface();
//				
//				return (int) (x2 - x1);
//			
//				}
//			}
//    	);
//    	return list;    	
    	return dessin.getFigures().stream()
    			.filter(f -> f instanceof Surfacable)
    			.map(x -> (Surfacable) x)
    			.sorted( (f1,f2) -> f1.surface() > f2.surface() ? -1 : 1)
    			.collect(Collectors.toList());

    }
    
    public static Figure get(String id){
    	if (ids.get(id)!=null){
            return ids.get(id);
    	}
    	return null;
    }
    
    public static Map<String, Figure> getFigureUtils(){
    	return ids;
    }
    
    public static void afficheTout(){
		for (Figure figure : getFigureUtils().values()) {
			figure.affiche();
			System.out.println(figure.getId()+"\n");
		}
    }
}
