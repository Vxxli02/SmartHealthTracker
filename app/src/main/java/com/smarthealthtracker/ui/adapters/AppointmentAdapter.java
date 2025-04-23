package com.smarthealthtracker.ui.adapters;

import android.content.Context;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.smarthealthtracker.R;
import com.smarthealthtracker.models.Appointment;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private final Context context;
    private final List<Appointment> appointmentList;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList) {
        this.context = context;
        this.appointmentList = appointmentList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment_card, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        holder.txtDoctorName.setText("Dr. " + appointment.getDoctorName());
        holder.txtSpecialty.setText(appointment.getSpecialty());
        holder.txtTime.setText(appointment.getTime());

        // ✅ Animation combinée slide + fade
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.slide_fade_in);
        holder.itemView.startAnimation(anim);
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView txtDoctorName, txtSpecialty, txtTime;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDoctorName = itemView.findViewById(R.id.txtDoctorName);
            txtSpecialty = itemView.findViewById(R.id.txtSpecialty);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
