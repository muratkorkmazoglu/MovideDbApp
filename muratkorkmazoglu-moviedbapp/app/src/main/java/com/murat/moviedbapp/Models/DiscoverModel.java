package com.murat.moviedbapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscoverModel {
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

    @Getter
    @Setter
    public class Result {

        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("video")
        @Expose
        public Boolean video;
        @SerializedName("vote_average")
        @Expose
        public Float voteAverage;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("popularity")
        @Expose
        public Float popularity;
        @SerializedName("poster_path")
        @Expose
        public String posterPath;
        @SerializedName("original_language")
        @Expose
        public String originalLanguage;
        @SerializedName("original_title")
        @Expose
        public String originalTitle;
        @SerializedName("genre_ids")
        @Expose
        public List<Integer> genreIds = null;
        @SerializedName("backdrop_path")
        @Expose
        public String backdropPath;
        @SerializedName("adult")
        @Expose
        public Boolean adult;
        @SerializedName("overview")
        @Expose
        public String overview;
        @SerializedName("release_date")
        @Expose
        public String releaseDate;

    }
}
