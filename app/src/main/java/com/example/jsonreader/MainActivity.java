package com.example.jsonreader;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonreader.model.BestiaryParser;
import com.example.jsonreader.model.Entry;
import com.example.jsonreader.model.Section;

import java.util.List;

import adapters.BichosAdapter;
import adapters.TiposBichosAdapter; // Asegúrate de tener este adaptador creado

public class MainActivity extends AppCompatActivity implements TiposBichosAdapter.TiposBichosAdapterListener {

    private RecyclerView rcyListBest;
    private RecyclerView rcyListMons;
    private TiposBichosAdapter tiposBichosAdapter; // Adapter para tipos de bichos
    private BichosAdapter bichosAdapter; // Adapter para bichos en la sección

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene la lista de secciones del bestiario
        List<Section> bestiary = BestiaryParser.getBestiary(this);
        Log.d("WITCHER", bestiary.toString());

        // Inicializa los RecyclerView
        rcyListBest = findViewById(R.id.rcyListbest);
        rcyListMons = findViewById(R.id.rcyListmons);

        // Configura el RecyclerView para tipos de bichos
        rcyListBest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tiposBichosAdapter = new TiposBichosAdapter(this, bestiary, this);
        rcyListBest.setAdapter(tiposBichosAdapter);

        // Configura el RecyclerView para bichos
        rcyListMons.setLayoutManager(new LinearLayoutManager(this));
        // Inicialmente, puedes no establecer un adaptador si no hay secciones seleccionadas
    }

    @Override
    public void onCardSelected(Section selected) {
        // Aquí se manejan los eventos de selección de tipo de bicho
        List<Entry> entries = selected.getEntries(); // Obtiene los bichos de la sección seleccionada
        bichosAdapter = new BichosAdapter(this, entries, null); // Crea el adaptador para los bichos
        rcyListMons.setAdapter(bichosAdapter); // Asigna el adaptador al RecyclerView de bichos
    }
}
