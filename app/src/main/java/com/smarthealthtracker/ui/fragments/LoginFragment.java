package com.smarthealthtracker.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.smarthealthtracker.R;
import com.smarthealthtracker.ui.activities.DoctorDashboardActivity;
import com.smarthealthtracker.ui.activities.HomeActivity;
import com.smarthealthtracker.utils.SessionManager;

public class LoginFragment extends Fragment {

    EditText emailInput, passwordInput;
    Button loginButton;

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailInput = view.findViewById(R.id.editLoginEmail);
        passwordInput = view.findViewById(R.id.editLoginPassword);
        loginButton = view.findViewById(R.id.btnLogin);

        SessionManager sessionManager = new SessionManager(getActivity());

        loginButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(getActivity(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            if ((email.equals("test") || email.equals("doctor@mail.com")) && password.equals("1")) {

                // Détecte et stocke le rôle
                if (email.equals("doctor@mail.com")) {
                    sessionManager.setUserRole("doctor");
                    sessionManager.setUserName("Dr. Ahmed");
                    Toast.makeText(getActivity(), "Bienvenue Docteur 👨‍⚕️", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), DoctorDashboardActivity.class));
                } else {
                    sessionManager.setUserRole("patient");
                    sessionManager.setUserName("Patient Ali");
                    Toast.makeText(getActivity(), "Bienvenue Patient 👤", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), HomeActivity.class));
                }

                // ⚠️ Laisse cette ligne commentée pour les tests
                // requireActivity().finish();

            } else {
                Toast.makeText(getActivity(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
