package mainApp;

import java.sql.SQLException;

import dao.CityDao;
import dao.CityDaoImpl;
import model.City;

public class MainApp {
	
	public static void main(String[] args) throws Exception {
		testDao();
		
	}
	
	public static void testDao() {
		CityDao dao = CityDaoImpl.getInstance();
		
		City city = new City("Ciudad Nueva 1", "ESP", "Andalusia", 1200);
		
		try {
			int n = dao.add(city);
			System.out.println("Se ha insertado" + n);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
