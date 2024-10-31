package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import conexion.Conexion;
import model.City;

public class CityDaoImpl implements CityDao{

	private static CityDaoImpl instance;
	
	static {
		instance = new CityDaoImpl();
	}
	
	private CityDaoImpl() {}
	
	public static CityDaoImpl getInstance(){
		return instance;
	}
	
	
	@Override
	public int add(City city) throws SQLException {
		String sql = "INSERT INTO City(name, countryCode, district, population) VALUES(?,?,?,?);";
		
		try(Connection conn = Conexion.conectar();
			PreparedStatement pstm = conn.prepareStatement(sql)){
			pstm.setString(1, city.getName());
			pstm.setString(2, city.getCountryCode());
			pstm.setString(3, city.getDistrict());
			pstm.setInt(4, city.getPopulation());
			
			return pstm.executeUpdate();
		}
	}

	@Override
	public City getById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(City city) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
