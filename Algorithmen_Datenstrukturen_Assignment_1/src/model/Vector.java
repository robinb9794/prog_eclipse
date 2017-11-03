package model;

public class Vector {
	public int[] v;
	
	public Vector(int x, int y){
		v = new int[3];
		v[0]=x;
		v[1]=y;
		v[2]=1;
	}
	
	public int getX(){
		return v[0];
	}
	
	public int getY(){
		return v[1];
	}
	
	public int getI(int i){
		return v[i];
	}
}
