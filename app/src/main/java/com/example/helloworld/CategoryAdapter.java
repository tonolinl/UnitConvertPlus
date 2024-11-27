package com.example.helloworld;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final List<String> categories;  // Liste des catégories (Température, Poids, etc.)
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

        // Si c'est Température, Poids ou Volume, affecter une image
        if (category.equals("Température")) {
            holder.imageButton.setImageResource(R.drawable.temp);  // Ajoute une image spécifique
        } else if (category.equals("Poids")) {
            holder.imageButton.setImageResource(R.drawable.poids);
        } else if (category.equals("Volume")) {
            holder.imageButton.setImageResource(R.drawable.vol);
        }

        // Listener pour la navigation
        holder.imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, ConversionListActivity.class);
            intent.putExtra("category", category);  // Passe la catégorie à la nouvelle activité
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
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



