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
import model.Task;
import model.Model;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public Model mod;
	public final JPanel pCenter = new JPanel(new BorderLayout());
	public final JPanel pDisplayImages = new JPanel(new FlowLayout());
	public final JScrollPane pane = new JScrollPane(pDisplayImages);
	public final Component comp = new Component(this);
	public JPanel pSouth;
	public MemoryImageSource srcMemImg, trgMemImg;
	public Image img;
	public boolean firstClicked, waitingForClicks, running;
	public boolean closedFrame, choseImages;

	public GUI(Model m) {
		super("Assign_01");
		this.mod = m;

		new ImageChooser();

		if (choseImages) {
			srcMemImg = new MemoryImageSource(mod.frameWidth, mod.frameHeight, mod.srcPix, 0, mod.frameWidth);
			srcMemImg.setAnimated(true);
			trgMemImg = new MemoryImageSource(mod.frameWidth, mod.frameHeight, mod.trgPix, 0, mod.frameWidth);
			trgMemImg.setAnimated(true);

			setPreferredSize(new Dimension(mod.frameWidth, mod.frameHeight));
			setLayout(new BorderLayout());
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(BorderLayout.SOUTH, pSouth);
			add(BorderLayout.CENTER, comp);
			setJMenuBar(new TheMenu());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}

	class Component extends JComponent {
		private static final long serialVersionUID = 1L;
		JFrame father;

		public Component(JFrame father) {
			this.father = father;
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent e) {
					// if(mod.TASK==Task.LENS){
					// mod.p1=e.getPoint();
					// synchronized(mod.LOCK){
					// mod.LOCK.notifyAll();
					// }
					// }
				}
			});
			addMouseListener(new MouseAdapter() {//
				public void mouseClicked(MouseEvent e) {
					synchronized (mod.LOCK) {
						if (waitingForClicks) {
							if (!firstClicked) {
								mod.p1 = e.getPoint();
								firstClicked = true;
								System.out.println("set p1 "+mod.p1.getX()+"|"+mod.p1.getY());
							} else if (firstClicked) {
								mod.p2 = e.getPoint();
								firstClicked = false;
								System.out.println("set p1 "+mod.p2.getX()+"|"+mod.p2.getY());
								mod.LOCK.notifyAll();
							}
						} else if (mod.TASK != null) {
							mod.LOCK.notifyAll();
						}
					}
				}
			});
		}

		public void paintComponent(Graphics g) {
			trgMemImg.newPixels();
			img = createImage(trgMemImg);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
			if(mod.p1!=null && mod.p2 != null){
				g.drawRect(mod.startX, mod.startY, mod.endX-mod.startX, mod.endY-mod.startY);
			}
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

					OriginalImage img = new OriginalImage(file, mod.frameWidth, mod.frameHeight);
					mod.originalImages.add(img);

					ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
					label.setIcon(icon);

					JCheckBox cb = new ImageCheckBox(icon, i);

					panel.add(BorderLayout.WEST, cb);
					panel.add(BorderLayout.CENTER, label);
					pDisplayImages.add(panel);
				}

				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				pane.setBounds(0, 0, mod.frameWidth, 50);
				pane.revalidate();
				add(BorderLayout.CENTER, pane);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error! Please select images.");
				choseImages = false;
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
				choseImages = true;
				File[] files = getSelectedFiles();
				pSouth = new ImageViewer(files);
			} else {
				closedFrame = true;
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
				mod.selectedIndexes.add(index);
			} else {
				System.out.println("removed image");
				mod.selectedIndexes.remove((Object) index);
			}
			if (!running) {
				synchronized (mod.LOCK) {
					mod.LOCK.notifyAll();
				}
			}
		}
	}

	class TheMenu extends JMenuBar {
		private static final long serialVersionUID = 1L;

		public TheMenu() {
			JMenu settings = new JMenu("Settings");
			JMenuItem miFade = new JMenuItem("Fade");
			// JMenuItem miLens = new JMenuItem("Lens");
			JMenuItem miMove = new JMenuItem("Move");
			JMenuItem miRotate = new JMenuItem("Rotate");
			JMenuItem miScale = new JMenuItem("Scale");
			JMenuItem miShear = new JMenuItem("Distort");
			JMenuItem miDrawLines = new JMenuItem("Draw Lines");
			JMenuItem miDrawCircles = new JMenuItem("Draw Circles");
			JMenuItem miHisto = new JMenuItem("Histogram");
			JMenuItem miStop = new JMenuItem("Stop");

			miFade.addActionListener(e -> {
				if (mod.selectedIndexes.size() > 1) {
					mod.TASK = Task.FADE;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select at least two images.");
				}
			});

			// miLens.addActionListener(e -> {
			// if (mod.selectedIndexes.size() == 2) {
			// mod.TASK = Task.LENS;
			// pSouth.setVisible(false);
			// if (!running) {
			// synchronized (mod.LOCK) {
			// mod.LOCK.notifyAll();
			// }
			// }
			// } else {
			// JOptionPane.showMessageDialog(this, "Please select exactly two
			// images.");
			// }
			// });

			miMove.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.MOVE;
					mod.p1 = null;
					mod.p2 = null;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miRotate.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.ROTATE;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miScale.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.SCALE;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miShear.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.SHEAR;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miDrawLines.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.DRAW_LINES;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image.");
				}
			});

			miDrawCircles.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.DRAW_CIRCLE;
					if (!running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image.");
				}
			});

			miHisto.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					mod.createHistogram();
					JOptionPane.showMessageDialog(this, "Created histogram.");
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image to create histogram.");
				}
			});

			miStop.addActionListener(e -> {
				mod.TASK = null;
				mod.p1=null;
				mod.p2=null;
			});

			settings.add(miFade);
			// settings.add(miLens);
			settings.add(miMove);
			settings.add(miRotate);
			settings.add(miScale);
			settings.add(miShear);
			settings.add(miDrawLines);
			settings.add(miDrawCircles);
			settings.add(miHisto);
			settings.add(miStop);
			add(settings);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		comp.repaint();
	}
}