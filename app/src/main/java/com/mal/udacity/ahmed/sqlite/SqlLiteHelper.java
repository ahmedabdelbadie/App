package com.mal.udacity.ahmed.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mal.udacity.ahmed.model.MovieModel;
import java.util.ArrayList;

public class SqlLiteHelper extends SQLiteOpenHelper {
    private static SqlLiteHelper instance;

    private SqlLiteHelper(Context context) {
        super(context, "M", null, 1);
    }

    public static synchronized SqlLiteHelper getInstance(Context ctx) {
        if (instance == null) {
            instance = new SqlLiteHelper(ctx.getApplicationContext());
        }
        return instance;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public long setFav(MovieModel m){
        ContentValues values = new ContentValues();
        values.put("poster_path" , m.getPosterPath());
        values.put("adult" , m.getAdult());
        values.put("overview" , m.getOverview());
        values.put("release_date" , m.getReleaseDate());
        values.put("movie_id", m.getId());
        values.put("original_title" , m.getOriginalTitle());
        values.put("original_language" , m.getOriginalLang());
        values.put("title" , m.getTitle());
        values.put("backdrop_path" , m.getBackdrop());
        values.put("popularity" , m.getPopularity());
        values.put("vote_count" , m.getVoteCount());
        values.put("video" , m.getVideo());
        values.put("vote_average" , m.getVoteAverage());

        return getWritableDatabase().insert("movies", null, values);
    }

    public ArrayList<MovieModel> getFav(){
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + "movies", null);

        if (cursor == null || cursor.getCount() <= 0){
            if (cursor != null) cursor.close();
            return null;
        }

        ArrayList<MovieModel> MovieModelList = new ArrayList<>();

        while (cursor.moveToNext()){
            MovieModel MovieModel = new MovieModel(cursor.getString(1),cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5),
                    cursor.getString(6), cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12),
                    cursor.getString(13));

            MovieModelList.add(MovieModel);
        }

        cursor.close();
        return MovieModelList;
    }

    public boolean isFav(String id){
        Cursor cursor = getReadableDatabase()
                .query(
                        "movies",
                        new String[]{"_id"},
                        "movie_id" + "=? ",
                        new String[]{id},
                        null, null, null);

        if (cursor == null || cursor.getCount() <= 0){
            if (cursor != null) cursor.close();
            return false;
        }
        return true;
    }

    public int deleteFav(String id){
        return getWritableDatabase().delete("movies", "movie_id" + "=? ", new String[]{id});
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dbCreateStatement = "CREATE TABLE " +
                "movies" +
                "(" +
                "_id" + " INTEGER PRIMARY KEY," +  "poster_path" +
                " TEXT," + "adult" +
                " TEXT," +
                "overview" +
                " TEXT," +
                "release_date" +
                " TEXT," +
                "movie_id" +
                " TEXT," +
                "original_title" +
                " TEXT," +
                "original_language" +
                " TEXT," +
                "title" +
                " TEXT," +
                "backdrop_path" +
                " TEXT," +
                "popularity" +
                " TEXT," +
                "vote_count" +
                " TEXT," +
                "video" +
                " TEXT," +
                "vote_average" +
                " TEXT" +
                " )";

        db.execSQL(dbCreateStatement);
    }
}
