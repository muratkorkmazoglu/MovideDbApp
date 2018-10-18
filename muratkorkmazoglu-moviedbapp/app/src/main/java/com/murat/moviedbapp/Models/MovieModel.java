package com.murat.moviedbapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieModel {
    @SerializedName("adult")
    @Expose
    public Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    public BelongsToCollection belongsToCollection;
    @SerializedName("budget")
    @Expose
    public Integer budget;
    @SerializedName("genres")
    @Expose
    public List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    public String homepage;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("imdb_id")
    @Expose
    public String imdbId;
    @SerializedName("original_language")
    @Expose
    public String originalLanguage;
    @SerializedName("original_title")
    @Expose
    public String originalTitle;
    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("popularity")
    @Expose
    public Float popularity;
    @SerializedName("poster_path")
    @Expose
    public String posterPath;
    @SerializedName("production_companies")
    @Expose
    public List<ProductionCompany> productionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    public List<ProductionCountry> productionCountries = null;
    @SerializedName("release_date")
    @Expose
    public String releaseDate;
    @SerializedName("revenue")
    @Expose
    public Integer revenue;
    @SerializedName("runtime")
    @Expose
    public Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    public List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("video")
    @Expose
    public Boolean video;
    @SerializedName("vote_average")
    @Expose
    public Float voteAverage;
    @SerializedName("vote_count")
    @Expose
    public Integer voteCount;

    @Getter
    @Setter
    public class BelongsToCollection {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;

    }

    @Getter
    @Setter
    public class ProductionCompany {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("logo_path")
        @Expose
        public String logoPath;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("origin_country")
        @Expose
        public String originCountry;

    }

    @Getter
    @Setter
    public class ProductionCountry {

        @SerializedName("iso_3166_1")
        @Expose
        public String iso31661;
        @SerializedName("name")
        @Expose
        public String name;

    }

    @Getter
    @Setter
    public class SpokenLanguage {

        @SerializedName("iso_639_1")
        @Expose
        public String iso6391;
        @SerializedName("name")
        @Expose
        public String name;

    }
    @Getter
    @Setter
    public class Genre {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;

    }
}
