package Life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Life {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int m_Width, m_Height;
	int m_Zeilen, m_Spalten;
	int[][] m_a;
	boolean m_printTheGame;
	int m_wX;
	int m_textWidth;
	boolean m_isMaxX;
	boolean m_Flag;
	boolean m_isDialog;

	Model(int width, int height, int size) {
		m_Width = width;
		m_Height = height;
		m_Zeilen = size;
		m_Spalten = size;
		m_a = new int[m_Zeilen][m_Spalten];
		m_wX = 0;
	}

	public void setArray() {
		m_a = new int[m_Zeilen][m_Spalten];
	}

	void firstInit() {
		double r = Math.random();
		for (int i = 0; i < m_a.length; i++) {
			for (int j = 0; j < m_a[i].length; j++) {
				if (r > 0.5) {
					m_a[i][j] = 1;
				} else {
					m_a[i][j] = 0;
				}
				r = Math.random();
			}
		}
	}

	void newInit() {
		int[][] b = new int[m_Zeilen][m_Spalten];

		for (int iZ = 0; iZ < m_a.length; ++iZ) {
			for (int iS = 0; iS < m_a[iZ].length; ++iS) {
				int zaehler = 0;
				if (m_a[(iZ - 1 + m_a.length) % (m_a.length)][iS] > 0) {
					++zaehler;
				}
				if (m_a[iZ][(iS - 1 + m_a[iZ].length) % (m_a[iZ].length)] > 0) {
					++zaehler;
				}
				if (m_a[(iZ + 1) % m_a.length][(iS - 1 + m_a[iZ].length) % (m_a[iZ].length)] > 0) {
					++zaehler;
				}
				if (m_a[(iZ - 1 + m_a.length) % (m_a.length)][(iS + 1) % m_a[iZ].length] > 0) {
					++zaehler;
				}
				if (m_a[(iZ + 1) % m_a.length][iS] > 0) {
					++zaehler;
				}
				if (m_a[iZ][(iS + 1) % m_a[iZ].length] > 0) {
					++zaehler;
				}
				if (m_a[(iZ + 1) % m_a.length][(iS + 1) % m_a[iZ].length] > 0) {
					++zaehler;
				}
				if (m_a[(iZ - 1 + m_a.length) % (m_a.length)][(iS - 1 + m_a[iZ].length) % (m_a[iZ].length)] > 0) {
					++zaehler;
				}

				if (m_a[iZ][iS] > 0 && zaehler == 3) {
					b[iZ][iS] = ++m_a[iZ][iS];
				} else if (m_a[iZ][iS] > 0 && zaehler == 2) {
					b[iZ][iS] = ++m_a[iZ][iS];
				} else if (m_a[iZ][iS] == 0 && zaehler == 3) {
					b[iZ][iS] = ++m_a[iZ][iS];
				} else if (zaehler > 3 || zaehler < 2) {
					b[iZ][iS] = 0;
				} else {
					b[iZ][iS] = 0;
				}
			}
		}

		for (int i = 0; i < m_a.length; i++) {
			for (int j = 0; j < m_a[i].length; j++) {
				m_a[i][j] = b[i][j];
			}
		}
	}
}

class View extends JFrame {
	Model m;
	JDialog dialog;

	View(Model m) {
		this.m = m;
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(d.width / 2 - m.m_Width / 2, d.height / 2 - m.m_Height / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new JComponent() {
			{
				setPreferredSize(new Dimension(m.m_Width, m.m_Height));
			}

			public void paintComponent(Graphics g) {
				String c = "";
				Dimension d = getSize();
				for (int i = 0; i < m.m_a.length; i++) {
					for (int j = 0; j < m.m_a[i].length; j++) {
						if (m.m_a[i][j] == 0) {
							c = "_";
						} else if (m.m_a[i][j] == 1) {
							c = "a";
						} else if (m.m_a[i][j] == 2) {
							c = "f";
						} else if (m.m_a[i][j] == 3) {
							c = "r";
						} else if (m.m_a[i][j] > 3) {
							c = "6";
						}
						g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, (int) d.height / m.m_Spalten));
						g.drawString(c, (d.width / m.m_Spalten * (j + 1) - (d.width / m.m_Spalten / 2)),
								(d.height / m.m_Zeilen * (i + 1) - (d.height / m.m_Zeilen / 2)));
					}
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension d = getSize();
				m.m_Width = d.width;
				m.m_Height = d.height;
				setPreferredSize(d);
			}
		});

		JFrame f2 = new JFrame();
		f2.setSize(200, 100);
		f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f2.setLocation(d.width / 2 + m.m_Width / 2, d.height / 2 - m.m_Height / 2);
		f2.setLayout(new FlowLayout());
		f2.add(new MyButton());

