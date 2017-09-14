package TTT;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Main extends Frame {
	int spielzug=0;
	JLabel[][] grid = new JLabel[3][3];
	
	Main(){
		setSize(600,600);
		setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				dispose();
			}
		});
		
		Panel p = new Panel(new GridLayout(3,3));
		for(int i = 0; i<3;i++){
			for(int j = 0; j<3;j++){				
				JLabel l = new JLabel();	
				int m =i;
				int n = j;
				
				l.setFont(new Font("Georgia", Font.PLAIN, 100));
				l.setBorder(LineBorder.createGrayLineBorder());
				l.setHorizontalAlignment(JLabel.CENTER);
				l.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
						if(spielzug%2==0){
							l.setText("X");
							grid[m][n].removeMouseListener(this);
						}else{
							l.setText("O");
							grid[m][n].removeMouseListener(this);
						}
						++spielzug;
					}
				});
				grid[i][j] = l;
				p.add(BorderLayout.CENTER,l);
			}
		}
		
		add(BorderLayout.CENTER,p);
		setVisible(true);
	}
	
	public static void main(String args[]){
		new Main();
	}
}
