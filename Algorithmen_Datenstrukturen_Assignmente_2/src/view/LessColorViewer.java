package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Model;

public class LessColorViewer extends GUI{
	private static final long serialVersionUID = 1L;

	public LessColorViewer(){
		super();
		setResizable(false);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		add(BorderLayout.CENTER,new JComponent(){
			{
				setPreferredSize(new Dimension(mod.compWidth,mod.compHeight));
			}
			
			public void paintComponent(Graphics g){
				mod.trgMemImg.newPixels();
				img = createImage(mod.trgMemImg);
				g.drawImage(img, 0, 0, mod.compWidth, mod.compHeight, null);
			}
		});
		add(BorderLayout.SOUTH,new ApproxSettings());
		pack();
		setVisible(true);
	}
	
	class ApproxSettings extends JPanel{		
		public ApproxSettings(){
			setLayout(new BorderLayout());
			add(BorderLayout.NORTH,new Values());
			add(BorderLayout.SOUTH,new ColorSlider());
			pack();
			setVisible(true);
		}
		
	}
	
	class Values extends JPanel{
		public Values(){
			setLayout(new FlowLayout());
			addTextFields();
		}
		
		private void addTextFields(){
			for(int i=0; i<3;i++){
				JTextField t = new JTextField();
				t.setPreferredSize(new Dimension(mod.compWidth/3,25));
				add(t);
			}
		}
	}
	
	class ColorSlider extends JSlider implements ChangeListener{
		public ColorSlider(){
			super(JSlider.HORIZONTAL,0,mod.approxValue,mod.approxValue);
			setMinorTickSpacing(1);
			setMajorTickSpacing(5);
			setPaintTicks(true);			
			setPaintLabels(true);
			addChangeListener(this);
			setVisible(true);
		}

		public void stateChanged(ChangeEvent e) {
			mod.approxValue=getValue();
		}
	}
}
