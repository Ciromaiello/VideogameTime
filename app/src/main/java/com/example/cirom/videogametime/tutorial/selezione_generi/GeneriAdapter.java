package com.example.cirom.videogametime.tutorial.selezione_generi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.cirom.videogametime.R;

import java.util.ArrayList;
import java.util.List;

public class GeneriAdapter extends RecyclerView.Adapter<GeneriAdapter.ViewHolder> {

    ArrayList<Generi> generi;

    public GeneriAdapter(List<Generi> generi) {
        this.generi= new ArrayList<>(generi);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(generi.get(position));
        holder.checkbox1.setOnCheckedChangeListener(null);
        holder.checkbox1.setChecked(generi.get(position).isSelected());
        holder.checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                generi.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return generi.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textgeneri;
        private CheckBox checkbox1;

        public ViewHolder(View v) {
            super(v);
            textgeneri= (TextView) v.findViewById(R.id.textgeneri);
            checkbox1 = (CheckBox) v.findViewById(R.id.checkbox1);
        }

        public void bindData(Generi gen) {
            textgeneri.setText(gen.getTextgeneri());
        }
    }
}
