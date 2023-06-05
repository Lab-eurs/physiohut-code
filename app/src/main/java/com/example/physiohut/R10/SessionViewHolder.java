package com.example.physiohut.R10;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;

public class SessionViewHolder extends RecyclerView.ViewHolder {

    private final TextView priceText;
    private final TextView descriptionText;
    private final TextView dateText;

    public SessionViewHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

        priceText = (TextView) view.findViewById(R.id.priceTextV);
        descriptionText = (TextView) view.findViewById(R.id.descriptionTextV);
        dateText = (TextView) view.findViewById(R.id.dateTextV);

    }



    public TextView getPriceTextView(){
        return priceText;
    }

    public TextView getDescriptionTextView(){
        return descriptionText;
    }

    public TextView getDateTextView(){
        return dateText;
    }

}
