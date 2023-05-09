package com.example.physiohut;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView textImerominia, textParoxi, textpatient;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        textImerominia = itemView.findViewById(R.id.textImerominia);
        textParoxi = itemView.findViewById(R.id.textParoxi);
    }
}
