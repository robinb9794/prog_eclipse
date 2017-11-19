package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.OriginalImage;
import model.Task;
import model.Model;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public Model mod;
	public JPanel pCenter = new JPanel(new BorderLayout());
	public JPanel pDisplayImages = new JPanel(new FlowLayout());
	public JScrollPane pane = new JScrollPane(pDisplayImages);
	public Component comp = new Component(this);
	public JPanel pSouth;
	public MemoryImageSource srcMemImg, trgMemImg;
	public Image img;
	public ColorFade colorFade;
	public Circle c;

	public GUI(Model m) {
		super("Assign_01");
		this.mod = m;
		
		new ImageChooser();

		if (mod.choseImages) {
			setPreferredSize(new Dimension(mod.frameWidth, mod.frameHeight));
			setLayout(new BorderLayout());
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			add(BorderLayout.SOUTH, pSouth);
			add(BorderLayout.CENTER, comp);
			setJMenuBar(new Settings());
			pack();
			setLocationRelativeTo(null);
			setVisible(true);
		}
	}
	
	public void createColorFade(){
		this.colorFade=new ColorFade();
	}

	public void setMemImg() {
		srcMemImg = new MemoryImageSource(comp.getWidth(), comp.getHeight(), mod.srcPix, 0, comp.getWidth());
		srcMemImg.setAnimated(true);
		trgMemImg = new MemoryImageSource(comp.getWidth(), comp.getHeight(), mod.trgPix, 0, comp.getWidth());
		trgMemImg.setAnimated(true);
	}

	public class Component extends JComponent {
		private static final long serialVersionUID = 1L;
		JFrame father;

		public Component(JFrame father) {
			this.father = father;
			addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseMoved(MouseEvent e) {
					mod.lensP = e.getPoint();
					if (mod.TASK == Task.LENS) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				}
			});
			addMouseListener(new MouseAdapter() {//
				public void mouseClicked(MouseEvent e) {
					synchronized (mod.LOCK) {
						if (mod.waitingForClicks) {
							if (!mod.firstClicked || mod.TASK==Task.DRAW_CIRCLES) {
								mod.p1 = e.getPoint();
								System.out.println("set p1 (" + mod.p1.getX() + "|" + mod.p1.getY() + ")");
								mod.firstClicked = true;
								if(mod.TASK==Task.DRAW_CIRCLES && mod.waitingForClicks){
									mod.LOCK.notifyAll();
								}
							} else if (mod.firstClicked && !(mod.TASK==Task.DRAW_CIRCLES)) {
								mod.p2 = e.getPoint();
								System.out.println("set p2 (" + mod.p2.getX() + "|" + mod.p2.getY() + ")");
								mod.firstClicked = false;
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
			if (trgMemImg != null) {
				trgMemImg.newPixels();
				img = createImage(trgMemImg);
				g.drawImage(img, 0, 0, mod.compWidth, mod.compHeight, null);
				if ((mod.TASK == Task.TRANSLATE || mod.TASK == Task.ROTATE || mod.TASK == Task.SHEARX
						|| mod.TASK == Task.SHEARY) && mod.p1 != null && mod.p2 != null) {
					g.setColor(Color.RED);
					g.drawRect(mod.startX, mod.startY, mod.endX - mod.startX, mod.endY - mod.startY);
				}
			}
		}

		public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
			int xIncr = 1, yIncr = 1, dy = 2 * (y2 - y1), dx = 2 * (x1 - x2), tmp;

			if (x1 > x2) {
				xIncr = -1;
				dx = -dx;
			}

			if (y1 > y2) {
				yIncr = -1;
				dy = -dy;
			}

			int e = 2 * dy + dx;
			int x = x1;
			int y = y1;

			if (dy < -dx) {
				while (x != (x2 + 1)) {
					e += dy;
					if (e > 0) {
						e += dx;
						y += yIncr;
					}
					g.drawLine(x, y, x, y);
					x += xIncr;
				}
			} else {
				tmp = -dx;
				dx = -dy;
				dy = tmp;

				e = 2 * dy + dx;

				while (y != (y2 + 1)) {
					e += dy;
					if (e > 0) {
						e += dx;
						x += xIncr;
					}
					g.drawLine(x, y, x, y);
					y += yIncr;
				}
			}
		}
	}

	class ImageViewer extends JPanel {
		private static final long serialVersionUID = 1L;

		public ImageViewer(File[] files) {
			setLayout(new BorderLayout());
			try {
				for (int i = 0; i < files.length; i++) {
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
					JLabel label = new JLabel();
					label.setSize(new Dimension(100, 100));

					File file = files[i];
					if (file.isDirectory()) {
						files = file.listFiles();
						file = files[i];
					}

					OriginalImage oImg = new OriginalImage(ImageIO.read(file));
					mod.originalImages.add(oImg);

					ImageIcon icon = new ImageIcon(ImageIO.read(file).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
					label.setIcon(icon);

					JCheckBox cb = new ImageCheckBox(icon, i);

					panel.add(cb);
					panel.add(label);
					panel.add(Box.createHorizontalStrut(15));
					panel.add(new JSeparator(SwingConstants.VERTICAL));
					panel.setBorder(BorderFactory.createEmptyBorder(5,0,20,3));
					pDisplayImages.add(panel);					
				}
				pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				pane.setBounds(0, 0, mod.frameWidth, 50);
				pane.revalidate();
				add(BorderLayout.CENTER, pane);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Error! Please select images.");
				mod.choseImages = false;
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
				mod.choseImages = true;
				File[] files = getSelectedFiles();
				pSouth = new ImageViewer(files);
			} else {
				mod.closedFrame = true;
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
			if (!mod.running) {
				synchronized (mod.LOCK) {
					mod.LOCK.notifyAll();
				}
			}
		}
	}

	class Settings extends JMenuBar {
		private static final long serialVersionUID = 1L;

		public Settings() {
			JMenu actions = new JMenu("Actions");
			JMenuItem miFade = new JMenuItem("Fade");
			JMenuItem miLens = new JMenuItem("Lens");
			JMenuItem miTranslate = new JMenuItem("Translate");
			JMenuItem miRotate = new JMenuItem("Rotate");
			JMenuItem miScale = new JMenuItem("Scale");
			JMenuItem miShearX = new JMenuItem("Shear X");
			JMenuItem miShearY = new JMenuItem("Shear Y");
			JMenuItem miDrawLines = new JMenuItem("Draw Lines");
			JMenuItem miDrawCircles = new JMenuItem("Draw Circles");
			JMenuItem miHisto = new JMenuItem("Histogram");
			JMenuItem miStop = new JMenuItem("Stop");
			JMenuItem miReset = new JMenuItem("Reset");

			miFade.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() > 1) {
					mod.TASK = Task.FADE;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select at least two images.");
				}
			});

			miLens.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.LENS;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miTranslate.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.TRANSLATE;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miRotate.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.ROTATE;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miScale.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.SCALE;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miShearX.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.SHEARX;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miShearY.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 2) {
					mod.TASK = Task.SHEARY;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly two images.");
				}
			});

			miDrawLines.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.DRAW_LINES;
					if (!mod.running) {
						synchronized (mod.LOCK) {
							mod.LOCK.notifyAll();
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image.");
				}
			});

			miDrawCircles.addActionListener(e -> {
				if (!mod.stopClicked && mod.TASK != null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				} else if (mod.selectedIndexes.size() == 1) {
					mod.TASK = Task.DRAW_CIRCLES;
					if (!mod.running) {
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
					JOptionPane.showMessageDialog(this, "Created ./histogram.txt.");
				} else {
					JOptionPane.showMessageDialog(this, "Please select exactly one image to create histogram.");
				}
			});

			miStop.addActionListener(e -> {
				if (mod.TASK == null) {
					JOptionPane.showMessageDialog(this, "Please select an action first.");
				} else {	
					if(mod.TASK!=Task.FADE && mod.TASK!=Task.LENS && mod.TASK!=Task.DRAW_LINES && mod.TASK!=Task.DRAW_CIRCLES){
						int reply = JOptionPane.showConfirmDialog(null, "Save pixel?", "Important Question", JOptionPane.YES_NO_OPTION);
				        if (reply == JOptionPane.YES_OPTION) {
				        	mod.insertInArray(mod.trgPix,mod.bgImg.imgPix);
				        	mod.pixelSaved=true;
				        }
					}					
					
					mod.running = false;
					mod.stopClicked = true;
					mod.TASK = null;
					mod.p1=null;
					mod.p2=null;
					if(colorFade!=null){
						colorFade.dispose();
					}
					try {
						Thread.sleep(20);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					synchronized (mod.LOCK) {
						mod.LOCK.notifyAll();
					}

				}
			});

			miReset.addActionListener(e -> {
				if (mod.stopClicked) {
					mod.reset();
					repaint();
					synchronized (mod.LOCK) {
						mod.LOCK.notifyAll();
					}
				} else if (mod.TASK == null) {
					JOptionPane.showMessageDialog(this, "Please stop first.");
				}
			});

			actions.add(miFade);
			actions.add(miLens);
			actions.addSeparator();
			actions.add(miTranslate);
			actions.add(miRotate);
			actions.add(miScale);
			actions.add(miShearX);
			actions.add(miShearY);
			actions.addSeparator();
			actions.add(miDrawLines);
			actions.add(miDrawCircles);
			actions.addSeparator();
			actions.add(miHisto);
			actions.addSeparator();
			actions.add(miStop);
			actions.add(miReset);
			add(actions);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		comp.repaint();
	}
	
	public class ColorFade extends JFrame {
		private static final long serialVersionUID = 1L;

		public ColorFade() {
			super("Fade your circle...");
			setPreferredSize(new Dimension(300,200));
			addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					dispose();
				}
			});
			ControlledColor srcCol = new ControlledColor(c.col1);
			ControlledColor trgCol = new ControlledColor(c.col2);
			setLayout(new GridLayout(2,1));
			add(srcCol);
			add(trgCol);
			pack();
			setVisible(true);
		}

		class LabelScrollBar extends JPanel {
			private static final long serialVersionUID = 1L;
			JTextField m_Lab = new JTextField(6);
			JScrollBar m_Bar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 1, 0, 256);
			String m_Prefix;

			public LabelScrollBar(String strPrefix) {
				m_Prefix = strPrefix;
				m_Lab.setText(m_Prefix);
				m_Lab.setEnabled(false);
				setLayout(new BorderLayout());
				add(BorderLayout.EAST, m_Lab);
				add(BorderLayout.CENTER, m_Bar);
				m_Bar.addAdjustmentListener(new AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						m_Lab.setText(m_Prefix + m_Bar.getValue());
					}
				});
			}
		}

	class ControlledColor extends JPanel implements AdjustmentListener {
		private static final long serialVersionUID = 1L;
			LabelScrollBar red = new LabelScrollBar("red ");
			LabelScrollBar green = new LabelScrollBar("green ");
			LabelScrollBar blue = new LabelScrollBar("blue ");
			int[] cols;

			public ControlledColor(int[] cols) {
				this.cols = cols;
				setLayout(new GridLayout(3, 1));
				add(red);
				add(green);
				add(blue);
				red.m_Bar.addAdjustmentListener(this);
				green.m_Bar.addAdjustmentListener(this);
				blue.m_Bar.addAdjustmentListener(this);
			}

			public void adjustmentValueChanged(AdjustmentEvent e) {
				cols[0] = red.m_Bar.getValue();
				cols[1] = green.m_Bar.getValue();
				cols[2] = blue.m_Bar.getValue();
				c.reRun();
			}
		}
	}
}

