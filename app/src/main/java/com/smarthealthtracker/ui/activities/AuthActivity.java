package com.smarthealthtracker.ui.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.smarthealthtracker.R;
import com.smarthealthtracker.ui.adapters.AuthPagerAdapter;

public class AuthActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    AuthPagerAdapter authPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        // 🔗 Récupération des vues
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        // ✅ Initialise l'adapter pour LoginFragment & RegisterFragment
        authPagerAdapter = new AuthPagerAdapter(this);
        viewPager.setAdapter(authPagerAdapter);

        // 🔁 Connecte le TabLayout avec ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(position == 0 ? "Login" : "Register")
        ).attach();

        // ✅ Gère la redirection vers l'onglet correct
        String mode = getIntent().getStringExtra("mode");
        if ("register".equals(mode)) {
            viewPager.setCurrentItem(1); // Onglet Register
        } else {
            viewPager.setCurrentItem(0); // Onglet Login (par défaut)
        }
    }
}
