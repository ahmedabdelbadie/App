package com.mal.udacity.ahmed.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abo Abdel-Badie on 10/25/2016.
 */

public class MovieModel implements Parcelable {

    private String posterPath;
    private String adult;
    private String overview;
    private String releaseDate;
    private String id;
    private String originalTitle;
    private String originalLang;
    private String title;
    private String backdrop;
    private String popularity;
    private String voteCount;
    private String video;
    private String voteRange;

    public MovieModel(String posterPath, String adult,
            String overview, String releaseDate,
            String id,
            String originalTitle,
            String originalLang,
            String title,
            String backdrop,
            String popularity,
            String voteCount,
            String video,
            String voteRange) {

        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.id = id;
        this.originalTitle = originalTitle;
        this.originalLang = originalLang;
        this.title = title;
        this.backdrop = backdrop;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteRange = voteRange;
    }


    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    private MovieModel(Parcel in) {
        posterPath = in.readString(); adult = in.readString();
        overview = in.readString(); releaseDate = in.readString();
        id = in.readString();
        originalTitle = in.readString();
        originalLang = in.readString();
        title = in.readString();
        backdrop = in.readString();
        popularity = in.readString();
        voteCount = in.readString();
        video = in.readString();
        voteRange = in.readString();
    }


    public String getPosterPath() {
        return posterPath;
    }

    public String getAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getId() {
        return id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String getVideo() {
        return video;
    }

    public String getVoteAverage() {
        return voteRange;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(adult);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(id);
        dest.writeString(originalTitle);
        dest.writeString(originalLang);
        dest.writeString(title);
        dest.writeString(backdrop);
        dest.writeString(popularity);
        dest.writeString(voteCount);
        dest.writeString(video);
        dest.writeString(voteRange);
    }
}

