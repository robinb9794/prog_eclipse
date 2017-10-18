package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Model;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private Model m_Mod;
	
	public final JPanel m_pCenter = new JPanel();
	private final JPanel m_pSouth = new JPanel(new BorderLayout());
	private final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	private final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	private final JMenuBar m_Bar = new JMenuBar();
	private final JMenu m_Menu = new JMenu("Menu");
	private final JMenuItem m_miChooseImages = new JMenuItem("Choose images");
	private final JMenuItem m_miHistogram = new JMenuItem("Histogram");
	
	private JDialog m_Dialog;
	
	public MemoryImageSource m_imgSrc;
	public Image m_Img;
	

	public GUI(Model m) {
		super("Let's fade...");
		this.m_Mod = m;
		this.m_imgSrc = new MemoryImageSource(m_Mod.getM_imgWidth(), m_Mod.getM_imgHeight(), m_Mod.getM_Pix(), 0,
				m_Mod.getM_imgWidth());
		this.m_imgSrc.setAnimated(true);
		m_Img = createImage(m_imgSrc);

		m_Pane.setVisible(false);

		setPreferredSize(new Dimension(m_Mod.getM_Width(), m_Mod.getM_Height()));
		setLayout(new BorderLayout());
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				m.setM_Width(getWidth());
				m.setM_Height(getHeight());
			}
		});
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.setM_XClicked(true);
				synchronized (m_Mod.getLOCK()) {
					m_Mod.getLOCK().notifyAll();
				}
				if(m_Mod.isM_openedHistogram()){
					m_Dialog.dispose();
				}
				dispose();
			}
		});

		m_miChooseImages.addActionListener(e->{
			new MyImageChooser();
		});
		m_miHistogram.addActionListener(e->{
			if(m_Mod.isM_openedHistogram()){
				m_Dialog.setVisible(true);
			}else{
				m_Dialog = new MyHistogram(this);
				m_Mod.setM_openedHistogram(true);
			}
		});
		m_Menu.add(m_miChooseImages);
		m_Menu.add(m_miHistogram);
		m_Bar.add(m_Menu);
		setJMenuBar(m_Bar);

		m_pSouth.add(m_Pane);
		add(BorderLayout.SOUTH, m_pSouth);
		add(BorderLayout.CENTER, m_pCenter);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	class ImageCheckBox extends JCheckBox implements ActionListener {
		private static final long serialVersionUID = 1L;
		private ImageIcon image;

		public ImageCheckBox(ImageIcon image) {
			this.image = image;
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
	
	class MyHistogram extends JDialog{
		private static final long serialVersionUID = 1L;

		public MyHistogram(JFrame parent){
			setPreferredSize(new Dimension(m_Mod.getM_Width(),m_Mod.getM_Height()/2));
			setModal(false);
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					m_Mod.setM_openedHistogram(false);
					dispose();
				}
			});
			setLocationRelativeTo(parent);
			setLayout(new BorderLayout());
			add(BorderLayout.NORTH, new JLabel("0: 0-63___1: 64-127___2: 128-191___3: 192-255"));
			
			JPanel table = createTable();
			
			add(BorderLayout.CENTER,table);
			add(BorderLayout.CENTER,new JScrollPane(table));
			pack();
			setVisible(true);
		}
		
		private JPanel createTable(){
			JPanel table = new JPanel(new GridLayout(0,4));

			for(int i=0; i<4;i++){
				for(int j=0; j<4;j++){
					for(int k=0; k<4;k++){
						if(i==0&&j==0&&k==0){
							table.add(new JLabel("Red"));
							table.add(new JLabel("Green"));
							table.add(new JLabel("Blue"));
							table.add(new JLabel("Pixel Count"));
						}
						JLabel label_i = new JLabel(Integer.toString(i));
						JLabel label_j = new JLabel(Integer.toString(j));
						JLabel label_k = new JLabel(Integer.toString(k));
						JLabel label_v = new JLabel(Integer.toString(i*j+k));
						
						label_i.setBorder(BorderFactory.createMatteBorder(1,1,0,1,Color.BLACK));
						label_j.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.BLACK));
						label_k.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.BLACK));
						label_v.setBorder(BorderFactory.createMatteBorder(1,0,0,1,Color.BLACK));
						
						label_i.setHorizontalAlignment(JLabel.CENTER);
						label_j.setHorizontalAlignment(JLabel.CENTER);
						label_k.setHorizontalAlignment(JLabel.CENTER);
						label_v.setHorizontalAlignment(JLabel.CENTER);

						table.add(label_i);
						table.add(label_j);
						table.add(label_k);	
						table.add(label_v);		
						
						m_Mod.getM_histoValues()[i*j+k]=label_v;
					}
				}
			}
			return table;
		}
	}

	class MyImageChooser extends JFileChooser {
		private static final long serialVersionUID = 1L;

		public MyImageChooser() {
			setMultiSelectionEnabled(true);
			setDialogTitle("Please select images");
			setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg/gif", "jpg", "gif");
			setFileFilter(filter);
			int returnVal = showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = getSelectedFiles();
				for (int i = 0; i < files.length; i++) {
					try {
						JPanel panel = new JPanel(new BorderLayout());
						JLabel label = new JLabel();
						label.setSize(new Dimension(m_Mod.getM_imgWidth(), m_Mod.getM_imgHeight()));

						File file = files[i];
						if(file.isDirectory()){
							files=file.listFiles();
							file = files[i];
						}

						ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(m_Mod.getM_imgWidth(),
								m_Mod.getM_imgHeight(), Image.SCALE_SMOOTH));

						label.setIcon(icon);
						label.addMouseListener(new MouseAdapter(){
							public void mouseEntered(MouseEvent e){

							}
						});

						JCheckBox cb = new ImageCheckBox(icon);

						panel.add(BorderLayout.WEST, cb);
						panel.add(BorderLayout.CENTER, label);

						m_pDisplayImages.add(panel);

					} catch (Exception e) {
					}
				}
				m_Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				m_Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				m_Pane.setBounds(0, 0, m_Mod.getM_Width(), 100);
				m_Pane.setVisible(true);

				m_pSouth.revalidate();
			}else{
				System.out.println("no images selected");
			}
		}
	}

	
}
