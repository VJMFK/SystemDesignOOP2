package com.example.systemdesignoop2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Movie with a title and a genre.
 */
public class Movie {

    /** The title of the movie. */
    private String aTitle;

    /** The genre of the movie. */
    private String aGenre;

    /**
     * Constructs a Movie with the specified title and genre.
     *
     * @param pTitle the title of the movie
     * @param pGenre the genre of the movie
     */
    public Movie(String pTitle, String pGenre) {
        this.aTitle = pTitle;
        this.aGenre = pGenre;
    }

    /**
     * Returns the title of the movie.
     *
     * @return the title of the movie
     */
    public String getTitle() {
        return aTitle;
    }

    /**
     * Sets the title of the movie.
     *
     * @param pTitle the new title of the movie
     */
    public void setTitle(String pTitle) {
        this.aTitle = pTitle;
    }

    /**
     * Returns the genre of the movie.
     *
     * @return the genre of the movie
     */
    public String getGenre() {
        return aGenre;
    }

    /**
     * Sets the genre of the movie.
     *
     * @param pGenre the new genre of the movie
     */
    public void setGenre(String pGenre) {
        this.aGenre = pGenre;
    }

    /**
     * Returns a string representation of the movie in the format:
     * "Title - Genre".
     *
     * @return a string representation of the movie
     */
    @Override
    public String toString() {
        return aTitle + " - " + aGenre;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the object to compare
     * @return true if this object is the same as the object argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(aTitle, movie.aTitle) &&
                Objects.equals(aGenre, movie.aGenre);
    }

    /**
     * Loads a list of movies from a resource file.
     *
     * @param fileName the name of the resource file (e.g., "/movies.txt")
     * @return a list of movies loaded from the file
     */
    public static List<Movie> loadMoviesFromFile(String fileName) {
        List<Movie> movieList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                Movie.class.getResourceAsStream(fileName)))) {

            String line;
            while ((line = br.readLine()) != null) {
                // Parse each line into title and genre
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String title = parts[0];
                    String genre = parts[1].replace(".", ""); // Remove trailing period
                    movieList.add(new Movie(title, genre));
                }
            }
        } catch (IOException | NullPointerException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return movieList;
    }
}