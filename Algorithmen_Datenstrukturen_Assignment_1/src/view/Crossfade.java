package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ImageToFade;
import model.Model;

public class Crossfade extends JFrame {
	private static final long serialVersionUID = 1L;
	public Model m_Mod;
	public final JPanel m_pCenter = new JPanel(new BorderLayout());
	public final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	public final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	public final Component m_Comp = new Component();
	public JDialog m_Dialog;
	public JPanel m_pSouth;
	public MemoryImageSource m_imgSrc;
	public Image m_Img;

	public Crossfade(Model m) {
		super("Let's fade...");
		this.m_Mod = m;

		new ImageChooser();
		
		m_imgSrc = new MemoryImageSource(m_Mod.m_Width, m_Mod.m_Height, m_Mod.m_Pix, 0, m_Mod.m_Width);
		m_imgSrc.setAnimated(true);

		setPreferredSize(new Dimension(m_Mod.m_Width, m_Mod.m_Height));
		setLayout(new BorderLayout());
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(BorderLayout.SOUTH,m_pSouth);

		add(BorderLayout.CENTER, m_Comp);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);		
	}

	class Component extends JComponent {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			m_imgSrc.newPixels();
			m_Img = createImage(m_imgSrc);
			g.drawImage(m_Img, 0, 0, getWidth(), getHeight(), null);
		}
	}

	class ImageViewer extends JPanel {
		private static final long serialVersionUID = 1L;

		public ImageViewer(File[] files) {
			setLayout(new BorderLayout());
			for (int i = 0; i < files.length; i++) {
				try {
					JPanel panel = new JPanel(new BorderLayout());
					JLabel label = new JLabel();
					label.setSize(new Dimension(100, 100));

					File file = files[i];
					if (file.isDirectory()) {
						files = file.listFiles();
						file = files[i];
					}
					
					ImageToFade img = new ImageToFade(file,m_Mod.m_Width, m_Mod.m_Height);
					m_Mod.m_originalImages.add(img);
					
					ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
					label.setIcon(icon);

					label.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							m_Mod.createHistogram(img);
						}
					});

					JCheckBox cb = new ImageCheckBox(icon, i);

					panel.add(BorderLayout.WEST, cb);
					panel.add(BorderLayout.CENTER, label);
					m_pDisplayImages.add(panel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			m_Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			m_Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			m_Pane.setBounds(0, 0, m_Mod.m_Width, 50);
			m_Pane.revalidate();
			add(BorderLayout.CENTER,m_Pane);
			

			//m_pCenter.add(BorderLayout.SOUTH, m_Pane);
//			add(BorderLayout.CENTER, m_pCenter);
//			pack();
//			setVisible(true);
		}
	}

	class ImageChooser extends JFileChooser {
		private static final long serialVersionUID = 1L;

		public ImageChooser() {
			setMultiSelectionEnabled(true);
			setDialogTitle("Please select images");
			setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg/gif", "jpg", "gif");
			setFileFilter(filter);
			int returnVal = showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = getSelectedFiles();
				m_pSouth = new ImageViewer(files);
			} else {
				System.out.println("no images selected");
			}
		}
	}

	class ImageCheckBox extends JCheckBox implements ActionListener {
		static final long serialVersionUID = 1L;
		ImageIcon image;
		int index;

		public ImageCheckBox(ImageIcon image, int index) {
			this.image = image;
			this.index = index;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (isSelected()) {
				System.out.println("added image");
				m_Mod.m_Indexes.add(index);
				if (m_Mod.m_Indexes.size() == 2) {
					synchronized (m_Mod.LOCK) {
						m_Mod.LOCK.notifyAll();
					}
				}
			} else {
				System.out.println("removed image");
				m_Mod.m_Indexes.remove((Object) index);
			}
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		m_Comp.repaint();
	}
	
	public void clear(){
		m_Comp.getGraphics().clearRect(0, 0, getWidth(), getHeight());
	}
}