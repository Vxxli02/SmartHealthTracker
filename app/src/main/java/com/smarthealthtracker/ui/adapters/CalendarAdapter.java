package com.smarthealthtracker.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.smarthealthtracker.R;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private final List<Calendar> daysList;
    private final Context context;
    private int selectedPosition = -1;

    public interface OnDateSelectedListener {
        void onDateSelected(Calendar selectedDate);
    }

    private final OnDateSelectedListener listener;

    public CalendarAdapter(Context context, List<Calendar> daysList, OnDateSelectedListener listener) {
        this.context = context;
        this.daysList = daysList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar_day, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Calendar calendar = daysList.get(position);

        SimpleDateFormat dayFormat = new SimpleDateFormat("EEE", Locale.getDefault());
        SimpleDateFormat dateFormat = new SimpleDateFormat("d", Locale.getDefault());

        holder.txtDay.setText(dayFormat.format(calendar.getTime()).toUpperCase(Locale.getDefault()));
        holder.txtDate.setText(dateFormat.format(calendar.getTime()));

        holder.itemView.setSelected(position == selectedPosition);

        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
            listener.onDateSelected(calendar); // uniquement ça
        });
    }

    @Override
    public int getItemCount() {
        return daysList.size();
    }

    public static class CalendarViewHolder extends RecyclerView.ViewHolder {
        TextView txtDay, txtDate;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtDate = itemView.findViewById(R.id.txtDate);
        }
    }
}
