package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Model;

public class GUI extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	private Model m_Mod;
	private final JPanel m_pCenter = new JPanel();
	private final JPanel m_pSouth = new JPanel(new BorderLayout());
	private final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	private final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	private final JMenuBar m_Bar = new JMenuBar();
	private final JMenu m_Menu = new JMenu("Settings");
	private final JMenuItem m_miChooseImages = new MyMenuItem();
	private JFileChooser m_Chooser;
	private MemoryImageSource m_imgSrc;
	private Image m_Img;
		
	public GUI(Model m){
		super("Let's fade...");
		this.m_Mod=m;		
		this.m_imgSrc= new MemoryImageSource(m_Mod.getM_imgWidth(),m_Mod.getM_imgHeight(),m_Mod.getM_Pix(),0,m_Mod.getM_imgWidth());
		this.m_imgSrc.setAnimated(true);		
		m_Img=createImage(m_imgSrc);
		
		m_Pane.setVisible(false);
		
		setPreferredSize(new Dimension(m_Mod.getM_Width(),m_Mod.getM_Height()));
		setLayout(new BorderLayout());		
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				m.setM_Width(getWidth());
				m.setM_Height(getHeight());
			}
		});		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				m.setM_XClicked(true);
				synchronized(m_Mod.getLOCK()){
					m_Mod.getLOCK().notifyAll();
				}
				dispose();
			}
		});
		
		m_Menu.add(m_miChooseImages);
		m_Bar.add(m_Menu);
		setJMenuBar(m_Bar);

		m_pSouth.add(m_Pane);
		add(BorderLayout.SOUTH, m_pSouth);
		add(BorderLayout.CENTER,m_pCenter);
		
		pack();
		setVisible(true);
		
		new Thread(this).start();
	}
	
	class MyMenuItem extends JMenuItem implements ActionListener{
		private static final long serialVersionUID = 1L;

		public MyMenuItem(){
			super("Choose images...");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			m_Chooser = new ImageChooser();
		}
		
	}
	
	class ImageCheckBox extends JCheckBox implements ActionListener{
		private static final long serialVersionUID = 1L;
		private ImageIcon image;

		public ImageCheckBox(ImageIcon image, int i){
			this.image=image;
			setName("cb_" + (i + 1));
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			if(isSelected()){
				System.out.println("added selected image");
				m_Mod.getM_selectedImages().add(image.getImage());
				if(m_Mod.getM_selectedImages().size()==1){
					synchronized(m_Mod.getLOCK()){
						m_Mod.getLOCK().notifyAll();
					}
				}
			}else{
				System.out.println("removed image");
				m_Mod.getM_selectedImages().remove(image.getImage());
				
			}
		}	
	}

	class ImageChooser extends JFileChooser{
		private static final long serialVersionUID = 1L;

		public ImageChooser(){
			setMultiSelectionEnabled(true);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg/.gif", "jpg", "gif");
			setFileFilter(filter);
			int returnVal = showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = getSelectedFiles();
				for (int i = 0; i < files.length; i++) {
					try {
						JPanel panel = new JPanel(new BorderLayout());
						JLabel label = new JLabel();
						label.setSize(new Dimension(m_Mod.getM_imgWidth(),m_Mod.getM_imgHeight()));
						ImageIcon image = new ImageIcon(ImageIO.read(files[i]).getScaledInstance(m_Mod.getM_imgWidth(),m_Mod.getM_imgHeight(), Image.SCALE_SMOOTH));
						label.setIcon(image);
						label.setName("image_" + (i + 1));

						JCheckBox cb = new ImageCheckBox(image,i);

						panel.add(BorderLayout.WEST, cb);
						panel.add(BorderLayout.CENTER, label);

						m_pDisplayImages.add(panel);

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				m_Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				m_Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				m_Pane.setBounds(0, 0, m_Mod.getM_Width(), 100);
				m_Pane.setVisible(true);

				m_pSouth.revalidate();
				
			}
		}	
	}

	public void run() {
		while(!m_Mod.isM_XClicked()){
			try {
				synchronized(m_Mod.getLOCK()){
					while(!(m_Mod.getM_selectedImages().size()>0)){
						if(m_Mod.getM_selectedImages().size()==0){
							m_pCenter.getGraphics().clearRect(0, 0, m_pCenter.getWidth(), m_pCenter.getHeight());
						}
						System.out.println("waiting for selected images...");
						m_Mod.getLOCK().wait();
					}
				}
				if(!m_Mod.isM_XClicked()){
					System.out.println("congrats, you've selected images! Now let's fade...");
					m_Mod.fade(m_imgSrc, m_pCenter, m_Img);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		}
	}
}


