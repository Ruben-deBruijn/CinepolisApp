package jedi.cinepolis;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Operator on 3/30/2017.
 */

public class GenreItem implements Serializable {

    public String getGenreNaam() {
        return genreNaam;
    }

    public GenreItem setGenreNaam(String genreNaam) {
        this.genreNaam = genreNaam;
        return this;
    }

    public String getGenreId() {
        return genreId;
    }

    public GenreItem setGenreId(String genreId) {
        this.genreId = genreId;
        return this;
    }

    @Override
    public String toString() {
        return "GenreItem{" +
                "genreNaam='" + genreNaam + '\'' +
                ", genreId='" + genreId + '\'' +
                '}';
    }

    public String genreNaam;
    public String genreId;
    }


