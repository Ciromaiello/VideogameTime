package com.example.cirom.videogametime.tutorial.selezione_piattaforme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cirom.videogametime.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PiattaformeAdapter extends RecyclerView.Adapter<PiattaformeAdapter.ViewHolder> {

    ArrayList<Piattaforme> piattaforme;

    public PiattaformeAdapter(Context context, List<Piattaforme> piattaforme) {
        this.piattaforme= new ArrayList<>(piattaforme);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(piattaforme.get(position));
        holder.checkbox2.setOnCheckedChangeListener(null);
        holder.checkbox2.setChecked(piattaforme.get(position).isSelected1());
        holder.checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                piattaforme.get(holder.getAdapterPosition()).setSelected1(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return piattaforme.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textpiattaforme;
        private CheckBox checkbox2;
        private ImageView imageConsole;

        public ViewHolder(View v) {
            super(v);
            textpiattaforme= (TextView) v.findViewById(R.id.textpiattaforme);
            checkbox2 = (CheckBox) v.findViewById(R.id.checkbox2);
            imageConsole = (ImageView) v.findViewById(R.id.imageConsole);
        }

        public void bindData(Piattaforme console) {
            textpiattaforme.setText(console.getNome());
            Glide.with(itemView.getContext()).load(console.getImage()).into(imageConsole);
        }
    }
}