package mainApp;

import utils.HibernateUtil;

public class MainApp {
	
	private static final String RUTA = "Alumnado_nuevo.txt";
	
	public static void main(String[] args) {
		HibernateUtil.aniadirUsuariosCSV(RUTA);
	}

}
