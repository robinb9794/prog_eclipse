package controller;

import model.Model;
import view.GUI;

public class Controller {
	private Model m_Mod;
	private GUI m_View;
	public Controller(){
		m_Mod = new Model(500,500);
		m_View = new GUI(m_Mod);
	}
}
