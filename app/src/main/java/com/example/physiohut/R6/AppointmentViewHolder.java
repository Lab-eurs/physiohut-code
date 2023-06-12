package com.example.physiohut.R6;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;

public class AppointmentViewHolder extends RecyclerView.ViewHolder {

    private final TextView appointmentText;
    private final Button button;

    public AppointmentViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View
        appointmentText = (TextView) view.findViewById(R.id.textView);
        button = (Button) view.findViewById(R.id.item_btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_doctorFragment_to_provisionPatients);
            }
        });
    }


    public TextView getStateTextView(){
        return appointmentText;
    }
    public Button getButtonView(){
        return button;
    }

}
