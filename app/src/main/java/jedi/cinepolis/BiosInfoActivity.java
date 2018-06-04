package jedi.cinepolis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;

public class BiosInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bios_info);

        overridePendingTransition(R.anim.stay, R.anim.stay);

        getSupportActionBar().setTitle("Contact");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        ImageView iv = (ImageView) findViewById(R.id.contactBg);
                iv.animate()
                        .alpha(1)
                        .setDuration(500);

        ScrollView sv = (ScrollView) findViewById(R.id.contactSv);
                sv.animate()
                    .alpha(1)
                    .setDuration(500);

        final ImageButton btn1 = (ImageButton)findViewById(R.id.navbtn1);
        ImageButton btn2 = (ImageButton)findViewById(R.id.navbtn2);
        ImageButton btn3 = (ImageButton)findViewById(R.id.navbtn3);
        ImageButton btn4 = (ImageButton)findViewById(R.id.navbtn4);
        final ImageView btn1v = (ImageView)findViewById(R.id.navbtn1v);
        final ImageView btn2v = (ImageView)findViewById(R.id.navbtn2v);
        final ImageView btn3v = (ImageView)findViewById(R.id.navbtn3v);
        final ImageView btn4v = (ImageView)findViewById(R.id.navbtn4v);
        btn1v.setVisibility(View.INVISIBLE);
        btn4v.setVisibility(View.VISIBLE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn4v.setVisibility(View.INVISIBLE);
                btn1v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn4v.setVisibility(View.INVISIBLE);
                btn2v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), GenreActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn4v.setVisibility(View.INVISIBLE);
                btn3v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), AccountActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn4v.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), BiosInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}
