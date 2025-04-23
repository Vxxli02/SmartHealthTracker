package com.smarthealthtracker.ui.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smarthealthtracker.R;
import com.smarthealthtracker.utils.SessionManager;
import com.smarthealthtracker.ui.adapters.CalendarAdapter;
import com.smarthealthtracker.models.Appointment;
import com.smarthealthtracker.ui.adapters.AppointmentAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    RecyclerView appointmentRecyclerView;
    AppointmentAdapter appointmentAdapter;
    List<Appointment> appointmentList = new ArrayList<>();

    RecyclerView calendarRecyclerView;
    CalendarAdapter calendarAdapter;
    List<Calendar> calendarList = new ArrayList<>();

    TextView welcomeText, welcomeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toast.makeText(this, "🏠 Dashboard patient ouvert", Toast.LENGTH_SHORT).show();

        // Initialisation vues
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        appointmentRecyclerView = findViewById(R.id.appointmentRecyclerView);
        welcomeText = findViewById(R.id.welcomeText);
        welcomeUser = findViewById(R.id.welcomeUser);

        // Génération des 7 jours
        Calendar today = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            Calendar day = (Calendar) today.clone();
            day.add(Calendar.DAY_OF_MONTH, i);
            calendarList.add(day);
        }

        // Initialisation du calendrier avec listener
        calendarAdapter = new CalendarAdapter(this, calendarList, selectedDate -> {
            updateAppointmentsForDate(selectedDate);
        });

        calendarRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        calendarRecyclerView.setAdapter(calendarAdapter);

        // Initialisation des rendez-vous du jour
        updateAppointmentsForDate(today);

        // Adapter RDV
        appointmentAdapter = new AppointmentAdapter(this, appointmentList);
        appointmentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentRecyclerView.setAdapter(appointmentAdapter);

        // Chargement de la session utilisateur
        SessionManager sessionManager = new SessionManager(this);
        String name = sessionManager.getUserName();
        String role = sessionManager.getUserRole();

        if (welcomeUser != null) welcomeUser.setText(name);
        if (welcomeText != null) {
            if ("patient".equals(role)) {
                welcomeText.setText("👋 Bienvenue " + name + " !");
            } else {
                welcomeText.setText("👋 Bienvenue !");
            }
        }
    }

    private void updateAppointmentsForDate(Calendar selectedDate) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String selectedKey = fmt.format(selectedDate.getTime());

        Log.d("DATE_SELECTED", "Clé = " + selectedKey);

        appointmentList.clear();

        switch (selectedKey) {
            case "2025/04/25":
                appointmentList.add(new Appointment("Sarah Lamine", "Cardiologie", "10:30 - 11:00"));
                break;
            case "2025/04/26":
                appointmentList.add(new Appointment("Amine Berrada", "Dermatologie", "14:00 - 14:30"));
                appointmentList.add(new Appointment("Hajar Benzakour", "Pédiatrie", "15:00 - 15:30"));
                break;
            default:
                appointmentList.add(new Appointment("Pas de rendez-vous", "", ""));
                break;
        }

        appointmentAdapter.notifyDataSetChanged();
    }
}
