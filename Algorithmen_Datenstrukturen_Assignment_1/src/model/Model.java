package model;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
	public int frameWidth, frameHeight;
	public int compWidth, compHeight;
	public int startX, startY, endX, endY;
	public int translX = 0, translY = 0;
	public int rotateA=0;
	public double scaleF=1;
	public double shearX=0, shearY=0;
	public int[] srcPix, trgPix;
	public ArrayList<OriginalImage> originalImages = new ArrayList<OriginalImage>();
	public ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
	public final Object LOCK = new Object();
	public Point p1, p2, lensP;
	public Task TASK;
	public Matrix morphMatrix;
	public OriginalImage srcImg, bgImg;
	public boolean firstClicked, waitingForClicks, running;
	public boolean closedFrame, choseImages;
	public boolean stopClicked, pixelSaved;

	public Model(int width, int height) {
		this.frameWidth = width;
		this.frameHeight = height;
		
	}

	private int singleShuffle(int part1, int part2, int p) {
		return part1 + (part2 - part1) * p / 100;
	}

	private int colorShuffle(int pix1, int pix2, int p) {
		int red = singleShuffle((pix1 >> 16) & 255, (pix2 >> 16) & 255, p);
		int green = singleShuffle((pix1 >> 8) & 255, (pix2 >> 8) & 255, p);
		int blue = singleShuffle((pix1) & 255, (pix2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}

	public void fade(int p) {
		for (int i = 0; i < compWidth * compHeight; i++) {
			trgPix[i] = colorShuffle(srcImg.imgPix[i], bgImg.imgPix[i], p);
		}
	}

	public void createHistogram() {
		Writer writer;
		try {
			OriginalImage img = originalImages.get(0);
			HashMap<String, Integer> histogram = new HashMap<String, Integer>();

			for (int i = 0; i < compWidth * compHeight; i++) {
				String key = binaryToDec(Integer.toBinaryString(img.imgPix[i]));
				int value = 1;
				if (!histogram.containsKey(key)) {
					histogram.put(key, value);
				} else {
					value = histogram.get(key);
					histogram.replace(key, value + 1);
				}
			}

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("histogram.txt"), "UTF-8"));
			for (Map.Entry e : histogram.entrySet()) {
				writer.write(e.getKey() + "\t:\t" + e.getValue() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String binaryToDec(String bin){
		String b="",g="",r="";
		for(int i=0; i<8;i++){
			r=r+bin.charAt(8+i);
			g=g+bin.charAt(8+i+8);
			b=b+bin.charAt(8+i+2*8);			
		}
		return new Integer(Integer.parseInt(r,2)).toString()+" "+new Integer(Integer.parseInt(g,2)).toString()+" "+new Integer(Integer.parseInt(b,2)).toString();
	}

	public void lens() {
		for (int x = 0; x < compWidth; x++) {
			for (int y = 0; y < compHeight; y++) {
				int index = y * compWidth + x;
				int xDif = lensP.x - x;
				int yDif = lensP.y- y;
				int value = (xDif * xDif + yDif * yDif) / 100;
				int maxValue = value > 100 ? 100 : value;
				trgPix[index] = colorShuffle(srcImg.imgPix[index], bgImg.imgPix[index], maxValue);
			}
		}
	}

	public void morph() {
		for (int x = 0; x < compWidth; x++) {
			for (int y = 0; y < compHeight; y++) {
				Vector trgVec = new Vector(x,y);
				Vector srcVec = Matrix.multiply(morphMatrix, trgVec);
				int srcIndex = srcVec.getY() * compWidth + srcVec.getX();
				if (srcVec.getX()>=startX && srcVec.getY()>=startY && srcVec.getX()<endX && srcVec.getY()<endY) {
					trgPix[y * compWidth + x] = pixelSaved?srcPix[srcIndex]:srcImg.imgPix[srcIndex];
				}else{
					trgPix[y * compWidth + x] = bgImg.imgPix[y * compWidth + x];
				}
			}
		}
	}

	public void insertInArray(int[] src, int[] trg) {
		for (int x = 0; x < compWidth; x++) {
			for (int y = 0; y < compHeight; y++) {
				trg[y * compWidth + x] = src[y * compWidth + x];
			}
		}
	}

	public void setStartAndEnd() {
		startX = p1.x < p2.x ? p1.x : p2.x;
		startY = p1.y < p2.y ? p1.y : p2.y;
		endX = p2.x > p1.x ? p2.x : p1.x;
		endY = p2.y > p1.y ? p2.y : p1.y;		
	}
	
	public void reset(){
		TASK=null;
		running=false;
		stopClicked=false;
		pixelSaved=false;
		p1=null;
		p2=null;
		translX=0;
		translY=0;
		rotateA=0;
		scaleF=1;
		shearX=0;
		shearY=0;
		for(int x=0; x<compWidth;x++){
			for(int y=0; y<compHeight;y++){
				srcPix[y*compHeight+x]=0;
				trgPix[y*compHeight+x]=0;
			}
		}
	}
	
	public void setCompValues(int width, int height){
		compWidth=width;
		compHeight=height;
		this.srcPix = new int[compWidth * compHeight];
		this.trgPix = new int[compWidth * compHeight];
	}
	
	public void grapPixels(){
		for(OriginalImage oImg : originalImages){
			oImg.setPixels(compWidth, compHeight);
		}
	}
}
