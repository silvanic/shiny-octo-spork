import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FigureUtils {
	private static final int SIZE_MIN = 1;
	private static final int SIZE_MAX = 25;
	
	private static int X_MIN = -375;
	private static int X_MAX = 375;
	
	private static int Y_MIN = -375;
	private static int Y_MAX = 375;
	
	public static Random rand = new Random();
	
	private static final Map<String, Figure> ids = new HashMap<String, Figure>();
	
	public static Rond getRandomRond(){
		Rond rond =new Rond(getRandomPoint(), getRandomInt(SIZE_MIN, SIZE_MAX), getRandomCouleur());
		ids.put(rond.getId(), rond);		
		return rond;
	}
	
	public static Rectangle getRandomRectangle(){
		Rectangle rect = new Rectangle(getRandomPoint(), getRandomInt(SIZE_MIN, SIZE_MAX) , getRandomInt(SIZE_MIN, SIZE_MAX), getRandomCouleur());
		ids.put(rect.getId(), rect);
		return rect;
	}
	
	public static Carre getRandomCarre(){
		Carre carre = new Carre(getRandomPoint(), getRandomInt(SIZE_MIN, SIZE_MAX), getRandomCouleur());
		ids.put(carre.getId(), carre);
		return carre;
	}
	
	public static Segment getRandomSegment(){
		Segment segment= new Segment(getRandomPoint(), getRandomInt(SIZE_MIN, SIZE_MAX), rand.nextBoolean(), getRandomCouleur());
		ids.put(segment.getId(), segment);
		return segment;
	}	
	
	public static Point getRandomPoint(){
		return new Point(getRandomInt(X_MIN, X_MAX), getRandomInt(Y_MIN, Y_MAX));
	}
	
	public static int getRandomInt(int min, int max){		
		return rand.nextInt(max-min)+min;
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
	
	public static Couleur getRandomCouleur(){
		return Couleur.values()[rand.nextInt(5)];
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
    
    public static Optional<Figure> getFigureEn(Point p, Dessin dessin){
    	return dessin.getFigures().stream()
    			.filter(f -> f.couvre(p))
    			.findFirst();
    }
    
    public static Collection<Figure> trieProcheOrigine(Dessin dessin){
    	return dessin.getFigures().stream()
        		.sorted()
        		.collect(Collectors.toList());
        	
    }

    public static List<Surfacable> trieDominant(Dessin dessin){	
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
    
    public static void imprime(Dessin dessin) throws IOException, ImpressionHorsLimiteException{
		
		Predicate<Point> xTropPetit = p -> p.getX() < X_MIN;
		Predicate<Point> yTropPetit = p -> p.getY() < Y_MIN;
		Predicate<Point> xTropGrand = p -> p.getX() > X_MAX;
		Predicate<Point> yTropGrand = p -> p.getY() > Y_MAX;
		
		Optional<Point> mauvais = dessin.getPointsExtremes()
									.stream()
									.filter(xTropPetit
											.or(yTropPetit)
											.or(xTropGrand)
											.or(yTropGrand))
									.findAny();
		
		if(mauvais.isPresent()){
			//throw new ImpressionHorsLimiteException();
		}
		
    	File file = File.createTempFile("monDessin", ".txt");
		PrintWriter sortie = new PrintWriter(new FileOutputStream(file));
		dessin.getFigures().stream()
			.forEach(f->sortie.println(f));
		for (int i = 0; i < 100; i++) {
			sortie.print("=");
		}
		sortie.println();
		for (int i = X_MIN; i < X_MAX; i++) {
			for (int j = Y_MIN; j < Y_MAX; j++) {
				Optional<Figure> figure = getFigureEn(new Point(i, j), dessin);				
				if(figure.isPresent()){
					sortie.print(figure.get().getCouleur().abreviation());
				}
				else{
					sortie.print(" ");
				}
			}
			sortie.println();
		}
		System.out.println("Impression sous " + file.getAbsolutePath());
		sortie.close();
		Desktop.getDesktop().browse(file.toURI());
    }
    
    public static void sauvegarde(Dessin d) throws IOException {
		File file = File.createTempFile("monDessin", ".save");
		ObjectOutputStream sortie = new ObjectOutputStream(new FileOutputStream(file));
		sortie.writeObject(d);
		System.out.println("Sauvegarde sous " + file.getAbsolutePath());
		sortie.close();
	}
	
	public static Dessin charge(String filename) throws IOException, ClassNotFoundException {
		Dessin dessin;
		try {
			ObjectInputStream sortie = new ObjectInputStream(new FileInputStream(filename));
			dessin = (Dessin) sortie.readObject();
			sortie.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fichier non trouvé : " + e.getMessage());
			dessin = new Dessin();
		}
		return dessin;
	}
}
