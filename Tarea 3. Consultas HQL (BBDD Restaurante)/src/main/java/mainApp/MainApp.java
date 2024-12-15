package mainApp;

import java.sql.Date;

import model.Existencias;
import model.Localidad;
import model.Restaurante;
import model.Titular;
import utils.HibernateUtil;

public class MainApp {
		
	public static void main(String[] args) {
		Localidad localidad = new Localidad(31, "Rincon");
		HibernateUtil.insertLocalidad(localidad);
		
		Restaurante restaurante = new Restaurante("R001", "Restaurante1", "1234567890", "Calle Falsa 1",
				Date.valueOf("2024-11-22"), "08:00 - 22:00", localidad);
		HibernateUtil.insertRestaurante(restaurante);
		
		Existencias hamburguesas = new Existencias("HAMB001", "Hamburguesas", 100, 5.99, "R001");
		HibernateUtil.insertExistencias(hamburguesas);
		
		Titular titular = new Titular("11111111A", "Paco Makako", "Calle 1", "R001");
		HibernateUtil.insertTitular(titular);
		
		HibernateUtil.selectRestaurantes();
		HibernateUtil.selectConJoin();
		HibernateUtil.selectConGroupBy();
	}

}
