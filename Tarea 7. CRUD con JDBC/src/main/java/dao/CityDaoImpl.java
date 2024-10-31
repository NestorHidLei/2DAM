package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.Conexion;
import model.City;

public class CityDaoImpl implements CityDao {

	private static CityDaoImpl instance;

	static {
		instance = new CityDaoImpl();
	}

	private CityDaoImpl() {
	}

	public static CityDaoImpl getInstance() {
		return instance;
	}

	@Override
	public int add(City city) throws SQLException {
		String sql = "INSERT INTO City(name, countryCode, district, population) VALUES(?,?,?,?);";

		try (Connection conn = Conexion.conectar(); PreparedStatement pstm = conn.prepareStatement(sql)) {
			pstm.setString(1, city.getName());
			pstm.setString(2, city.getCountryCode());
			pstm.setString(3, city.getDistrict());
			pstm.setInt(4, city.getPopulation());

			return pstm.executeUpdate();
		}
	}

	@Override
	public City getById(int id) throws SQLException {
		String sql = "SELECT * FROM city where ID = ?;";
		City result = null;

		try (Connection conn = Conexion.conectar(); PreparedStatement pstm = conn.prepareStatement(sql);) {
			pstm.setInt(1, id);

			try (ResultSet rs = pstm.executeQuery()) {
				while (rs.next()) {
					result = new City();
					result.setId(rs.getInt("ID"));
					result.setName(rs.getString("Name"));
					result.setCountryCode(rs.getString("CountryCode"));
					result.setDistrict(rs.getString("District"));
					result.setPopulation(rs.getInt("Population"));
				}
			}

		}

		return result;
	}

	@Override
	public List<City> getAll() throws SQLException {
		String sql = "SELECT * FROM city";
		List<City> result = new ArrayList<City>();

		try (Connection conn = Conexion.conectar();
				PreparedStatement pstm = conn.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery()) {
			City city;
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("ID"));
				city.setName(rs.getString("Name"));
				city.setCountryCode(rs.getString("CountryCode"));
				city.setDistrict(rs.getString("District"));
				city.setPopulation(rs.getInt("Population"));
				result.add(city);
			}
		}

		return result;
	}

	@Override
	public int update(City city) throws SQLException {
		String sql = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?;";
		
		int result;
		
		try(Connection conn = Conexion.conectar();
				PreparedStatement pstm = conn.prepareStatement(sql)){
			pstm.setString(1, city.getName());
			pstm.setString(2, city.getCountryCode());
			pstm.setString(3, city.getDistrict());
			pstm.setInt(4, city.getPopulation());
			pstm.setInt(5, city.getId());
			
			result = pstm.executeUpdate();
		}
		
		return result;
	}

	@Override
	public void delete(int id) throws SQLException {
		String sql = "DELETE FROM city where ID = ?;";
		try(Connection conn = Conexion.conectar();
				PreparedStatement pstm = conn.prepareStatement(sql)){
			pstm.setInt(1, id);
			pstm.executeUpdate();
		}
	}

}
