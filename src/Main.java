import java.util.HashSet;

import javax.swing.JOptionPane;

public class Main {
	static boolean start;
	static long time;
	
	public static void main(String[] args){
		go();
		go("Créer des points");
		Point a = new Point(0,0);
		Point b = new Point(3,0);
		Point c = new Point(1,1);
		
		a.affiche(a.toString());
		b.affiche(b.toString());
		c.affiche(c.toString());
		
		//Initialise un rond
		go("Initialise un rond");
		Rond rond = new Rond(a,3);
		Rond rond2 = new Rond(a,3);
		Rond rond3 = new Rond(a,4);
		rond.affiche();		
		
		//Construis un rectangle
		go("Construis un rectangle");
		Rectangle rect = new Rectangle(a, 4, 2);
		Rectangle rect2 = new Rectangle(a, 4, 2);
		Rectangle rect3 = new Rectangle(a, 10, 7);
		rect.affiche();
		
		//Construis un carré
		go("Construis un carré");
		Carre carre = new Carre(a, 6);
		Carre carre2 = new Carre(a, 6);
		Carre carre3 = new Carre(a, 7);
		carre.affiche();		
		
		//Afficher un rectangle
		go("Affiche un rectangle");
		rect.affiche();
		
		//Construis un segment
		go("Construis un segment");
		Segment segment = new Segment(a, 6,false);
		Segment segment2 = new Segment(a, 6,false);
		Segment segment3 = new Segment(a, 5,true);
		segment.affiche();
		
		//Afficher un carré
		go("Affiche un carré");
		carre.affiche();							
		
		go("Egalité a/b");
		System.out.println(a.equals(b));
		
		go("Egalité a/c");
		System.out.println(a.equals(c));
		
		go("Egalité a/3");
		System.out.println(a.equals(3));
		
		go("Afficher Hashcode de a");
		a.affiche(new Integer(a.hashCode()).toString());
		
		go("Afficher figure aléatoire");
		FigureUtils.getRandomFigure().affiche();
		FigureUtils.getRandomFigure().affiche();
		FigureUtils.getRandomFigure().affiche();
		
		go("Centre rond");
		rond.getCentre().affiche();
		
		go("Surface rond");
		System.out.println(rond.surface());
		
		go("Centre rectangle");
		rect.getCentre().affiche();
		
		go("Surface rectangle");
		System.out.println(rect.surface());
		
		go("Centre carré");
		carre.getCentre().affiche();
		
		go("Surface carré");
		System.out.println(carre.surface());
		
		go("Creation segment");
		Segment seg = new Segment(a, 5, false);
		seg.affiche();
		seg.getCentre().affiche();
		
		go("Creation segment 2");
		Segment seg2 = new Segment(a, 5, true);
		seg2.affiche();
		seg2.getCentre().affiche();
		
		go("Creation segment aléatoire");
		Segment seg3 = FigureUtils.getRandomSegment();
		seg3.affiche();
		seg3.getCentre().affiche();
		
		go("Afficher les points d'un carré");
		carre.affichePoints();
		
		go("Test tableau");
		Figure[] fig = {rond, carre, rect};
		System.out.println("Nombre de points additonné dans rond, carré, rect : " + FigureUtils.getPoints(fig).size());
		System.out.println("Nombre de points additonné dans rond, 2xcarré, rect : " + FigureUtils.getPoints(rond,rect,carre,rond).size());
		
		go("Dessin");
		Dessin dessin = new Dessin();
		dessin.add(rond);
		dessin.add(rect);
		dessin.add(carre);
		dessin.add(rond);
		for (Figure figure : dessin.getFigures()) {
			for (Point point : figure.getPoints()) {
				point.affiche();
			}			
		}
		
		
		go("Dessin aléatoire");		
		int nb =10;
		int index = 0;
		Dessin array = new Dessin();
		array.figures = (HashSet<Figure>) FigureUtils.genere(nb);
//		for (Figure figure : array.getFigures()) {
//			System.out.println("\nFIGURE "+index);
//			figure.affiche();
//			index++;			
//		}				
		System.out.println("Nb de figure dessiné(s) : "+array.size()+"/"+nb+" donc " + (nb-array.size()) +" doublon(s)");
		
		go("Couverture");
		rect.affiche();
		System.out.println("a :"+rect.couvre(a));
		
		carre.affiche();
		System.out.println("c :"+carre.couvre(c));
		
		seg.affiche();
		System.out.println("a :"+seg.couvre(a));
		
		rond.affiche();
		System.out.println("b :"+rond.couvre(b));

		System.out.println(FigureUtils.getFigureEn(a,array));
				
		Point test = FigureUtils.getRandomPoint();
		test.affiche();
		System.out.println(FigureUtils.getFigureEn(test,dessin));
		
		go("Compare rond");
		affiche(rond.equals(rond2));
		affiche(rond.equals(rond3));
		
		go("Compare rectangle");
		affiche(rect.equals(rect2));
		affiche(rect.equals(rect3));
		
		go("Compare segment");
		affiche(segment.equals(segment2));
		affiche(segment.equals(segment3));
		
		go("Compare carré");
		affiche(carre.equals(carre2));
		affiche(carre.equals(carre3));
		
		Dessin dessin4 = new Dessin();
		dessin4.add(rond2);
		dessin4.add(rect3);
		dessin4.add(carre3);
		dessin4.add(rond3);
		
		go("Triage");
		for (Figure figure : dessin4.figures) {
			figure.affiche();
		}
		affiche("     Après     ");
		for (Surfacable figure : FigureUtils.trieDominant(dessin4)) {
			((Figure) figure).affiche();
		}
		
		go("Origine");
		affiche("     Après     ");
		for (Figure figure : FigureUtils.trieProcheOrigine(dessin4)) {
			figure.affiche();
		}
		
		go("Figure Utils");
		FigureUtils.afficheTout();
		
		go("Recherche figure Rond > Rectangle > Carré > Segment");
		affiche(FigureUtils.get("ROND 20"));
		affiche(FigureUtils.get("ROND 21"));
		affiche(FigureUtils.get("Rectangle 20"));
		affiche(FigureUtils.get("Rectangle 21"));
		affiche(FigureUtils.get("Carré 20"));
		affiche(FigureUtils.get("Carré 21"));
		affiche(FigureUtils.get("SEGMENT 20"));
		affiche(FigureUtils.get("SEGMENT 21"));
		
		go("Test compare figure couleur Carré");
		Carre cart1 = new Carre(a,4,Couleur.Noir);
		Carre cart2 = new Carre(a,4,Couleur.Bleu);
		affiche(cart1.equals(cart1));
		affiche(cart1.equals(cart2));
		
		go("Test compare figure couleur Rectangle");
		Rectangle rectt1 = new Rectangle(a,4,5,Couleur.Noir);
		Rectangle rectt2 = new Rectangle(a,4,5,Couleur.Bleu);
		affiche(rectt1.equals(rectt1));
		affiche(rectt1.equals(rectt2));
		
		go("Test compare figure couleur Rond");
		Rond rndt1 = new Rond(a, 5, Couleur.Rouge);
		Rond rndt2 = new Rond(a, 5, Couleur.Vert);
		affiche(rndt1.equals(rndt1));
		affiche(rndt1.equals(rndt2));
		
		go("Test compare figure couleur Segment");
		Segment segt1 = new Segment(a, 3, false, Couleur.Jaune);
		Segment segt2 = new Segment(a, 5, true, Couleur.Rouge);
		affiche(segt1.equals(segt1));
		affiche(segt1.equals(segt2));
		
		go();
		
	}

	public static void go(){
		if(!start){
			System.out.println("                    Go!");
			start=true;
			time = System.currentTimeMillis();
		}
		else{
			System.out.println("\n============================================================\n"
					+ "                  Temps d'exécution : " + (System.currentTimeMillis()-time) 
					+ "ms\n============================================================\n");
		}
	}

	public static void reset(){
		time = System.currentTimeMillis();
	}
	
	public static void go(boolean stop){
		if(stop){
			System.exit(0);
		}
	}
	
	public static void go(String str){
		go();
		reset();
		System.out.println("                    ["+str.toUpperCase()+"]                    ");			
	}
	
	public static void affiche(Object str){
		System.out.println(str);
	}
	
}

