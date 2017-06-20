import java.io.Serializable;

public class Point implements Serializable {
	
	private int x, y;
	private static final int INIT_X = 25;
	private static final int INIT_Y = 25;
	
	private static final Point origine = new Point();
	
	public Point(int abs, int ord){
		x = abs;
		y = ord;
	}
	
	public Point(){
		this (INIT_X, INIT_Y);
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String toString(){
		return "("+x+";"+y+")";
	}
	
	public boolean equals(Object o){
		if(o instanceof Point){
			Point p = (Point) o;
			return (p.getX() == this.getX())&&(p.getY() == this.getY());
		} else {
			return false;
		}
	}
	
	public void affiche(String str){
		System.out.println(str);
	}
	
	public void affiche(){
		System.out.println(toString());
	}
	
	public double distanceOrigine() {
		int x2 = (origine.getX() - this.getX()) * (origine.getX() - this.getX());
		int y2 = (origine.getY() - this.getY()) * (origine.getY() - this.getY());
		return Math.sqrt( x2 + y2 );
	}
}
