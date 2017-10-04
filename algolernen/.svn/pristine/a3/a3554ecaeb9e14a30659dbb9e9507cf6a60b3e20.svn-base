package uebung02.aufgabe02;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ImageColorReduction {
	
	public Image imageColorReduction(Image image, double reductionFactor, Frame owner) throws Exception{
		int w = image.getWidth(owner);
		int h = image.getHeight(owner);
		
		int[] pixArray = new int[w*h];
		
		PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixArray, 0, w);
		pg.grabPixels();
		
		HashMap<Integer, ColorInfoObject> colorHashMap = new HashMap<Integer, ColorInfoObject>();
		
		//--------- ColorInfoObjekte einfügen --------------------------------------------
		for (int i = 0; i < pixArray.length; ++i) {
			ColorInfoObject obj = colorHashMap.get(pixArray[i]); 
			if (obj == null){
				colorHashMap.put(pixArray[i],obj = new ColorInfoObject(pixArray[i]));
			}
			obj.occurence.add(i);
		}
		
		List<ColorInfoObject> arrayList = new ArrayList<ColorInfoObject>(colorHashMap.values()); 

		Collections.sort(arrayList,new Comparator<ColorInfoObject>(){
			@Override
			public int compare(ColorInfoObject o1, ColorInfoObject o2) {
				Integer links = o1.occurence.size();
				Integer rechts = o2.occurence.size();
				return links.compareTo(rechts);
			}
		});
		
		final int CUT = (int)(arrayList.size()*reductionFactor);
		for(int i=0;i<CUT;++i){
			final int col = arrayList.get(i).color;
			
			int bestColor=0;
			double bestHitDiff=766;

			for(int j=CUT;j<arrayList.size();j++){
				final int col2 = arrayList.get(j).color;
				final int DIFF = diffIntColors(col,col2);
				if (DIFF < bestHitDiff){
					bestColor = col2;
					bestHitDiff = DIFF;
				}
			}
			arrayList.get(i).sub_col=bestColor;
		}
		
		for(int i = 0;i<arrayList.size();++i){
			for(int j=0;j<arrayList.get(i).occurence.size();++j){
				pixArray[arrayList.get(i).occurence.get(j)]=arrayList.get(i).sub_col;
			}
		}
		MemoryImageSource m = new MemoryImageSource(w,h,pixArray,0,w);
		final Image img = owner.createImage(m);
		img.flush();
		return img;
	}
	
	public int diffIntColors(int colorLeft, int colorRight){
		int redLeft = (colorLeft>>16)&255;
		int greenLeft = (colorLeft>>8)&255;
		int blueLeft = (colorLeft)&255;
		
		int redRight = (colorRight>>16)&255;
		int greenRight = (colorRight>>8)&255;
		int blueRight = (colorRight)&255;
		
		int redDiff, greenDiff, blueDiff;
		
		redDiff=Math.abs(redLeft-redRight);
		greenDiff=Math.abs(greenLeft-greenRight);
		blueDiff=Math.abs(blueLeft-blueRight);
		
		return redDiff+greenDiff+blueDiff;		
	}
	
}
