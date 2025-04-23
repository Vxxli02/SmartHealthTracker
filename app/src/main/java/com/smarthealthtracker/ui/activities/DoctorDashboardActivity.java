package com.smarthealthtracker.ui.activities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.smarthealthtracker.R;
import com.smarthealthtracker.utils.SessionManager;

public class DoctorDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        TextView title = findViewById(R.id.doctorWelcome);

        SessionManager sessionManager = new SessionManager(this);
        String doctorName = sessionManager.getUserName();

        title.setText("👨‍⚕️ Welcome Dr. " + doctorName);
    }
}
