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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
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
	
	public MemoryImageSource m_imgSrc;
	public Image m_Img;
	

	public GUI(Model m) {
		super("Let's fade...");
		this.m_Mod = m;

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
				dispose();
			}
		});

		m_miChooseImages.addActionListener(e->{
			new MyImageChooser();
		});
		m_miHistogram.addActionListener(e->{

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
	
	public void paint(Graphics g) {
		if (m_pCenter.getGraphics() != null && m_imgSrc!=null) {
			m_pCenter.getGraphics().drawImage(m_Img, 0, 0, m_pCenter.getWidth(), m_pCenter.getHeight(), null);
		}
	}

	class ImageCheckBox extends JCheckBox implements ActionListener {
		private static final long serialVersionUID = 1L;
		private ImageIcon image;
		private int index;

		public ImageCheckBox(ImageIcon image, int index) {
			this.image = image;
			this.index=index;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent arg0) {
			if(isSelected()){
				System.out.println("added selected image");
				m_Mod.getM_selectedImages().add(this.image.getImage());
				m_Mod.getM_Indexes().add(this.index);
				if(m_Mod.getM_selectedImages().size()==1){
					synchronized(m_Mod.getLOCK()){
						m_Mod.getLOCK().notifyAll();
					}
				}
			}else{
				System.out.println("removed image");
				m_Mod.getM_selectedImages().remove(this.image.getImage());
				m_Mod.getM_Indexes().remove((Object)this.index);
			}
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
						label.setSize(new Dimension(100,100));

						File file = files[i];
						if(file.isDirectory()){
							files=file.listFiles();
							file = files[i];
						}
						
						m_Mod.getM_originalImages().add(ImageIO.read(file));

						ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(100,100, Image.SCALE_SMOOTH));

						label.setIcon(icon);

						JCheckBox cb = new ImageCheckBox(icon,i);

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
