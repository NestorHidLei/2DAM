package utils;

import java.sql.Timestamp;

public class Film {

	private int film_id;
	private String title;
	private String description;
	private int relase_year;
	private int language_id;
	private int original_language_id;
	private int rental_duration;
	private double rental_rate;
	private int length;
	private double replacement_cost;
	private String raiting;
	private String special_features;
	private Timestamp last_update;
	
	
	public Film(int film_id, String title, String description, int relase_year, int language_id,
			int original_language_id, int rental_duration, double rental_rate, int length, double replacement_cost,
			String raiting, String special_features, Timestamp last_update) {
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.relase_year = relase_year;
		this.language_id = language_id;
		this.original_language_id = original_language_id;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.raiting = raiting;
		this.special_features = special_features;
		this.last_update = last_update;
	}


	@Override
	public String toString() {
	    return "🎬🎞️ FILM DETAILS 🎞️🎬\n" +
	           "--------------------------\n" +
	           "📽️ Film ID: " + film_id + "\n" +
	           "🎬 Title: " + title + "\n" +
	           "📝 Description: " + description + "\n" +
	           "📅 Release Year: " + relase_year + "\n" +
	           "🗣️ Language ID: " + language_id + "\n" +
	           "🌍 Original Language ID: " + original_language_id + "\n" +
	           "⏳ Rental Duration: " + rental_duration + " days\n" +
	           "💰 Rental Rate: $" + rental_rate + "\n" +
	           "⏱️ Length: " + length + " minutes\n" +
	           "💵 Replacement Cost: $" + replacement_cost + "\n" +
	           "⭐ Rating: " + raiting + "\n" +
	           "🎁 Special Features: " + special_features + "\n" +
	           "🔄 Last Update: " + last_update + "\n" +
	           "--------------------------";
	}


	public int getFilm_id() {
		return film_id;
	}


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public int getRelase_year() {
		return relase_year;
	}


	public int getLanguage_id() {
		return language_id;
	}


	public int getOriginal_language_id() {
		return original_language_id;
	}


	public int getRental_duration() {
		return rental_duration;
	}


	public double getRental_rate() {
		return rental_rate;
	}


	public int getLength() {
		return length;
	}


	public double getReplacement_cost() {
		return replacement_cost;
	}


	public String getRaiting() {
		return raiting;
	}


	public String getSpecial_features() {
		return special_features;
	}


	public Timestamp getLast_update() {
		return last_update;
	}

	


}
