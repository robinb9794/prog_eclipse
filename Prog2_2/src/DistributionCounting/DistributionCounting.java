package DistributionCounting;

public class DistributionCounting {
	public static void main(String args[]){
		int n = 5;
		int m = 8;
		
		int[] field = new int[n];
		for(int i = 0; i<field.length;i++){
			field[i]=(int)(Math.random()*m);
		}
		print(field);
		countThis(field);
	}
	
	static void print(int[] field){
		for(int i = 0; i<field.length;i++){
			System.out.print(field[i]+"\t");
		}
		System.out.println();
	}
	
	static void countThis(int[] field){
		int min=99999999;
		int max=-999999999;
		
		//min und max ermitteln
		for(int i = 0; i<field.length;i++){
			if(field[i]<min){
				min=field[i];
			}
			if(field[i]>max){
				max=field[i];
			}
		}
		System.out.println("Minimum: "+min+", Maximum: "+max);
		
		int[] count = new int[max+1];
		
		//zähle die Einträge
		for(int i = 0; i<field.length;++i){
			++count[field[i]];
		}
		
		//speichere die Einträge aus count gezielt wieder in field ab
		for(int i1=0, i2=0; i1<count.length;++i1){
			for(int i3=0; i3<count[i1];++i3){
				field[i2++]=i1;
			}
		}
		print(field);
	}
}
