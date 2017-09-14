package Mergesort;

public class Merge {	
	public static void main(String args[]){
		int[] arr = {8,7,6,5,4,3,2,1};
		merge(arr);
	}
	
	public static int[] merge(int[] arr){
		if(arr.length==1){
			return arr;
		}
		int[] l = new int[(int)Math.floor(arr.length/2)];
		int[] r = new int[(int)Math.ceil(arr.length/2)];
		for(int i = 0; i<arr.length;i++){
			if(i<(int)Math.floor(arr.length/2)){
				l[i]=arr[i];
			}else{
				r[i-(int)Math.floor(arr.length/2)]=arr[i];
			}
		}
		l=merge(l);
		r=merge(r);
		return merge(l,r);
	}
	
	public static int[] merge(int[] l,int[] r){
		int[] merge = new int[l.length+r.length];
		int i =0,j=0;
		for(int k =0; k<merge.length;k++){
			if(l[i]<r[j]){
				merge[k]=l[i++];
			}else{
				merge[k]=r[j++];
			}
		}
		return merge;
	}
}
