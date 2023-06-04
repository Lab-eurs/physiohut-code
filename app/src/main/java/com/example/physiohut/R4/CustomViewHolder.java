package com.example.physiohut.R4;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView textImerominia, textParoxi;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        textImerominia = itemView.findViewById(R.id.textImerominia);
        textParoxi = itemView.findViewById(R.id.textParoxi);
    }
}
