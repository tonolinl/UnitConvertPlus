package com.example.helloworld;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryManager {
    private static final String HISTORY_FILE_NAME = "history.json";
    private Context context;

    public HistoryManager(Context context) {
        this.context = context;
    }

    // Méthode pour sauvegarder un calcul dans l'historique
    public void saveHistory(String expression, String result) {
        try {
            // Charger l'historique existant
            List<Map<String, String>> history = loadHistory();

            // Ajouter une nouvelle entrée
            JSONObject newEntry = new JSONObject();
            newEntry.put("expression", expression);
            newEntry.put("result", result);

            // Ajouter à l'historique
            JSONArray historyArray = new JSONArray();
            historyArray.put(newEntry);

            // Sauvegarder l'historique
            FileOutputStream outputStream = context.openFileOutput(HISTORY_FILE_NAME, Context.MODE_PRIVATE);
            outputStream.write(historyArray.toString().getBytes("UTF-8"));  // Utilisation de "UTF-8" pour compatibilité
            outputStream.close();
        } catch (IOException | JSONException e) {
            Log.e("HistoryManager", "Error saving history", e);
        }
    }

    // Méthode pour charger l'historique
    public List<Map<String, String>> loadHistory() {
        List<Map<String, String>> historyList = new ArrayList<>();
        try {
            FileInputStream inputStream = context.openFileInput(HISTORY_FILE_NAME);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer, "UTF-8");  // Utilisation de "UTF-8" pour compatibilité
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject entry = jsonArray.getJSONObject(i);
                String expression = entry.getString("expression");
                String result = entry.getString("result");

                // Ajouter à l'historique sous forme de Map
                Map<String, String> historyEntry = Map.of("expression", expression, "result", result);
                historyList.add(historyEntry);
            }
        } catch (IOException | JSONException e) {
            Log.e("HistoryManager", "Error loading history", e);
        }
        return historyList;
    }

    // Méthode pour vider l'historique (effacer le fichier)
    public void clearHistory() {
        try {
            context.deleteFile(HISTORY_FILE_NAME);
        } catch (Exception e) {
            Log.e("HistoryManager", "Error clearing history", e);
        }
    }
}
