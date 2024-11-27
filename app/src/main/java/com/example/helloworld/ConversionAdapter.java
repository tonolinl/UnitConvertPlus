package com.example.helloworld;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.ConversionViewHolder> {

    private final Context context;
    private final List<String> conversions;

    public ConversionAdapter(Context context, List<String> conversions) {
        this.context = context;
        this.conversions = conversions;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_conversion, parent, false);
        return new ConversionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        String unit = conversions.get(position);
        holder.textView.setText(unit);
    }

    @Override
    public int getItemCount() {
        return conversions.size();
    }

    public static class ConversionViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ConversionViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}


