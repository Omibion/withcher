package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bumptech.glide.Glide;
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

        // Carga la imagen correspondiente a la sección desde drawable
        String imageName = section.getImage(); // Asegúrate de que esto contenga solo el nombre de la imagen sin extensión
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Comprueba si la imagen se ha encontrado
        if (resId != 0) {
            Glide.with(context).load(resId).into(holder.fototipo); // Cargar imagen usando Glide
        } else {
            // Opcional: manejar el caso en que la imagen no se encuentra (por ejemplo, cargar una imagen por defecto)
            holder.fototipo.setImageResource(R.drawable.bestiary_cards_beasts); // Reemplaza "default_image" con una imagen predeterminada que tengas
        }

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
        ImageView fototipo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textoSobreImagen); // Asegúrate de que este ID exista en tu layout
            fototipo = itemView.findViewById(R.id.fototipo); // Asegúrate de que este ID exista en tu layout
        }
    }
}

