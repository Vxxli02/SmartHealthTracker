package com.smarthealthtracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.smarthealthtracker.R;

public class StartActivity extends AppCompatActivity {

    Button btnLogin, btnRegister;
    ImageView logoImage;
    TextView appName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        logoImage = findViewById(R.id.logoStart);
        appName = findViewById(R.id.appNameStart);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        // Animations
        logoImage.startAnimation(AnimationUtils.loadAnimation(this, R.anim.slide_top));
        appName.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        btnLogin.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_bounce));
        btnRegister.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_bounce));

        // Navigation
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, AuthActivity.class);
            intent.putExtra("mode", "login");
            startActivity(intent);
        });

        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, AuthActivity.class);
            intent.putExtra("mode", "register");
            startActivity(intent);
        });
    }
}
