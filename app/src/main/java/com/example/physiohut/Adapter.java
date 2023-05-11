package com.example.physiohut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Appointments> arrayList;

    public void setFilteredList(ArrayList<Appointments> filteredList){
        this.arrayList = filteredList;
        notifyDataSetChanged();
    }

    public Adapter(Context context, ArrayList<Appointments> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.patient_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        Appointments appointmentsClass = arrayList.get(position);

        holder.patientNameR7.setText(appointmentsClass.patientName);
        holder.dateOfAppointmentDataR7.setText(appointmentsClass.appointmentDate);
        holder.locationOfAppointmentDataR7.setText(appointmentsClass.appointmentArea);
        holder.timeOfAppointmentDataR7.setText(appointmentsClass.appointmentTime);

        holder.expandButtonR7.setOnClickListener(view -> {
            if(arrayList.get(position).isVisible){
                holder.expandedLayoutR7.setVisibility(View.GONE);
                arrayList.get(position).isVisible=false;
                holder.expandButtonR7.setRotation(0);

            }
            else {
                holder.expandedLayoutR7.setVisibility(View.VISIBLE);
                arrayList.get(position).isVisible=true;
                holder.expandButtonR7.setRotation(180);
            }
        });

        holder.patientNameR7.setOnClickListener(view -> {
            if(arrayList.get(position).isVisible){
                holder.expandedLayoutR7.setVisibility(View.GONE);
                arrayList.get(position).isVisible=false;
                holder.expandButtonR7.setRotation(0);
            }
            else {
                holder.expandedLayoutR7.setVisibility(View.VISIBLE);
                arrayList.get(position).isVisible=true;
                holder.expandButtonR7.setRotation(180);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton expandButtonR7;
        TextView patientNameR7;
        TextView dateOfAppointmentDataR7;
        TextView locationOfAppointmentDataR7;
        TextView timeOfAppointmentDataR7;

        ConstraintLayout appointmentsRecyclerViewR7;
        ConstraintLayout expandedLayoutR7;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameR7 = itemView.findViewById(R.id.patientNameR7);
            dateOfAppointmentDataR7 = itemView.findViewById(R.id.dateOfAppointmentDataR7);
            locationOfAppointmentDataR7 = itemView.findViewById(R.id.locationOfAppointmentDataR7);
            timeOfAppointmentDataR7 = itemView.findViewById(R.id.timeOfAppointmentDataR7);
            appointmentsRecyclerViewR7 = itemView.findViewById(R.id.recyclerViewR7);
            expandedLayoutR7 = itemView.findViewById(R.id.expandedLayoutR7);
            expandButtonR7 = itemView.findViewById(R.id.expandButtonR7);
        }

    }
}


