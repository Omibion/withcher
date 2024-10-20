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

    public BichosAdapter(Context context, Section section, BichosAdapterListener listener) {
        this.context = context;
        this.listener = listener;

        // Asigna las entradas de la sección seleccionada
        this.entries = section.getEntries(); // Obtener los entries de la sección seleccionada
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

        // Obtiene el nombre de la imagen desde el JSON
        String imageName = current.getImage(); // Asegúrate de que esto contenga solo el nombre de la imagen sin extensión

        // Obtén el ID del recurso drawable correspondiente
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Cargar imagen usando Glide
        if (resId != 0) {
            Glide.with(context)
                    .load(resId) // Carga la imagen desde drawable
                    .placeholder(R.drawable.bestiary_abaya_654x727) // Imagen de placeholder
                    .error(R.drawable.bestiary_abaya_654x727) // Imagen de error si falla la carga
                    .into(holder.imvPhoto); // Donde se mostrará la imagen
        } else {
            // Si no se encuentra la imagen, establece una imagen por defecto
            holder.imvPhoto.setImageResource(R.drawable.bestiary_abaya_654x727); // Reemplaza con una imagen predeterminada
        }

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
