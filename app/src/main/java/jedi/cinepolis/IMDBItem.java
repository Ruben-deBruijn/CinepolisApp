package jedi.cinepolis;

import java.io.Serializable;

/**
 * Created by Operator on 3/27/2017.
 */

public class IMDBItem implements Serializable {
    public String getFilmTitel() {
        return filmTitel;
    }

    public IMDBItem setFilmTitel(String filmTitel) {
        this.filmTitel = filmTitel;
        return this;
    }

    public String getFilmJaar() {
        return filmJaar;
    }

    public IMDBItem setFilmJaar(String filmJaar) {
        this.filmJaar = filmJaar;
        return this;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public IMDBItem setFilmRating(String filmRating) {
        this.filmRating = filmRating;
        return this;
    }

    public String getFilmBeschrijving() {
        return filmBeschrijving;
    }

    public IMDBItem setFilmBeschrijving(String filmTijdsduur) {
        this.filmBeschrijving = filmTijdsduur;
        return this;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public IMDBItem setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
        return this;
    }


    public String getFilmActeurs() {
        return filmActeurs;
    }

    public IMDBItem setFilmActeurs(String filmActeurs) {
        this.filmActeurs = filmActeurs;
        return this;
    }

    public String getFilmPlot() {
        return filmPlot;
    }

    public IMDBItem setFilmPlot(String filmPlot) {
        this.filmPlot = filmPlot;
        return this;
    }

    public String getFilmTaal() {
        return filmTaal;
    }

    public IMDBItem setFilmTaal(String filmTaal) {
        this.filmTaal = filmTaal;
        return this;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public IMDBItem setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        return this;
    }

    public String filmTitel;
    public String filmJaar;
    public String filmRating;
    public String filmBeschrijving;
    public String filmGenre;

    public String getFilmBg() {
        return filmBg;
    }

    public IMDBItem setFilmBg(String filmBg) {
        this.filmBg = filmBg;
        return this;
    }

    @Override
    public String toString() {
        return "IMDBItem{" +
                "filmTitel='" + filmTitel + '\'' +
                ", filmJaar='" + filmJaar + '\'' +
                ", filmRating='" + filmRating + '\'' +
                ", filmBeschrijving='" + filmBeschrijving + '\'' +
                ", filmGenre='" + filmGenre + '\'' +
                ", filmBg='" + filmBg + '\'' +
                ", filmPosterUrl='" + filmPosterUrl + '\'' +
                ", filmActeurs='" + filmActeurs + '\'' +
                ", filmPlot='" + filmPlot + '\'' +
                ", filmTaal='" + filmTaal + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }

    public String filmBg;

    public String getFilmPosterUrl() {
        return filmPosterUrl;
    }

    public IMDBItem setFilmPosterUrl(String filmPosterUrl) {
        this.filmPosterUrl = filmPosterUrl;
        return this;
    }

    public String filmPosterUrl;

    public String filmActeurs;
    public String filmPlot;
    public String filmTaal;
    public String imdbRating;
}
