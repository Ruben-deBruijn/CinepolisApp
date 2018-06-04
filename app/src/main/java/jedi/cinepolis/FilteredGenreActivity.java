package jedi.cinepolis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FilteredGenreActivity extends AppCompatActivity implements IMDBTask.onIMDBItemAvailable, GridView.OnItemClickListener{

    private GridView imdbGridView;
    private IMDBAdapter imdbAdapter;
    private ArrayList<IMDBItem> imdbItems = new ArrayList<IMDBItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);
        super.onCreate(savedInstanceState);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        setContentView(R.layout.activity_filtered_genre);

        TextView genreTv = (TextView)findViewById(R.id.genreTxt);
        ImageView genreThumb = (ImageView)findViewById(R.id.genreBg);
        Intent genreIntent = getIntent();
        Bundle b = genreIntent.getExtras();

        final ImageButton btn1 = (ImageButton)findViewById(R.id.navbtn1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.navbtn2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.navbtn3);
        ImageButton btn4 = (ImageButton)findViewById(R.id.navbtn4);
        final ImageView btn1v = (ImageView)findViewById(R.id.navbtn1v);
        btn1v.setVisibility(View.INVISIBLE);
        final ImageView btn2v = (ImageView)findViewById(R.id.navbtn2v);
        btn2v.setVisibility(View.VISIBLE);
        ImageView btn3v = (ImageView)findViewById(R.id.navbtn3v);
        ImageView btn4v = (ImageView)findViewById(R.id.navbtn4v);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Intent intent = new Intent(getApplicationContext(), BiosInfoActivity.class);
                startActivity(intent);
            }
        });

        if(b!=null)
        {
            String j =(String) b.get("PUTGENRE");
            genreTv.setText(j);
        }

        String j =(String) b.get("PUTGENRE");
        Integer t =(Integer) b.get("PUTTHUMB");

        genreThumb.setImageResource(t);

        String genreId = null;

        if (j.equals("Action")) {
            genreId = "28";
        }
        if (j.equals("Adventure")) {
            genreId = "12";
        }
        if (j.equals("Animation")) {
            genreId = "16";
        }
        if (j.equals("Comedy")) {
            genreId = "35";
        }
        if (j.equals("Drama")) {
            genreId = "18";
        }
        if (j.equals("Crime")) {
            genreId = "80";
        }
        if (j.equals("Fantasy")) {
            genreId = "14";
        }
        if (j.equals("History")) {
            genreId = "36";
        }
        if (j.equals("Horror")) {
            genreId = "27";
        }
        if (j.equals("Music")) {
            genreId = "10402";
        }
        if (j.equals("Romance")) {
            genreId = "10749";
        }


        // Get Genre ID

        // Bundle uitpakken
        // string


        imdbGridView = (GridView) findViewById(R.id.moviesGv);
        imdbAdapter = new IMDBAdapter(this, imdbItems);
        imdbGridView.setAdapter(imdbAdapter);
        imdbAdapter.notifyDataSetChanged();
        imdbGridView.setOnItemClickListener(this);

        imdbGridView.setAlpha(0);

        imdbGridView.animate()
                .alpha(1)
                .translationY(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(3000);


        // Clear list
        this.imdbItems.clear();



        // GET movies
        IMDBTask repoTask = new IMDBTask(this);
        String[] urls = new String[]
                {"https://api.themoviedb.org/3/discover/movie?api_key=863618e1d5c5f5cc4e34a37c49b8338e&language=nl-US&sort_by=popularity.desc&include_adult=false&include_video=true&page=1&with_genres=" + genreId};
        repoTask.execute(urls);


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
