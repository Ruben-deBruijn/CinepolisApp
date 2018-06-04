package jedi.cinepolis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class TicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        ImageView enterCinema =(ImageView)findViewById(R.id.enterCinema);
        enterCinema.setVisibility(View.VISIBLE);
        enterCinema.animate()
                .setStartDelay(1750)
                .scaleX(1.1f)
                .scaleY(1.1f)
                .alpha(0)
                .setDuration(3000);

    }
}
