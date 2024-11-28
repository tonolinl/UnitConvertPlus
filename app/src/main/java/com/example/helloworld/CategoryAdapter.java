package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<String> categories;
    private final Context context;

    public CategoryAdapter(Context context, List<String> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categories.get(position);

        holder.textView.setText(category);

        // Placeholder pour icônes (si nécessaires)
        switch (category) {
            case "Température":
                holder.imageButton.setImageResource(R.drawable.temp);
                break;
            case "Poids":
                holder.imageButton.setImageResource(R.drawable.poids);
                break;
            case "Volume":
                holder.imageButton.setImageResource(R.drawable.vol);
                break;
            case "Distance":
                holder.imageButton.setImageResource(R.drawable.regle);
                break;
            case "Data Byte":
                holder.imageButton.setImageResource(R.drawable.bites);
                break;
            case "Vitesse":
                holder.imageButton.setImageResource(R.drawable.vitesse);
                break;
            case "Fréquence":
                holder.imageButton.setImageResource(R.drawable.frequence);
                break;
            case "Pression":
                holder.imageButton.setImageResource(R.drawable.pression);
                break;
        }

        // Navigation vers l'activité de conversion
        holder.imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ConversionActivity.class);
            intent.putExtra("category", category);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void updateCategories(List<String> newCategories) {
        this.categories = newCategories;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        TextView textView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageButton);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
