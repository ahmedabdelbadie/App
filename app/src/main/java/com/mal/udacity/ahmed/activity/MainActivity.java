package com.mal.udacity.ahmed.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.mal.udacity.ahmed.R;
import com.mal.udacity.ahmed.fragment.DetailsFragment;
import com.mal.udacity.ahmed.fragment.MoviesFragment;
import com.mal.udacity.ahmed.prefs.PrefsController;

public class MainActivity extends AppCompatActivity {
   public MoviesFragment moviesFragment;
   public DetailsFragment detailsFragment;
    boolean isMyPhoneTablet;
    private PrefsController prefsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.moviesFragment);
        detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.detailsFragment);
        prefsController = PrefsController.getInstance(this);

        if (detailsFragment != null) isMyPhoneTablet = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_fragment_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_sort_by_popularity:
                if(!prefsController.getSortBy().equals("popular")){
                    prefsController.saveSort("popular");
                    moviesFragment.start("popular");
                }
                return true ;

            case R.id.action_sort_by_rating:
                if(!prefsController.getSortBy().equals("rated")){
                    prefsController.saveSort("rated");
                    moviesFragment.start("rated");
                }
                return true ;

            case R.id.action_show_favourites:
                if(!prefsController.getSortBy().equals("favorite")){
                    prefsController.saveSort("favorite");
                    moviesFragment.start("favorite");
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isMyPhoneTablet() {
        return isMyPhoneTablet;
    }
}

