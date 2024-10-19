package adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.example.jsonreader.R;
import com.example.jsonreader.model.Entry;
import com.example.jsonreader.model.Section;

public class BichosAdapter extends RecyclerView.Adapter<BichosAdapter.ViewHolder> {

    private List<Entry> entries;
    private Context context;
    private BichosAdapterListener listener;

    public interface BichosAdapterListener {
        void onCardSelected(Entry selected);
    }

    public BichosAdapter(Context context, List<Section> bestiary, BichosAdapterListener listener) {
        this.context = context;
        this.listener = listener;

        // Asumimos que los entries están en la sección de bestiary
        // Puedes adaptar esto si necesitas una estructura diferente
        this.entries = bestiary.get(0).getEntries(); // Obtener los entries de la primera sección por ejemplo
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bichos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry current = entries.get(position);
        holder.txvName.setText(current.getTitle());
        Glide.with(context).load(current.getImage()).into(holder.imvPhoto);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCardSelected(current);
            }
        });

        Log.d("BICHOS_ADAPTER", "Mostrando bicho: " + current.getTitle());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvName;
        TextView txvDesc;
        ImageView imvPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvName = itemView.findViewById(R.id.txvName); // Asegúrate de que este ID exista en tu layout
            imvPhoto = itemView.findViewById(R.id.imvPhoto); // Asegúrate de que este ID exista en tu layout
        }
    }
}