		JMenuBar menuBar = new JMenuBar();
		JMenu settings = new JMenu("Einstellungen");

		JMenuItem newInit = new JMenuItem("Berechnen");
		newInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.newInit();
				repaint();
			}
		});

		JMenuItem close = new JMenuItem("Beenden");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				f2.dispose();
				if (m.m_isDialog) {
					dialog.dispose();
				}
			}
		});

		JMenuItem cent = new JMenuItem("Zentrieren");
		cent.addActionListener(e -> {
			setLocation(d.width / 2 - m.m_Width / 2, d.height / 2 - m.m_Height / 2);
		});

		JMenuItem max = new JMenuItem("Maximieren");
		max.addActionListener(e -> {
			setSize(d.width, d.height);
			setLocation(0, 0);
			m.m_Width = d.width;
			m.m_Height = d.height;
		});

		JMenuItem icon = new JMenuItem("Inconifizieren");
		icon.addActionListener(e -> {
			URL url = getClass().getResource("Scoobydoo.jpg");
			ImageIcon img = new ImageIcon(url);
			setIconImage(img.getImage());
		});

		JMenuItem newinit = new JMenuItem("Neue Initialisierung");
		newinit.addActionListener(e -> {
			m.firstInit();
			repaint();
		});

		JMenuItem popup = new JMenuItem("Zeilen & Spalten");
		popup.addActionListener(e -> {
			if (m.m_isDialog) {
				dialog.setVisible(true);
			} else {
				myDialog();
				m.m_isDialog = true;
			}
		});

		settings.add(newInit);
		settings.add(cent);
		settings.add(max);
		settings.add(icon);
		settings.add(newinit);
		settings.add(popup);
		settings.add(close);

		menuBar.add(settings);

		setJMenuBar(menuBar);

		pack();

		MyWindow(this, f2);

	}

	public void myDialog() {
		dialog = new JDialog();
		dialog.setTitle("Zeilen & Spalten");
		dialog.setSize(400, 100);
		dialog.setModal(false);
		dialog.setLayout(new FlowLayout());

		JButton btn1 = new JButton("15x10");
		btn1.addActionListener(e -> {
			m.m_Zeilen = 15;
			m.m_Spalten = 10;
			m.setArray();
			m.firstInit();
			repaint();
		});
		dialog.add(btn1);

		JButton btn2 = new JButton("25x25");
		btn2.addActionListener(e -> {
			m.m_Zeilen = 25;
			m.m_Spalten = 25;
			m.setArray();
			m.firstInit();
			repaint();
		});
		dialog.add(btn2);

		JButton btn3 = new JButton("40x30");
		btn3.addActionListener(e -> {
			m.m_Zeilen = 40;
			m.m_Spalten = 30;
			m.setArray();
			m.firstInit();
			repaint();
		});
		dialog.add(btn3);

		dialog.pack();
		dialog.setVisible(true);
	}

	public void MyWindow(JFrame f, JFrame f2) {
		Window w = new Window(f);
		w.setBackground(Color.BLACK);
		w.setSize(new Dimension(500, 200));
		w.setLocationRelativeTo(null);

		w.add(new JComponent() {
			public void paintComponent(Graphics g) {
				Dimension d = w.getSize();

				g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 25));

				int textWidth = g.getFontMetrics().stringWidth("Game Of Life");

				boolean reachedEnd = false;

				if (m.m_Flag == false) {
					reachedEnd = m.m_wX == d.getWidth() ? true : false;
					if (reachedEnd == true) {
						m.m_Flag = true;
					}
				}
				g.setColor(Color.WHITE);
				g.drawString("Game Of Life", m.m_wX - textWidth, (int) d.getHeight() / 2);

				if (m.m_Flag == false) {
					++m.m_wX;
				} else {
					--m.m_wX;
				}

				if (m.m_Flag == true && m.m_wX == -textWidth) {
					m.m_printTheGame = true;
				}

			}
		});
		w.setVisible(true);

		while (m.m_printTheGame == false) {
			w.repaint();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		w.dispose();
		f.setVisible(true);
		f2.setVisible(true);
	}

	class MyButton extends JButton {
		public MyButton() {
			super("berechnen");
			setPreferredSize(new Dimension(100, 50));
			addActionListener(new Reagierer());
		}
	}

	class Reagierer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.newInit();
			repaint();
		}

	}
}

class Controller {

	public Controller() {
		Model m = new Model(600, 600, 20);
		View v = new View(m);
		m.firstInit();
		v.repaint();

		// while (true) {
		// m.newInit();
		// v.repaint();
		// try {
		// Thread.sleep(200);
		// } catch (InterruptedException e) {
		//
		// }
		// }
	}
}