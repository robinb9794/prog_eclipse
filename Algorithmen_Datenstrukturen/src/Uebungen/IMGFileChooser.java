package Uebungen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.filechooser.FileNameExtensionFilter;

public class IMGFileChooser {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int mWidth, mHeight;
	Image mClickedImage;

	public Model(int mWidth, int mHeight) {
		this.mWidth = mWidth;
		this.mHeight = mHeight;
	}

}

class View extends JFrame {
	Model m;
	MyComp comp = new MyComp();
	final JPanel pMain = new JPanel(new BorderLayout());
	final JPanel pSouth = new JPanel(new BorderLayout());
	final JPanel pDisplayImages = new JPanel(new FlowLayout());
	final JScrollPane scrollPane = new JScrollPane(pDisplayImages);

	public View(Model m) {
		this.m = m;
		setPreferredSize(new Dimension(m.mWidth, m.mHeight));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				m.mWidth = getWidth();
				m.mHeight = getHeight();
			}
		});
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Einstellungen");
		JMenuItem chooseImages = new JMenuItem("Bilder wählen...");

		chooseImages.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			FileNameExtensionFilter filter = new FileNameExtensionFilter(".jpg/.gif", "jpg", "gif");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File[] files = chooser.getSelectedFiles();
				for (int i = 0; i < files.length; i++) {
					try {
						JPanel panel = new JPanel(new BorderLayout());

						JLabel label = new JLabel();
						label.setSize(new Dimension(60, 60));
						ImageIcon image = new ImageIcon(ImageIO.read(files[i]).getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
						label.setIcon(image);
						label.setName("image_" + (i + 1));
						label.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								m.mClickedImage = image.getImage();
								comp.repaint();
							}
						});

						JCheckBox cb = new JCheckBox();
						cb.setName("cb_" + (i + 1));

						panel.add(BorderLayout.WEST, cb);
						panel.add(BorderLayout.CENTER, label);

						pDisplayImages.add(panel);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				scrollPane.setBounds(0, 0, m.mWidth, 100);

				pSouth.revalidate();
			}
		});

		menu.add(chooseImages);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		pSouth.add(scrollPane);
		pMain.add(BorderLayout.CENTER, comp);
		pMain.add(BorderLayout.SOUTH, pSouth);

		add(pMain);
		pack();
		setVisible(true);
	}

	class MyComp extends JComponent {
		public void paintComponent(Graphics g) {
			if (m.mClickedImage != null) {
				g.drawImage(m.mClickedImage, m.mWidth / 2 - 100, m.mHeight / 3 - 100, 200, 200, null);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 500);
	View v = new View(m);

	public Controller() {

	}
}
