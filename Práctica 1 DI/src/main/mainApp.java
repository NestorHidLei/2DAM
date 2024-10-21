package main;

import java.awt.EventQueue;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

import resources.Clientes;
import swing.swingApp;

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
