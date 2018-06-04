package jedi.cinepolis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements IMDBTask.onIMDBItemAvailable, GridView.OnItemClickListener {

    private Context context;
    private int mLongAnimationDuration;
    private GridView imdbGridView;
    private IMDBAdapter imdbAdapter;
    private BottomNavigationView mBottomNav;
    private ArrayList<IMDBItem> imdbItems = new ArrayList<IMDBItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        setContentView(R.layout.activity_main);

        //Override de standaard transition met fadein / fadeout animatie
        overridePendingTransition(R.anim.stay, R.anim.stay);

        getSupportActionBar().setTitle("Nu in de biscoop");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        //Koppel de MoviePosterAdapter aan de GridView
        imdbGridView = (GridView) findViewById(R.id.moviesGv);
        imdbAdapter = new IMDBAdapter(this, imdbItems);
        imdbGridView.setAdapter(imdbAdapter);
        imdbAdapter.notifyDataSetChanged();
        imdbGridView.setOnItemClickListener(this);

        imdbGridView.animate()
                .alpha(1)
                .setDuration(500);



        // Clear list
        this.imdbItems.clear();

        // GET movies
        IMDBTask repoTask = new IMDBTask(this);
        String[] urls = new String[]
                {"https://api.themoviedb.org/3/movie/upcoming?api_key=863618e1d5c5f5cc4e34a37c49b8338e"};
        repoTask.execute(urls);


        final ImageButton btn1 = (ImageButton)findViewById(R.id.navbtn1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.navbtn2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.navbtn3);
        ImageButton btn4 = (ImageButton)findViewById(R.id.navbtn4);
        final ImageView btn1v = (ImageView)findViewById(R.id.navbtn1v);
        btn1v.setVisibility(View.VISIBLE);
        final ImageView btn2v = (ImageView)findViewById(R.id.navbtn2v);
        ImageView btn3v = (ImageView)findViewById(R.id.navbtn3v);
        final ImageView btn4v = (ImageView)findViewById(R.id.navbtn4v);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.INVISIBLE);
                btn2v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.INVISIBLE);
                btn4v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), BiosInfoActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public void onIMDBItemAvailable(IMDBItem item) {
        imdbItems.add(item);
        imdbAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        view.setTransitionName("selectedPoster");
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, view.getTransitionName());

        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        IMDBItem item = (IMDBItem) this.imdbItems.get(i);
        intent.putExtra("IMDBITEM",item);
                startActivity(intent, optionsCompat.toBundle());

    }



}
