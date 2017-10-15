package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
	public final JPanel m_pSouth = new JPanel(new BorderLayout());
	public final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	public final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	public final JMenuBar m_Bar = new JMenuBar();
	public final JMenu m_Menu = new JMenu("Settings");
	public final JMenuItem m_miChooseImages = new MyMenuItem();
	public JFileChooser m_Chooser;
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
				dispose();
			}
		});

		m_Menu.add(m_miChooseImages);
		m_Bar.add(m_Menu);
		setJMenuBar(m_Bar);

		m_pSouth.add(m_Pane);
		add(BorderLayout.SOUTH, m_pSouth);
		add(BorderLayout.CENTER, m_pCenter);

		pack();
		setVisible(true);
	}

	class MyMenuItem extends JMenuItem implements ActionListener {
		private static final long serialVersionUID = 1L;

		public MyMenuItem() {
			super("Choose images...");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			m_Chooser = new ImageChooser();
		}

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

	class ImageChooser extends JFileChooser {
		private static final long serialVersionUID = 1L;

		public ImageChooser() {
			setMultiSelectionEnabled(true);
			setDialogTitle("Please select images");
			setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg/.gif", "jpg", "gif");
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
						label.setName("image_" + (i + 1));

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
		
		 public void approveSelection() {
             if (getSelectedFile().isFile()) {
                 return;
             } else
                 super.approveSelection();
         }
	}

	
}
