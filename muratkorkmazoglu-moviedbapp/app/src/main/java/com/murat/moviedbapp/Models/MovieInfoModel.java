package com.murat.moviedbapp.Models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInfoModel implements Serializable {
    String title, image, date, overview;
    int movieId;

}
