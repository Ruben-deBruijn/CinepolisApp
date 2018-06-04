package jedi.cinepolis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    private int mLongAnimationDuration;
    private float posterDim;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        overridePendingTransition(R.anim.stay, R.anim.stay);

        mLongAnimationDuration = getResources().getInteger(
                android.R.integer.config_longAnimTime);

        //Dimt alle posters
        posterDim = 0.5f ;

        ImageView aPoster1 = (ImageView) findViewById(R.id.sPoster1);
        ImageView aPoster2 = (ImageView) findViewById(R.id.sPoster2);
        ImageView aPoster3 = (ImageView) findViewById(R.id.sPoster3);
        ImageView aPoster4 = (ImageView) findViewById(R.id.sPoster4);
        ImageView aPoster5 = (ImageView) findViewById(R.id.sPoster5);
        ImageView aPoster6 = (ImageView) findViewById(R.id.sPoster6);
        ImageView aLogo = (ImageView) findViewById(R.id.sLogo);
        LinearLayout aPosters =(LinearLayout) findViewById(R.id.sPosters);
        FrameLayout aContainer =(FrameLayout) findViewById(R.id.sContainer);
        aPoster1.animate()
                .alpha(posterDim)
                .setStartDelay(500)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPoster2.animate()
                .alpha(posterDim)
                .setStartDelay(1000)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPoster3.animate()
                .alpha(posterDim)
                .setStartDelay(1500)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPoster4.animate()
                .alpha(posterDim)
                .setStartDelay(2000)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPoster5.animate()
                .alpha(posterDim)
                .setStartDelay(2500)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPoster6.animate()
                .alpha(posterDim)
                .setStartDelay(3000)
                .scaleX(1)
                .scaleY(1)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aLogo.animate()
                .alpha(1)
                .setStartDelay(0)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setDuration(750);
        aPosters.animate()
                .alpha(1)
                .setDuration(mLongAnimationDuration);
        aContainer.animate()
                .setStartDelay(3500)
                .scaleX(20)
                .scaleY(20)
                .alpha(0)
                .setDuration(mLongAnimationDuration);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}