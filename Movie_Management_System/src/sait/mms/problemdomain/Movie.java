package sait.mms.problemdomain;

public class Movie {

	private int id;
	private int duration;
	private String title;
	private int year;
	
	/**
	 * Movie constructor with id, duration, title, and year 
	 * 
	 * @param id movie's id
	 * @param duration movie's duration
	 * @param year the release year
	 * @param title the title of movie
	 */
	
	public Movie(int id, int duration, int year, String title) {
		super();
		this.id = id;
		this.duration = duration;
		this.title = title;
		this.year = year;
	}
	
	/**
	 * Movie constructor with duration, title, and year 
	 * 
	 * @param duration movie's duration
	 * @param year the the release date
	 * @param title the title of movie
	 */
	
	public Movie( int duration, int year, String title) {
		super();
		this.duration = duration;
		this.title = title;
		this.year = year;
	}

	/**
	 * returns the id of movie.
	 *
	 * @return the id
	 */

	public int getId() {
		return id;
	}
	/**
	 * sets the id of movie.
	 *
	 * @param id the id of movie
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * returns the duration of movie.
	 *
	 * @return the duration
	 */
	
	public int getDuration() {
		return duration;
	}
	/**
	 * sets the duration of movie.
	 *
	 * @param duration the duration of movie
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * returns the title of movie.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the title of movie.
	 *
	 * @param title the title of movie
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * returns the year of movie.
	 *
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * sets the release year.
	 *
	 * @param year the release year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", duration=" + duration + ", title=" + title + ", year=" + year + "]";
	}
	
	
}
