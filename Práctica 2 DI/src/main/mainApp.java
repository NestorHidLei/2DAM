package main;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import swing.swingApp;
import utils.Clientes;

public class mainApp {
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					swingApp frame = new swingApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

	}

}
