package com.example.physiohut.R10;

import static com.example.physiohut.R.*;

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
                .inflate(layout.recycler_provision_row, parent, false);

        return new SessionViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SessionViewHolder holder, int position) {
        Session s = localData.get(position);
        holder.getDateTextView().setText(s.getCompletedAt());
        if(holder.getDateTextView().getText().equals("null")){
            s.setCompletedAt("not completed yet");
            holder.getDateTextView().setText("not completed yet");
        }


        //TODO: put logic here that trims the text to 12 chars max or some amount
        holder.getDescriptionTextView().setText(s.getProvision().getDescription());
        holder.getPriceTextView().setText(s.getProvision().getPrice() + "$");

        if(s.getSessionState() == Session.SESSION_STATE.COMPLETED){
            holder.getStateTextView().setText("completed");
            holder.getStateTextView().setTextColor(holder.getStateTextView().getResources().getColor(color.blue_completed));
        }else if(s.getSessionState() == Session.SESSION_STATE.PENDING){
            holder.getStateTextView().setText("pending");
            holder.getStateTextView().setTextColor(holder.getStateTextView().getResources().getColor(color.yellow_pending));
        }else if(s.getSessionState() == Session.SESSION_STATE.REJECTED){
            holder.getStateTextView().setText("rejected");
            holder.getStateTextView().setTextColor(holder.getStateTextView().getResources().getColor(color.red_rejected));
        }else{
            holder.getStateTextView().setText("accepted");
            holder.getStateTextView().setBackgroundColor(holder.getStateTextView().getResources().getColor(color.green_accepted));
        }



    }

    @Override
    public int getItemCount() {
        return localData.size();
    }

}
