package com.example.physiohut.R7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<PendingAppointmentsR7> arrayList;

    public void setFilteredList(ArrayList<PendingAppointmentsR7> filteredList){
        this.arrayList = filteredList;
    }

    public Adapter(Context context, ArrayList<PendingAppointmentsR7> arrayList){
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
        PendingAppointmentsR7 appointmentsClass = arrayList.get(position);

        holder.patientNameR7.setText(appointmentsClass.getPatientName());
        holder.dateOfAppointmentDataR7.setText(appointmentsClass.getAppointmentDate());
        holder.locationOfAppointmentDataR7.setText(appointmentsClass.getAppointmentArea());
        holder.timeOfAppointmentDataR7.setText(appointmentsClass.getAppointmentTime());

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


