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


        this.entries = section.getEntries();
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

        String imageName = current.getImage();

        int resId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (resId != 0) {
            Glide.with(context)
                    .load(resId)
                    .placeholder(R.drawable.bestiary_abaya_654x727)
                    .error(R.drawable.bestiary_abaya_654x727)
                    .into(holder.imvPhoto);
        } else {

            holder.imvPhoto.setImageResource(R.drawable.bestiary_abaya_654x727);
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
            txvName = itemView.findViewById(R.id.txvName);
            imvPhoto = itemView.findViewById(R.id.imvPhoto);
        }
    }
}
