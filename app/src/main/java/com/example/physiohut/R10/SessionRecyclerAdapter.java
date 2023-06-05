package com.example.physiohut.R10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;
import com.example.physiohut.model.Session;

import java.util.ArrayList;

public class SessionRecyclerAdapter extends RecyclerView.Adapter<SessionViewHolder>{


    public SessionRecyclerAdapter(ArrayList<Session> data){
        this.localData = data;
    }

    private ArrayList<Session> localData;
    @NonNull
    @Override
    public SessionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_provision_row, parent, false);

        return new SessionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        Session s = localData.get(position);
        holder.getDateTextView().setText(s.getCompletedAt());
        //TODO: put logic here that trims the text to 12 chars max or some amount
        holder.getDescriptionTextView().setText(s.getProvision().getDescription());
        holder.getPriceTextView().setText(s.getProvision().getPrice() + "$");



    }

    @Override
    public int getItemCount() {
        return localData.size();
    }


}
