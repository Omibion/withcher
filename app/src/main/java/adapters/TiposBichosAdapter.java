package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.jsonreader.R;
import com.example.jsonreader.model.Section;

public class TiposBichosAdapter extends RecyclerView.Adapter<TiposBichosAdapter.ViewHolder> {

    private List<Section> secciones;
    private Context context;
    private TiposBichosAdapterListener listener;

    public interface TiposBichosAdapterListener {
        void onCardSelected(Section selected);
    }

    public TiposBichosAdapter(Context context, List<Section> secciones, TiposBichosAdapterListener listener) {
        this.context = context;
        this.secciones = secciones;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tipos_de_bichos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Section section = secciones.get(position);
        holder.textView.setText(section.getTitle());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCardSelected(section);
            }
        });
    }

    @Override
    public int getItemCount() {
        return secciones.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textoSobreImagen); // Asegúrate de que este ID exista en tu layout
        }
    }
}

