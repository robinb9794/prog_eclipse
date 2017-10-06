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

import model.Model;

public class GUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private Model m_Mod;
	private final Comp m_Comp = new Comp();
	private final JPanel m_pMain = new JPanel(new BorderLayout());
	private final JPanel m_pSouth = new JPanel(new BorderLayout());
	private final JPanel m_pDisplayImages = new JPanel(new FlowLayout());
	private final JScrollPane m_Pane = new JScrollPane(m_pDisplayImages);
	private final JMenuBar m_Bar = new JMenuBar();
	private final JMenu m_Menu = new JMenu("Settings");
	private final JMenuItem m_miChooseImages = new MyMenuItem();
	private JFileChooser m_Chooser;
	
	public GUI(Model m){
		this.m_Mod=m;
		m_Pane.setVisible(false);
		setPreferredSize(new Dimension(m_Mod.getM_Width(),m_Mod.getM_Height()));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e){
				m.setM_Width(getWidth());
				m.setM_Height(getHeight());
			}
		});
		m_Menu.add(m_miChooseImages);
		m_Bar.add(m_Menu);
		setJMenuBar(m_Bar);

		m_pSouth.add(m_Pane);
		m_pMain.add(BorderLayout.CENTER, m_Comp);
		m_pMain.add(BorderLayout.SOUTH, m_pSouth);

		add(m_pMain);
		pack();
		setVisible(true);
	}
	
	class MyMenuItem extends JMenuItem implements ActionListener{
		private static final long serialVersionUID = 1L;

		public MyMenuItem(){
			super("Choose images...");
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			m_Chooser = new MyChooser();
		}
		
	}

	class MyChooser extends JFileChooser{
		private static final long serialVersionUID = 1L;

		public MyChooser(){
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
						label.setSize(new Dimension(60, 60));
						ImageIcon image = new ImageIcon(ImageIO.read(files[i]).getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
						label.setIcon(image);
						label.setName("image_" + (i + 1));
						label.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								m_Mod.setM_clickedImage(image.getImage());
								m_Comp.repaint();
							}
						});

						JCheckBox cb = new JCheckBox();
						cb.setName("cb_" + (i + 1));

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

	class Comp extends JComponent{
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			if(m_Mod.getM_clickedImage()!=null){
				g.drawImage(m_Mod.getM_clickedImage(), m_Mod.getM_Width() / 2 - 100, m_Mod.getM_Height() / 3 - 100, 200, 200, null);
			}
		}
	}
}


