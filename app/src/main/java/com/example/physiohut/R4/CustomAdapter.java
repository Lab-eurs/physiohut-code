package com.example.physiohut.R4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physiohut.R;
import com.example.physiohut.R4.CustomViewHolder;
import com.example.physiohut.R4.R4Fragment;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<R4Fragment> list;

    public CustomAdapter(Context context, List<R4Fragment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_r4, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.textImerominia.setText(list.get(position).getImerominia());
        holder.textParoxi.setText(String.valueOf(list.get(position).getParoxi()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
