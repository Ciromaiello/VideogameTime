package com.example.cirom.videogametime;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

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

        //in some cases, it will prevent unwanted situations
        holder.checkbox.setOnCheckedChangeListener(null);

        //if true, your checkbox will be selected, else unselected
        holder.checkbox.setChecked(generi.get(position).isSelected());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textparole;
        private CheckBox checkbox;

        public ViewHolder(View v) {
            super(v);
            textparole = (TextView) v.findViewById(R.id.textparole);
            checkbox = (CheckBox) v.findViewById(R.id.checkbox);
        }

        public void bindData(Generi gen) {
            textparole.setText(gen.getTextparole());
        }
    }
}
