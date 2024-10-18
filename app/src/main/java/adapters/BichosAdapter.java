package adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jsonreader.R;
import com.example.jsonreader.model.BestiaryParser;
import com.example.jsonreader.model.Entry;
import com.example.jsonreader.model.Section;
import com.google.android.material.color.MaterialColors;

import java.util.HashMap;
import java.util.List;

public class BichosAdapter extends RecyclerView.Adapter<BichosAdapter.ViewHolder>  {
    @NonNull
    @Override
    public BichosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BichosAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
