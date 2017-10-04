package zusatz.uDrawGraphTestumgebung;

public class RedBlackTree_noNH<K extends Comparable<K>, D> implements
		Tree<K, D> {

	Object[] m_Nodes = new Object[4];
	Integer[] Keys ={556,738,666,313,222,500,132,248};
	public Node<K, D> m_Root = null;

	@SuppressWarnings("unused")
	boolean insert(K key, D data) {
		if (m_Root == null) {
			m_Root = new Node<K, D>(key, data);
			return true;
		} else {
			Node<K, D> dad = null;
			Node<K, D> grandDad = null;
			Node<K, D> greatGrandDad = null;
			Node<K, D> node = m_Root;

			while (node != null) {

				if (node.is4Node() == true) {
					if (node == m_Root) {
						m_Root.m_Left.m_bIsRed = false;
						m_Root.m_Right.m_bIsRed = false;
					} else {
						node.convert4Node();

						if (greatGrandDad == null) { // wenn was gefunden wird
														// isses mit grandDad
														// noch an root dran

							// 4ter nach convert4node
							if (grandDad != null
									&& grandDad.m_Right.m_Right == node
									&& dad.m_bIsRed == true
									&& node.m_bIsRed == true) {
								System.out.println("4ter Fall nach convert4node");
								if(greatGrandDad!=null)
									System.out.println("ggd "+greatGrandDad.m_Key);
								if(grandDad!=null)
									System.out.println("gd "+grandDad.m_Key);
								if(dad!=null)
									System.out.println("d "+dad.m_Key);
								if(node!=null)
									System.out.println("n "+node.m_Key);
								System.out.println();
								m_Root = dad;
								m_Root.m_Right = node;
								grandDad.m_Right = dad.m_Left;
								m_Root.m_Left = grandDad;
							}
							// 5ter nach convert4node
							if (grandDad != null
									&& grandDad.m_Left.m_Left == node
									&& dad.m_bIsRed == true
									&& node.m_bIsRed == true) {
								System.out.println("5ter Fall nach convert4node");
								if(greatGrandDad!=null)
									System.out.println("ggd "+greatGrandDad.m_Key);
								if(grandDad!=null)
									System.out.println("gd "+grandDad.m_Key);
								if(dad!=null)
									System.out.println("d "+dad.m_Key);
								if(node!=null)
									System.out.println("n "+node.m_Key);
								System.out.println();
								m_Root = dad;
								m_Root.m_Left = node;
								grandDad.m_Left = dad.m_Right;
								m_Root.m_Right = grandDad;
							}

							// 6ter nach convert4node
							if (grandDad != null
									&& grandDad.m_Right.m_Left == node
									&& dad.m_bIsRed == true
									&& node.m_bIsRed == true) {
								System.out.println("6ter Fall nach convert4node");
								if(greatGrandDad!=null)
									System.out.println("ggd "+greatGrandDad.m_Key);
								if(grandDad!=null)
									System.out.println("gd "+grandDad.m_Key);
								if(dad!=null)
									System.out.println("d "+dad.m_Key);
								if(node!=null)
									System.out.println("n "+node.m_Key);
								System.out.println();
								m_Root = node;
								grandDad.m_Right = node.m_Left;
								m_Root.m_Left = grandDad;
								dad.m_Left = node.m_Right;
								m_Root.m_Right = dad;

								m_Root.m_bIsRed = false;
								m_Root.m_Left.m_bIsRed = true;
							}

							// 7ter nach convert4node
							if (grandDad != null
									&& grandDad.m_Left.m_Right == node
									&& dad.m_bIsRed == true
									&& node.m_bIsRed == true) {
								System.out.println("7ter Fall nach convert4node");
								if(greatGrandDad!=null)
									System.out.println("ggd "+greatGrandDad.m_Key);
								if(grandDad!=null)
									System.out.println("gd "+grandDad.m_Key);
								if(dad!=null)
									System.out.println("d "+dad.m_Key);
								if(node!=null)
									System.out.println("n "+node.m_Key);
								System.out.println();
								m_Root = node;
								grandDad.m_Left = node.m_Right;
								m_Root.m_Right = grandDad;
								dad.m_Right = node.m_Left;
								m_Root.m_Left = dad;

								m_Root.m_bIsRed = false;
								m_Root.m_Right.m_bIsRed = true;
							}
						}

						else { // wenn was gefunden wird isses mitten im baum
								// oder greatGrandDad is root

							if (greatGrandDad.m_Left == grandDad) { // alles
																	// links
																	// unter
																	// greatGrandDad

								// 4ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Right.m_Right == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("4ter Fall nach convert4node mitten im baum Links unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Left = dad;
									dad.m_Right = node;
									grandDad.m_Right = dad.m_Left;
									dad.m_Left = grandDad;
									dad.m_bIsRed = false;
									grandDad.m_bIsRed=true;
									//node.m_bIsRed = false;
								}
								// 5ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Left.m_Left == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("5ter Fall nach convert4node mitten im baum Links unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Left = dad;
									dad.m_Left = node;
									grandDad.m_Left = dad.m_Right;
									dad.m_Right = grandDad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}

								// 6ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Right.m_Left == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("6ter Fall nach convert4node mitten im baum Links unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Left = node;
									grandDad.m_Right = node.m_Left;
									dad.m_Left = node.m_Right;
									node.m_Left = grandDad;
									node.m_Right = dad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}

								// 7ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Left.m_Right == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("7ter Fall nach convert4node mitten im baum Links unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Left = node;
									grandDad.m_Left = node.m_Right;
									dad.m_Right = node.m_Left;
									node.m_Right = grandDad;
									node.m_Left = dad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}

							}

							if (greatGrandDad.m_Right == grandDad) { // alles
																		// rechts
																		// unter
																		// greatGrandDad

								// 4ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Right.m_Right == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("4ter Fall nach convert4node mitten im baum Rechts unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Right = dad;
									dad.m_Right = node;
									grandDad.m_Right = dad.m_Left;
									dad.m_Left = grandDad;
									dad.m_bIsRed = false;
									grandDad.m_bIsRed=true;
									//node.m_bIsRed = false;
								}
								// 5ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Left.m_Left == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("5ter Fall nach convert4node mitten im baum Rechts unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Right = dad;
									dad.m_Left = node;
									grandDad.m_Left = dad.m_Right;
									dad.m_Right = grandDad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}

								// 6ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Right.m_Left == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("6ter Fall nach convert4node mitten im baum Rechts unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Right = node;
									grandDad.m_Right = node.m_Left;
									dad.m_Left = node.m_Right;
									node.m_Left = grandDad;
									node.m_Right = dad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}

								// 7ter nach convert4node
								if (grandDad != null
										&& grandDad.m_Left.m_Right == node
										&& dad.m_bIsRed == true
										&& node.m_bIsRed == true) {
									System.out.println("7ter Fall nach convert4node mitten im baum Rechts unter greatGrandDad");
									if(greatGrandDad!=null)
										System.out.println("ggd "+greatGrandDad.m_Key);
									if(grandDad!=null)
										System.out.println("gd "+grandDad.m_Key);
									if(dad!=null)
										System.out.println("d "+dad.m_Key);
									if(node!=null)
										System.out.println("n "+node.m_Key);
									System.out.println();
									greatGrandDad.m_Right = node;
									grandDad.m_Left = node.m_Right;
									dad.m_Right = node.m_Left;
									node.m_Right = grandDad;
									node.m_Left = dad;
									dad.m_bIsRed = false;
									node.m_bIsRed = false;
								}
							}
						}
						greatGrandDad = null;
						grandDad = null;
						dad = null;
						node = m_Root;
						while (node != null) {
							greatGrandDad = grandDad;
							grandDad = dad;
							dad = node;
							System.out.println("Fall Node NORMAL");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							node = (key.compareTo(node.m_Key) < 0 ? node.m_Left
									: node.m_Right);
						}
					}
				}
				if (node != null) {
					greatGrandDad = grandDad;
					grandDad = dad;
					dad = node;
					node = (key.compareTo(node.m_Key) < 0 ? node.m_Left
							: node.m_Right);
				}
			}
			if (key.compareTo(dad.m_Key) == 0)
				return false; // schlüssel bereits vorhanden
			
			System.out.println("und nochmal vorm insert");
			if(greatGrandDad!=null)
				System.out.println("ggd "+greatGrandDad.m_Key);
			if(grandDad!=null)
				System.out.println("gd "+grandDad.m_Key);
			if(dad!=null)
				System.out.println("d "+dad.m_Key);
			if(node!=null)
				System.out.println("n "+node.m_Key);
			System.out.println();
			
			// gerade links runter
			if (dad.m_bIsRed == true) { // an der stelle zum einfügen ist ein
										// 3er knoten
				if (greatGrandDad != null) {
					// --------------------------------------------------------
					// nicht root fälle
					if (greatGrandDad.m_Left == grandDad
							&& grandDad.m_Left == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("1ter Fall mitten im baum gerade links runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Left = dad;
							dad.m_Right = grandDad;
							dad.m_Left = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_Left = null;
							dad.m_Right.m_Right = null;
							return true;
						} else {
							System.out.println("2ter Fall mitten im baum gerade links runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							//GEÄNDERT
							greatGrandDad.m_Left = new Node<K, D>(key, data);
							greatGrandDad.m_Left.m_Left = dad;
							greatGrandDad.m_Left.m_Right = grandDad;
							greatGrandDad.m_Left.m_bIsRed = false;
							greatGrandDad.m_Left.m_Left.m_bIsRed = true;
							greatGrandDad.m_Left.m_Right.m_bIsRed = true;
							greatGrandDad.m_Left.m_Right.m_Left=null;
							return true;
						}
					}
					// sonderfall 1 blitz links
					if (greatGrandDad.m_Left == grandDad
							&& grandDad.m_Right == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("3ter Fall mitten im baum BLITZ LINKS gerade links runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Left = new Node<K, D>(key, data);
							greatGrandDad.m_Left.m_Left = grandDad;
							greatGrandDad.m_Left.m_Right = dad;
							greatGrandDad.m_Left.m_bIsRed = false;
							greatGrandDad.m_Left.m_Right.m_bIsRed = true;
							greatGrandDad.m_Left.m_Left.m_bIsRed = true;
							greatGrandDad.m_Left.m_Left.m_Left = null;
							greatGrandDad.m_Left.m_Left.m_Right = null;
							return true;
						} else {
							System.out.println("4ter Fall mitten im baum BLITZ LINKS ELSE gerade links runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Left = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						}
					}
					// gerade rechts runter
					if (greatGrandDad.m_Right == grandDad
							&& grandDad.m_Right == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("5ter Fall mitten im baum  gerade Rechts runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							
							greatGrandDad.m_Right = new Node<K, D>(key, data);
							greatGrandDad.m_Right.m_bIsRed=false;
							greatGrandDad.m_Right.m_Left=grandDad;
							greatGrandDad.m_Right.m_Right=dad;
							grandDad.m_bIsRed=true;
							dad.m_bIsRed=true;
							grandDad.m_Left = null;
							grandDad.m_Right = null;
							return true;
						} else {
							System.out.println("6ter Fall mitten im baum  gerade Rechts runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Right = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						}
					}
					// sonderfall 2 blitz rechts
					if (greatGrandDad.m_Right == grandDad
							&& grandDad.m_Left == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("7ter Fall mitten im baum  gerade Rechts runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Right = dad;
							greatGrandDad.m_Right.m_Left = new Node<K, D>(key,
									data);
							greatGrandDad.m_Right.m_Right = grandDad;
							greatGrandDad.m_Right.m_bIsRed = false;
							greatGrandDad.m_Right.m_Right.m_bIsRed = true;
							greatGrandDad.m_Right.m_Left.m_bIsRed = true;
							greatGrandDad.m_Right.m_Right.m_Left = null;
							greatGrandDad.m_Right.m_Right.m_Right = null;
							return true;
						} else {
							System.out.println("8ter Fall mitten im baum  gerade Rechts runter unter greatGrandDad");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							greatGrandDad.m_Right = new Node<K, D>(key, data);
							greatGrandDad.m_Right.m_Right = grandDad;
							greatGrandDad.m_Right.m_Left = dad;
							greatGrandDad.m_Right.m_bIsRed = false;
							greatGrandDad.m_Right.m_Left.m_bIsRed = true;
							greatGrandDad.m_Right.m_Right.m_bIsRed = true;
							greatGrandDad.m_Right.m_Right.m_Left = null;
							greatGrandDad.m_Right.m_Right.m_Right = null;
							return true;
						}
					}
				} else {
					// --------------------------------------------------------
					// root fälle
					if (m_Root == grandDad && grandDad.m_Left == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("1ter Fall an ROOT LINKS");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Right = grandDad;
							dad.m_Left = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_Left = null;
							dad.m_Right.m_Right = null;
							return true;
						} else {
							System.out.println("2ter Fall an ROOT");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Right = grandDad;
							dad.m_Left = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Right.m_Left = null;
							dad.m_Right.m_Right = null;
							return true;
						}
					}
					// sonderfall 1 blitz links
					if (m_Root == grandDad && grandDad.m_Right == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("3ter Fall an ROOT BLITZ LINKS");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = new Node<K, D>(key, data);
							m_Root.m_Left = grandDad;
							m_Root.m_Right = dad;
							m_Root.m_bIsRed = false;
							m_Root.m_Right.m_bIsRed = true;
							m_Root.m_Left.m_bIsRed = true;
							m_Root.m_Left.m_Left = null;
							m_Root.m_Left.m_Right = null;
							return true;
						} else {
							System.out.println("4ter Fall an ROOT BLITZ LINKS ELSE");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						}
					}
					// gerade rechts runter
					if (m_Root == grandDad && grandDad.m_Right == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("5ter Fall an ROOT Gerade Rechts runter");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						} else {
							System.out.println("6ter Fall an ROOT Gerade Rechts runter");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						}
					}
					// sonderfall 2 blitz rechts
					if (m_Root == grandDad && grandDad.m_Left == dad) {
						if (key.compareTo(dad.m_Key) < 0) {
							System.out.println("7ter Fall an ROOT BLITZ Rechts runter");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Right.m_Left = new Node<K, D>(key, data);
							dad.m_Right.m_Right = grandDad;
							dad.m_Right.m_bIsRed = false;
							dad.m_Right.m_Right.m_bIsRed = true;
							dad.m_Right.m_Left.m_bIsRed = true;
							dad.m_Right.m_Right.m_Left = null;
							dad.m_Right.m_Right.m_Right = null;
							return true;
						} else {
							System.out.println("8ter Fall an ROOT BLITZ Rechts ELSE runter");
							if(greatGrandDad!=null)
								System.out.println("ggd "+greatGrandDad.m_Key);
							if(grandDad!=null)
								System.out.println("gd "+grandDad.m_Key);
							if(dad!=null)
								System.out.println("d "+dad.m_Key);
							if(node!=null)
								System.out.println("n "+node.m_Key);
							System.out.println();
							m_Root = dad;
							dad.m_Left = grandDad;
							dad.m_Right = new Node<K, D>(key, data);
							dad.m_bIsRed = false;
							dad.m_Left.m_bIsRed = true;
							dad.m_Right.m_bIsRed = true;
							dad.m_Left.m_Left = null;
							dad.m_Left.m_Right = null;
							return true;
						}
					}
				}
			}

			if (key.compareTo(dad.m_Key) < 0) {
				dad.m_Left = new Node<K, D>(key, data);
			} else {
				dad.m_Right = new Node<K, D>(key, data);
			}
		}
		return true;
	}

	// Methoden ------------------------------------------
	@Override
	public Node<K, D> getRoot() {
		return m_Root;
	}

	// print bintree methode ausm internet--------------------------------
	public void printBinaryTree() {
		System.out.println("");
		System.out.println("");
		printBinaryTree(m_Root, 0);
		System.out.println("");
		System.out.println("");
	}

	public void printBinaryTree(Node<K, D> node, int level) {
		if (node == null)
			return;
		printBinaryTree(node.m_Right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			if (node.m_bIsRed == true)
				System.out.println("|--rot--" + node.m_Key + " [ "
						+ node.m_Data + " ]");
			else
				System.out.println("|-------" + node.m_Key + " [ "
						+ node.m_Data + " ]");
		} else
			System.out.println(node.m_Key);
		printBinaryTree(node.m_Left, level + 1);
	}

	// print bintree data--------------------------------
	public void printData() {
		printData(m_Root);
	}

	public void printData(Node<K, D> node) {
		if (node != null) {
			printData(node.m_Left);
			System.out.println(node.m_Key + " | " + node.m_Data);
			printData(node.m_Right);
		}
	}

	// treeDept ------------------------------------
	public int treeDept() {
		return treeDept(m_Root, -1);
	}

	public int treeDept(Node<K, D> node, int maxDept) {
		if (node.m_bIsRed != true)
			maxDept++;

		int maxDeptLeft = 0;
		int maxDeptRight = 0;

		if (node.m_Left != null)
			maxDeptLeft = treeDept(node.m_Left, maxDept);
		if (node.m_Right != null)
			maxDeptRight = treeDept(node.m_Right, maxDept);

		if (maxDeptLeft > maxDept)
			maxDept = maxDeptLeft;
		if (maxDeptRight > maxDept)
			maxDept = maxDeptRight;

		return maxDept;
	}
}
