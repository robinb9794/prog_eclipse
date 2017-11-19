package model;

public class ApproxPoint {
	public int x,y,z;
	
	public ApproxPoint(int x, int y){
		this.x=x;
		this.y=y;
		z=1;
	}
	
	public double distance(ApproxPoint p){
		return Math.abs(Math.sqrt(Math.pow(Math.abs(x-p.x), 2)+Math.pow(Math.abs(y-p.y), 2)+Math.pow(Math.abs(z-p.z), 2)));
	}
}
