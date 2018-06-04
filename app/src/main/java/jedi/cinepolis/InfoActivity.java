package jedi.cinepolis;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;

public class InfoActivity extends AppCompatActivity implements IMDBTask.onIMDBItemAvailable, View.OnClickListener {

    private Context context;
    private ArrayList<IMDBItem> imdbItems = new ArrayList<IMDBItem>();
    private int aantal = 1;
    private double prijs;
    private static DecimalFormat df2 = new DecimalFormat(".##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.BLACK);
        setContentView(R.layout.activity_info);

        overridePendingTransition(R.anim.slide_in_up, R.anim.stay);

        Intent intent = getIntent();
        IMDBItem item = (IMDBItem) intent.getSerializableExtra("IMDBITEM");

        ImageButton thumb =(ImageButton)findViewById(R.id.infoPoster);
        Picasso.with(context).load(item.filmPosterUrl).into(thumb);


        thumb.setOnClickListener(this);
        Button koopBtn = (Button)findViewById(R.id.koopBtn);
        final ImageButton btn1 = (ImageButton)findViewById(R.id.navbtn1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.navbtn2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.navbtn3);
        ImageButton btn4 = (ImageButton)findViewById(R.id.navbtn4);
        final ImageView btn1v = (ImageView)findViewById(R.id.navbtn1v);
        btn1v.setVisibility(View.INVISIBLE);
        final ImageView btn2v = (ImageView)findViewById(R.id.navbtn2v);
        btn2v.setVisibility(View.INVISIBLE);
        ImageView btn3v = (ImageView)findViewById(R.id.navbtn3v);
        ImageView btn4v = (ImageView)findViewById(R.id.navbtn4v);
        final TextView totaalPrijsTv = (TextView)findViewById(R.id.totaalPrijs);
        final TextView aantalTicketsTv = (TextView)findViewById(R.id.aantalTickets);
        Button plus = (Button)findViewById(R.id.plusButton);
        Button min = (Button)findViewById(R.id.minButton);


        koopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1v.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(getApplicationContext(), BiosInfoActivity.class);
                startActivity(intent);
            }
        });

        final Button tijdstip2 = (Button)findViewById(R.id.tijdstip2btn);
        tijdstip2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout b = (LinearLayout)findViewById(R.id.beschrijvingLayout);
                b.setVisibility(View.GONE);
                LinearLayout k = (LinearLayout)findViewById(R.id.kassaLayout);
                k.setVisibility(View.VISIBLE);

                tijdstip2.setBackgroundResource(R.color.cardview_light_background);
                tijdstip2.setTextColor(BLACK);
                Button tijdstip1 = (Button)findViewById(R.id.tijdstip1btn);
                tijdstip1.setBackgroundResource(R.color.cardview_dark_background);
                tijdstip1.setTextColor(BLACK);
            }
        });

        final Button tijdstip1 = (Button)findViewById(R.id.tijdstip1btn);
        tijdstip1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout b = (LinearLayout)findViewById(R.id.beschrijvingLayout);
                b.setVisibility(View.GONE);
                LinearLayout k = (LinearLayout)findViewById(R.id.kassaLayout);
                k.setVisibility(View.VISIBLE);

                tijdstip1.setBackgroundResource(R.color.cardview_light_background);
                tijdstip1.setTextColor(BLACK);
                Button tijdstip2 = (Button)findViewById(R.id.tijdstip2btn);
                tijdstip2.setBackgroundResource(R.color.cardview_dark_background);
                tijdstip2.setTextColor(WHITE);
            }
        });




        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aantal++;
                totaalPrijsTv.setText("€ " + df2.format(prijs * aantal));
                aantalTicketsTv.setText("" + aantal);

            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aantal--;
                if(aantal <= 1){
                    aantal =1;
                }
                totaalPrijsTv.setText("€ " + df2.format(prijs * aantal));
                aantalTicketsTv.setText("" + aantal);
            }
        });

        ImageView bg =(ImageView)findViewById(R.id.infoBg);
        Picasso.with(context).load(item.filmBg).into(bg);

        TextView datum = (TextView)findViewById(R.id.infoDatum);
        datum.setText(item.filmJaar);

        TextView titel = (TextView)findViewById(R.id.infoTitel);
        titel.setText(item.filmTitel);

        TextView beschrijving = (TextView)findViewById(R.id.beschrijving);
        beschrijving.setText(item.filmBeschrijving);

        TextView vote = (TextView)findViewById(R.id.infoVote);
        vote.setText(item.filmRating + "/10");

    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_normaal:
                if (checked)
                    prijs = 10.75;
                LinearLayout aantalKaartjes = (LinearLayout) findViewById(R.id.kassaLayout2);
                aantalKaartjes.setVisibility(View.VISIBLE);
                TextView totaalPrijsTv = (TextView)findViewById(R.id.totaalPrijs);
                totaalPrijsTv.setText("€ " + df2.format(prijs * aantal));
                break;
            case R.id.radio_korting:
                if (checked)
                    prijs = 9.75;
                aantalKaartjes = (LinearLayout) findViewById(R.id.kassaLayout2);
                aantalKaartjes.setVisibility(View.VISIBLE);
                TextView totaalPrijsTv2 = (TextView)findViewById(R.id.totaalPrijs);
                totaalPrijsTv2.setText("€ " + df2.format(prijs * aantal));
                break;
            case R.id.radio_student:
                if (checked)
                    prijs = 8.05;
                aantalKaartjes = (LinearLayout) findViewById(R.id.kassaLayout2);
                aantalKaartjes.setVisibility(View.VISIBLE);
                TextView totaalPrijsTv3 = (TextView)findViewById(R.id.totaalPrijs);
                totaalPrijsTv3.setText("€ " + df2.format(prijs * aantal));
                break;
        }
    }

    public void onClick(View v) {
        v.setTransitionName("selectedPoster");
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, v.getTransitionName());

        Intent intent2 = getIntent();
        IMDBItem item = (IMDBItem) intent2.getSerializableExtra("IMDBITEM");

        Intent intent = new Intent(getApplicationContext(), FullPosterActivity.class);
        intent.putExtra("IMDBITEM", item);
        startActivity(intent, optionsCompat.toBundle());
    }


    @Override
    public void onIMDBItemAvailable(IMDBItem item) {

    }
}
