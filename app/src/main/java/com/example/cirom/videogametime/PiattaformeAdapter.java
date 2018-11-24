package com.example.cirom.videogametime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PiattaformeAdapter extends RecyclerView.Adapter<PiattaformeAdapter.ViewHolder> {

    private Context mContext;
    ArrayList<Piattaforme> piattaforme;

    public PiattaformeAdapter(List<Piattaforme> piattaforme) {
        this.piattaforme= new ArrayList<>(piattaforme);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Piattaforme console = piattaforme.get(position);
        holder.bindData(piattaforme.get(position));

        //in some cases, it will prevent unwanted situations
        holder.checkbox2.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkbox2.setChecked(piattaforme.get(position).isSelected1());

        holder.checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                piattaforme.get(holder.getAdapterPosition()).setSelected1(isChecked);
            }
        });
        //Glide.with(mContext).load(console.getImage()).into(holder.imageConsole);
    }

    @Override
    public int getItemCount() {
        return piattaforme.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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
            textpiattaforme.setText(console.getTextpiattaforme());
        }
    }
}