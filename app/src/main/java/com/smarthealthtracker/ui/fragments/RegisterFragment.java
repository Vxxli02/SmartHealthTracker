package com.smarthealthtracker.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import com.smarthealthtracker.R;
import com.smarthealthtracker.ui.activities.DoctorDashboardActivity;
import com.smarthealthtracker.ui.activities.HomeActivity;
import com.smarthealthtracker.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterFragment extends Fragment {

    EditText nameInput, emailInput, mobileInput, dobInput, passwordInput;
    RadioGroup roleGroup;
    RadioButton radioPatient, radioDoctor;
    Button registerButton;

    public RegisterFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        nameInput = view.findViewById(R.id.editFullName);
        emailInput = view.findViewById(R.id.editRegisterEmail);
        mobileInput = view.findViewById(R.id.editMobile);
        dobInput = view.findViewById(R.id.editDOB);
        passwordInput = view.findViewById(R.id.editRegisterPassword);
        registerButton = view.findViewById(R.id.btnRegister);

        roleGroup = view.findViewById(R.id.radioGroupRole);
        radioPatient = view.findViewById(R.id.radioPatient);
        radioDoctor = view.findViewById(R.id.radioDoctor);

        SessionManager sessionManager = new SessionManager(getActivity());

        // 🎂 Affiche le sélecteur de date
        dobInput.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    (view1, selectedYear, selectedMonth, selectedDay) -> {
                        String formatted = String.format(Locale.getDefault(), "%04d/%02d/%02d",
                                selectedYear, selectedMonth + 1, selectedDay);
                        dobInput.setText(formatted);
                    },
                    year, month, day
            );

            // 🔒 Bloque les dates futures
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });

        registerButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String mobile = mobileInput.getText().toString().trim();
            String dob = dobInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                nameInput.setError("Nom requis");
                return;
            }

            if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Email invalide");
                return;
            }

            if (TextUtils.isEmpty(mobile) || mobile.length() < 8) {
                mobileInput.setError("Numéro invalide");
                return;
            }

            if (TextUtils.isEmpty(dob)) {
                dobInput.setError("Date de naissance requise");
                return;
            }

            try {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                fmt.setLenient(false);
                Date birthDateParsed = fmt.parse(dob);

                Calendar birthDate = Calendar.getInstance();
                birthDate.setTime(birthDateParsed);

                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
                if (today.get(Calendar.DAY_OF_YEAR) < birthDate.get(Calendar.DAY_OF_YEAR)) {
                    age--;
                }

                if (age < 18) {
                    dobInput.setError("Vous devez avoir au moins 18 ans");
                    return;
                }

            } catch (Exception e) {
                dobInput.setError("Date invalide (format : yyyy/MM/dd)");
                return;
            }

            if (TextUtils.isEmpty(password) || password.length() < 6) {
                passwordInput.setError("Mot de passe min. 6 caractères");
                return;
            }

            sessionManager.setUserName(name);

            String role;
            if (radioDoctor.isChecked()) {
                role = "doctor";
                sessionManager.setUserRole(role);
                Toast.makeText(getActivity(), "Inscription médecin réussie ✅", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), DoctorDashboardActivity.class));
            } else {
                role = "patient";
                sessionManager.setUserRole(role);
                Toast.makeText(getActivity(), "Inscription patient réussie ✅", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), HomeActivity.class));
            }

            requireActivity().finish();
        });

        return view;
    }
}
