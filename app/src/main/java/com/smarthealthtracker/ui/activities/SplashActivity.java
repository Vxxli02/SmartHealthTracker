package com.smarthealthtracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.smarthealthtracker.R;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 1. Récupération des vues
        ImageView logo = findViewById(R.id.logoImage);
        TextView appName = findViewById(R.id.appName);

        // 2. Application des animations
        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        appName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.zoom_in));

        // 3. Redirection vers StartActivity après 2.5 secondes
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, StartActivity.class));
            finish();
        }, 2500);
    }

}
