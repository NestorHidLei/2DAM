package model;

import java.time.LocalDate;

public class City {

	private int id;
	private String name;
	private String countryCode;
	private String district;
	private int population;

	/**
	 * Constructor vacio
	 */
	public City() {}
	
	/**
	 * Constructor City
	 * @param id - id de la city
	 * @param name - nombre de la city
	 * @param countryCode - countryCode de la city
	 * @param district - distritos de la city
	 * @param population - población de la city
	 */
	public City(String name, String countryCode, String district, int population) {
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}

	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	// Método toString para mostrar la información de la ciudad
	@Override
	public String toString() {
		return "City{" + "id=" + id + ", name='" + name + '\'' + ", countryCode='" + countryCode + '\'' + ", district='"
				+ district + '\'' + ", population=" + population + '}';
	}
}
