package adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.example.jsonreader.R;
import com.example.jsonreader.model.Section;

public class TiposBichosAdapter extends RecyclerView.Adapter<TiposBichosAdapter.ViewHolder> {

    private List<Section> secciones;
    private Context context;
    private TiposBichosAdapterListener listener;
    private int selectedPosition = -1;

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int XXX_DO_NOT_USE_ME) {
        Section section = secciones.get(holder.getAdapterPosition());
        holder.textView.setText(section.getTitle().replace(" ", "\n"));

        String imageName = section.getImage();
        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (resId != 0) {
            Glide.with(context).load(resId).into(holder.fototipo);
        } else {
            holder.fototipo.setImageResource(R.drawable.bestiary_cards_beasts);
        }


        if (holder.getAdapterPosition() == selectedPosition) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(v -> {

            selectedPosition = holder.getAdapterPosition();
            notifyDataSetChanged();

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
            textView = itemView.findViewById(R.id.textoSobreImagen);
            fototipo = itemView.findViewById(R.id.fototipo);
        }
    }
}

