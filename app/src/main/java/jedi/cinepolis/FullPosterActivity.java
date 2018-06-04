package jedi.cinepolis;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FullPosterActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        setContentView(R.layout.activity_full_poster);

        //Override de standaard transition met fadein / fadeout animatie
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        Intent intent = getIntent();
        IMDBItem item = (IMDBItem) intent.getSerializableExtra("IMDBITEM");

        ImageButton fullImg = (ImageButton) findViewById(R.id.fullPoster);
        Picasso.with(context).load(item.filmPosterUrl).into(fullImg);
        fullImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setTransitionName("selectedPoster");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, v.getTransitionName());

        Intent intent2 = getIntent();
        IMDBItem item = (IMDBItem) intent2.getSerializableExtra("IMDBITEM");

        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        intent.putExtra("IMDBITEM", item);
        startActivity(intent, optionsCompat.toBundle());
    }
}
