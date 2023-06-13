package com.example.physiohut.R7;

import android.annotation.SuppressLint;
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
    private ArrayList<PendingAppointmentsR7> arrayList;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patient_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        PendingAppointmentsR7 appointmentsClass = arrayList.get(position);
        System.out.println(appointmentsClass.getPatientName());
        holder.patientNameR7.setText(appointmentsClass.getPatientName());
        holder.dateOfAppointmentDataR7.setText(appointmentsClass.getAppointmentDate());

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
        holder.confirmAppointmentButtonR7.setOnClickListener(view -> removeAt(position));
        holder.declineAppointmentButtonR7.setOnClickListener(view -> removeAt(position));
    }
    private void removeAt(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, arrayList.size());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton expandButtonR7;
        TextView patientNameR7;
        TextView dateOfAppointmentDataR7;
        ConstraintLayout appointmentsRecyclerViewR7;
        ConstraintLayout expandedLayoutR7;
        ImageButton confirmAppointmentButtonR7;
        ImageButton declineAppointmentButtonR7;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            patientNameR7 = itemView.findViewById(R.id.patientNameR7);
            dateOfAppointmentDataR7 = itemView.findViewById(R.id.dateOfAppointmentDataR7);
            appointmentsRecyclerViewR7 = itemView.findViewById(R.id.recyclerViewR7);
            expandedLayoutR7 = itemView.findViewById(R.id.expandedLayoutR7);
            expandButtonR7 = itemView.findViewById(R.id.expandButtonR7);
            confirmAppointmentButtonR7 = itemView.findViewById(R.id.confirmAppointmentButtonR7);
            declineAppointmentButtonR7 =  itemView.findViewById(R.id.declineAppointmentButtonR7);
        }

    }
}


