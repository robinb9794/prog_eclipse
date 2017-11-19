package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.OriginalImage;
import model.Model;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static Model mod;
	public JPanel pCenter = new JPanel(new BorderLayout());
	public JPanel pDisplayImages = new JPanel(new FlowLayout());
	public JScrollPane pane = new JScrollPane(pDisplayImages);
	public Component comp = new Component();
	public JPanel pSouth;
	public Image img;
	public LessColorViewer lcViewer;
	
	public GUI(){}

	public GUI(Model m) {
		super("Assign_02");
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

	public class Component extends JComponent {
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g) {
			if(mod.selectedIndexes.size()>0){
				mod.trgMemImg.newPixels();
				img = createImage(mod.trgMemImg);
				g.drawImage(img, 0, 0, mod.compWidth, mod.compHeight, null);
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
			synchronized (mod.LOCK) {
				mod.LOCK.notifyAll();
			}
		}
	}

	class Settings extends JMenuBar {
		private static final long serialVersionUID = 1L;

		public Settings() {
			JMenu actions = new JMenu("Actions");
			JMenuItem miHisto = new JMenuItem("Histogram");
			JMenuItem miApprox = new JMenuItem("Less colors");

			miHisto.addActionListener(e -> {
				if (mod.selectedIndexes.size() == 1) {
					if(mod.checkHisto()) {
						showErrorDialog("Histogram already exists.");
					}else {
						mod.createHistogram();
						showErrorDialog("Created ./histogram.txt_"+mod.selectedIndexes.get(0)+".");
					}					
				} else {
					showErrorDialog("Please select exactly one image to create histogram.");
				}
			});

			miApprox.addActionListener(e->{
				if(mod.selectedIndexes.size()==1) {
					if(mod.checkHisto()){
						if(lcViewer==null) {
							mod.prepareApprox();
							lcViewer=new LessColorViewer();
						}else {
							lcViewer.setVisible(true);
						}
					}else {
						showErrorDialog("Please create histogram first.");
					}
				}else {
					showErrorDialog("Please select exactly one image.");
				}				
			});
			
			actions.add(miHisto);
			actions.add(miApprox);
			add(actions);
		}
	}
	
	private void showErrorDialog(String dialog) {
		JOptionPane.showMessageDialog(this, dialog);
	}

	public void paint(Graphics g) {
		super.paint(g);
		comp.repaint();
	}
	
	public Model getMod() {
		return mod;
	}
}

