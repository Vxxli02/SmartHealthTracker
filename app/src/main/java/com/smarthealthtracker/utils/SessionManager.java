package com.smarthealthtracker.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "UserSession";
    private static final String KEY_ROLE = "user_role";
    private static final String KEY_NAME = "user_name";
    private final SharedPreferences prefs;
    private final SharedPreferences.Editor editor;


    public void setUserName(String name) {
        editor.putString(KEY_NAME, name);
        editor.apply();
    }

    public String getUserName() {
        return prefs.getString(KEY_NAME, "User");
    }

    public SessionManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    // Enregistre le rôle (ex: "doctor" ou "patient")
    public void setUserRole(String role) {
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    // Récupère le rôle enregistré
    public String getUserRole() {
        return prefs.getString(KEY_ROLE, "patient"); // Par défaut : patient
    }

    // Efface toutes les données (si nécessaire)
    public void clearSession() {
        editor.clear().apply();
    }
}
