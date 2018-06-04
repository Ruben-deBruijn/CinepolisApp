package jedi.cinepolis;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class GenreActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // references to our images
//    private String[] mGenres = {
//            "Actie", "Avontuur",
//            "Comedy", "Drama",
//            "Fantasie", "Horror",
//            "Misdaad", "Romantiek"
//    };

    private ArrayList<String> mGenres = new ArrayList<>(Arrays.asList("Action", "Adventure", "Animation", "Comedy", "Crime", "Drama","Fantasy", "Horror", "Romance"));
    private ArrayList<Integer> mThumbIds = new ArrayList<>(Arrays.asList(R.drawable.beauty_and_the_beast, R.drawable.get_out,
            R.drawable.kong_skull_island, R.drawable.lego_batman_movie,
            R.drawable.logan, R.drawable.shack,
            R.drawable.power, R.drawable.horror, R.drawable.construction));
    // references to our images
//    private ArrayList<Integer> mThumbIds = new ArrayList<>(Arrays.asList(R.drawable.beauty_and_the_beast, R.drawable.get_out,
//            R.drawable.kong_skull_island, R.drawable.lego_batman_movie,
//            R.drawable.logan, R.drawable.shack,
//            R.drawable.power, R.drawable.fist_fight));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        setContentView(R.layout.activity_genre);

        overridePendingTransition(R.anim.stay, R.anim.stay);


        final ListView listview = (ListView) findViewById(R.id.genreListView);
        GenreAdapter adapter = new GenreAdapter(getApplicationContext(), mGenres, mThumbIds);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(this);

        listview.animate()
                .alpha(1)
                .setDuration(500);



        GenreItem genreItem = new GenreItem();

        final ImageButton btn1 = (ImageButton)findViewById(R.id.navbtn1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.navbtn2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.navbtn3);
        ImageButton btn4 = (ImageButton)findViewById(R.id.navbtn4);
        final ImageView btn1v = (ImageView)findViewById(R.id.navbtn1v);
        final ImageView btn2v = (ImageView)findViewById(R.id.navbtn2v);
        final ImageView btn3v = (ImageView)findViewById(R.id.navbtn3v);
        final ImageView btn4v = (ImageView)findViewById(R.id.navbtn4v);
        btn2v.setVisibility(View.VISIBLE);
        btn1v.setVisibility(View.INVISIBLE);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2v.setVisibility(View.INVISIBLE);
                btn1v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2v.setVisibility(View.INVISIBLE);
                btn3v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2v.setVisibility(View.INVISIBLE);
                btn4v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), BiosInfoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_up, R.anim.slide_out_up);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        view.setTransitionName("transGenre");
        ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, view.getTransitionName());

        adapterView.getSelectedView();

        Intent genreIntent = new Intent(getApplicationContext(), FilteredGenreActivity.class);

        // GenreItem item = (GenreItem) this.genreItems.get(i);
        Integer genreThumb = mThumbIds.get(i);
        String genreNaam = mGenres.get(i);
        genreIntent.putExtra("PUTTHUMB", genreThumb);
        genreIntent.putExtra("PUTGENRE", genreNaam);
        startActivity(genreIntent, optionsCompat.toBundle());
    }
}
