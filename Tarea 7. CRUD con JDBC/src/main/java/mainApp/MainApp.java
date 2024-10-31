package mainApp;

import java.sql.SQLException;
import java.util.List;

import dao.CityDao;
import dao.CityDaoImpl;
import model.City;

public class MainApp {

	public static void main(String[] args) throws Exception {
//		testDaoAñadir();
//		testDaoListar();
//		testDaoGetId();
//		testUpdate();
//		testBorrar();
	}

	public static void testDaoAñadir() {
		CityDao dao = CityDaoImpl.getInstance();

		City city = new City("Ciudad Nueva 1", "ESP", "Andalusia", 1200);

		try {
			int n = dao.add(city);
			System.out.println("Se ha insertado" + n);

			List<City> Cities = dao.getAll();
			if (Cities.isEmpty()) {
				System.out.println("No hay ciudades");
			} else {
				Cities.forEach(System.out::println);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDaoListar() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			List<City> Cities = dao.getAll();
			if (Cities.isEmpty()) {
				System.out.println("No hay ciudades");
			} else {
				Cities.forEach(System.out::println);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testDaoGetId() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			City City = dao.getById(1);
			System.out.println(City);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testUpdate() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			City city = dao.getById(1);
			city.setName("Not-Kabul");
			dao.update(city);
			city = dao.getById(1);
			System.out.println(city);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testBorrar() {
		CityDao dao = CityDaoImpl.getInstance();

		try {
			dao.delete(4080);
			System.out.println("Se borro");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
