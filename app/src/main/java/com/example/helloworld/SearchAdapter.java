package com.example.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private List<String> itemList; // Liste originale
    private List<String> filteredList; // Liste filtrée

    public SearchAdapter(List<String> itemList) {
        this.itemList = itemList;
        this.filteredList = new ArrayList<>(itemList); // Copie des données originales
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size(); // Retourne la taille de la liste filtrée
    }

    // Méthode pour effectuer le filtrage
    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(itemList); // Affiche tous les éléments si la requête est vide
        } else {
            for (String item : itemList) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item); // Ajoute les éléments correspondants à la requête
                }
            }
        }
        notifyDataSetChanged(); // Notifie l'adapter des changements
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}
