package com.smarthealthtracker.models;

public class Appointment {
    private String doctorName, specialty, time;

    public Appointment(String doctorName, String specialty, String time) {
        this.doctorName = doctorName;
        this.specialty = specialty;
        this.time = time;
    }

    public String getDoctorName() { return doctorName; }
    public String getSpecialty() { return specialty; }
    public String getTime() { return time; }
}
