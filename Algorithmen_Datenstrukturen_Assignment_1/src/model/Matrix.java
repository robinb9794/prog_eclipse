package model;

public class Matrix {
	double[][] v;
	
	public Matrix(double[][] v){
		this.v=v;
	}
	
	public static Matrix multiply(Matrix x, Matrix y){
		double[][] p = new double[3][3];
		for(int i = 0; i < x.v.length; ++i){
            for(int j = 0; j < x.v[i].length; j++){
                for(int k = 0; k < 3; k++){
                    p[i][j] += + x.v[k][j] * y.v[i][k];
                }
             }
         }
         return new Matrix(p); 
	}
	
	public static Matrix translate(int dx, int dy){
		double[][] dM = {{1,0,Math.round(-dx)},{0,1,Math.round(-dy)},{0, 0, 1}};
		return new Matrix(dM);
	}
	
	 public static Vector multiply(Matrix a, Vector v){
         int[] res = new int[a.v[0].length];
         for(int i = 0; i < a.v[0].length; i++){
             for(int j = 0; j < a.v.length; j++){               
                res[i] += a.v[i][j] * v.getI(j);
            }
        }
         Vector r = new Vector(res[0], res[1]); 
         return r;
     }
}
