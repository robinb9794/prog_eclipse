package Prog2_SS_2014;

import java.awt.*;
import java.awt.event.*;

public class Aufgabe3 extends Frame{
	Point p1,p2,p3,p4;
	int counter=0;
	Color c;
	Comp comp = new Comp();
	boolean flag;
	
	Aufgabe3(){
		setSize(400,400);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		
		comp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(counter==0){
					p1=e.getPoint();
					++counter;
				}else if(counter==1){
					p2=e.getPoint();
					++counter;
				}else if(counter==2){
					p3=e.getPoint();
					++counter;
				}else{
					p4=e.getPoint();
					counter=0;
					repaint();

				}
			}
		});
		
		Panel p = new Panel(new FlowLayout());
		
		Button red = new Button("rot");
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c=Color.RED;
				repaint();
			}
		});
		p.add(red);
		
		Button green = new Button("grün");
		green.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				c=Color.GREEN;
				repaint();
			}
		});
		p.add(green);
		
		Button ss = new Button("ss");
		ss.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(flag){
					flag=false;
				}else{
					flag=true;
					
					new Thread(){
						public void run(){
							while(flag){
								try{
									Thread.sleep(200);
								}catch(Exception e){
									
								}
								if(c==Color.RED){
									c=Color.GREEN;
								}else{
									c=Color.RED;
								}
								repaint();
							}
						}
					}.start();
				}
			}
		});
		p.add(ss);
		
		add(BorderLayout.SOUTH,p);
		add(BorderLayout.CENTER,comp);
		setVisible(true);
		
	}
	
	class Comp extends Component{
		public void paint(Graphics g){
			if(p1!=null && p2!=null && p3!=null && p4!=null){
				int[] x = {p1.x,p2.x,p3.x,p4.x};
				int[] y = {p1.y,p2.y,p3.y,p4.y};
				
				if(c!=null){
					g.setColor(c);
					g.fillPolygon(x,y,4);
				}else{
					g.drawPolygon(x,y,4);
				}
			}		
		}
	}
	
	public static void main(String args[]){
		new Aufgabe3();
	}
}
