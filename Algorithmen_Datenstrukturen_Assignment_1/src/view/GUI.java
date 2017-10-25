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
import java.awt.event.MouseMotionAdapter;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.OriginalImage;
import model.Model;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public Model m_Mod;
	public final JPanel m_pCenter = new JPanel(new BorderLayout());
	public final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	public final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	public final Component m_Comp = new Component(this);
	public JDialog m_Dialog;
	public JPanel m_pSouth;
	public MemoryImageSource m_imgSrc;
	public Image m_Img;
	boolean m_clickedFirst;
	public boolean m_choseImages = false, m_Fade = false, m_Lens = false;

	public GUI(Model m) {
		super("Let's fade...");
		this.m_Mod = m;

		new ImageChooser();

		if (m_choseImages) {
			m_imgSrc = new MemoryImageSource(m_Mod.m_Width, m_Mod.m_Height, m_Mod.m_Pix, 0, m_Mod.m_Width);
			m_imgSrc.setAnimated(true);

			setPreferredSize(new Dimension(m_Mod.m_Width, m_Mod.m_Height));
			setLayout(new BorderLayout());
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(BorderLayout.SOUTH, m_pSouth);
			add(BorderLayout.CENTER, m_Comp);
			setJMenuBar(new TheMenu());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}

	class Component extends JComponent {
		private static final long serialVersionUID = 1L;

		public Component(JFrame father) {
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent e) {
					m_Mod.m_lensPoint = e.getPoint();
					if (m_Lens) {
						synchronized (m_Mod.LOCK) {
							m_Mod.LOCK.notifyAll();
						}
					}
				}

				public void mouseDragged(MouseEvent e) {
					if (m_clickedFirst) {
						m_Mod.m_morphPoint_2 = e.getPoint();
						m_clickedFirst = false;
					} else {
						m_Mod.m_morphPoint_1 = e.getPoint();
						m_clickedFirst = true;
					}
				}
			});
			addMouseListener(new MouseAdapter() {//
				public void mouseReleased(MouseEvent e) {
					if (m_Mod.m_morphPoint_1 != null && m_Mod.m_morphPoint_2 != null) {
						Object[] options = { "move", "rotate", "scale", "distort" };
						int n = JOptionPane.showOptionDialog(father, "Choose wisely:", "Morphology",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
						m_Mod.morph(n);
					}
				}
			});
		}

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
			try {
				for (int i = 0; i < files.length; i++) {
					JPanel panel = new JPanel(new BorderLayout());
					JLabel label = new JLabel();
					label.setSize(new Dimension(100, 100));

					File file = files[i];
					if (file.isDirectory()) {
						files = file.listFiles();
						file = files[i];
					}

					OriginalImage img = new OriginalImage(file, m_Mod.m_Width, m_Mod.m_Height);
					m_Mod.m_originalImages.add(img);

					ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
					label.setIcon(icon);

					JCheckBox cb = new ImageCheckBox(icon, i);

					panel.add(BorderLayout.WEST, cb);
					panel.add(BorderLayout.CENTER, label);
					m_pDisplayImages.add(panel);
				}

				m_Pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				m_Pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				m_Pane.setBounds(0, 0, m_Mod.m_Width, 50);
				m_Pane.revalidate();
				add(BorderLayout.CENTER, m_Pane);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error! Please select images.");
				m_choseImages = false;
			}
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
				m_choseImages = true;
				File[] files = getSelectedFiles();
				m_pSouth = new ImageViewer(files);
			} else {
				m_Mod.m_XClicked = true;
			}
		}
	}

	class ImageCheckBox extends JCheckBox implements ActionListener {
		static final long serialVersionUID = 1L;
		private int index;

		public ImageCheckBox(ImageIcon image, int index) {
			this.index = index;
			addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			if (isSelected()) {
				System.out.println("added image");
				m_Mod.m_Indexes.add(index);
			} else {
				System.out.println("removed image");
				m_Mod.m_Indexes.remove((Object) index);
			}
		}
	}

	class TheMenu extends JMenuBar {
		private static final long serialVersionUID = 1L;

		public TheMenu() {
			JMenu settings = new JMenu("Settings");
			JMenuItem fade = new JMenuItem("Fade");
			JMenuItem lens = new JMenuItem("Lens");
			JMenuItem histo = new JMenuItem("Histogram");

			fade.addActionListener(e -> {
				if (m_Mod.m_Indexes.size() > 1) {
					m_Fade = !m_Fade;
					m_Lens = false;
					synchronized (m_Mod.LOCK) {
						m_Mod.LOCK.notifyAll();
					}

				} else {
					JOptionPane.showMessageDialog(this, "First select at least two images to fade.");
				}
			});

			lens.addActionListener(e -> {
				if (m_Mod.m_Indexes.size() == 2) {
					m_Lens = !m_Lens;
					if (m_Lens && !m_Fade) {
						synchronized (m_Mod.LOCK) {
							m_Mod.LOCK.notifyAll();
						}
					} else {
						m_Fade = false;
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			histo.addActionListener(e -> {
				if (m_Mod.m_Indexes.size() == 1) {
					m_Mod.createHistogram();
					JOptionPane.showMessageDialog(this, "Created histogram.");
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image to create histogram.");
				}
			});

			settings.add(fade);
			settings.add(lens);
			settings.add(histo);
			add(settings);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		m_Comp.repaint();
	}
}