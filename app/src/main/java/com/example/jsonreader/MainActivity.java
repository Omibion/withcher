package com.example.jsonreader;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonreader.model.BestiaryParser;
import com.example.jsonreader.model.Entry;
import com.example.jsonreader.model.Section;

import java.util.List;

import adapters.BichosAdapter;
import adapters.TiposBichosAdapter;

public class MainActivity extends AppCompatActivity implements TiposBichosAdapter.TiposBichosAdapterListener {

    private RecyclerView rcyListBest;
    private RecyclerView rcyListMons;
    private TiposBichosAdapter tiposBichosAdapter;
    private BichosAdapter bichosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton btnClose = findViewById(R.id.btnsalir);
        btnClose.setOnClickListener(v -> finish());


        List<Section> bestiary = BestiaryParser.getBestiary(this);
        Log.d("WITCHER", bestiary.toString());

        rcyListBest = findViewById(R.id.rcyListbest);
        rcyListMons = findViewById(R.id.rcyListmons);

        rcyListBest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        tiposBichosAdapter = new TiposBichosAdapter(this, bestiary, this);
        rcyListBest.setAdapter(tiposBichosAdapter);

        rcyListMons.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onCardSelected(Section selected) {
        List<Entry> entries = selected.getEntries();

        bichosAdapter = new BichosAdapter(this, selected, null);
        rcyListMons.setAdapter(bichosAdapter);
    }
}
