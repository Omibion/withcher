package com.example.jsonreader.model;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import com.example.jsonreader.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class BestiaryParser {
    public static List<Section> getBestiary(Context c){
        List<Section> bestiary = new ArrayList<>();


        //0.- LLegir arxiu JSON i posarlo aen una cadena
        String json = readJson(c);
        if(json == null) return null;

        //1.- Parsejar JSON i recorrer les sections
        try {
            JSONObject arrel = new JSONObject(json);
            JSONArray sections = arrel.getJSONArray("sections");

            for (int i = 0; i < arrel.length(); i++) {
                JSONObject section = sections.getJSONObject(i);
                String title = (section.getString("title"));
                String subtitle = (section.getString("subtitle"));
                String image = (section.getString("image"));
                Section sec = new Section(title, subtitle, image);

                //2.- Per cada section, buscas las entries
                JSONArray entries = section.getJSONArray("entries");
                for (int j = 0; j < entries.length(); j++) {
                    JSONObject entry = entries.getJSONObject(i);
                    title = entry.getString("title");
                    image = entry.getString("image");
                    Entry ent = new Entry(title, image);
                    sec.getEntries().add(ent);
                }
                bestiary.add(sec);
            }
        } catch (JSONException e) {
            Log.e("WATCHER", "Error parsejant JSON", e);
            return null;
        }
        return bestiary;
    }

    private static String readJson(Context c) {
        InputStream is = c.getResources().openRawResource(R.raw.bestiary);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        String json ="";

        try{
            while((line = br.readLine())!=null){
                json += line+"\n";
            }
           //Log.d("WITCHER\n", json);
        }catch (IOException ex){
            return null;
        }
        return json;
    }

}
