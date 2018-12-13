package com.example.cirom.videogametime.tutorial.selezione_generi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi.checkato;
import static com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi.giochi;
import static com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi.giochige;
import static com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi.nomi;
import static com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi.nomige;

public class GeneriAdapter extends RecyclerView.Adapter<GeneriAdapter.ViewHolder> {

    ArrayList<Generi> generi;
    FirebaseFirestore mFirestore;
    CollectionReference mCollection;

    public GeneriAdapter(List<Generi> generi) {
        this.generi= new ArrayList<>(generi);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        mFirestore= FirebaseFirestore.getInstance();
        mCollection=mFirestore.collection("Giochi");
        checkato = false;
        giochige = new ArrayList<>();
        nomige = new ArrayList<>();
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(generi.get(position));
        holder.checkbox1.setOnCheckedChangeListener(null);
        holder.checkbox1.setChecked(generi.get(position).isSelected());
        holder.checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                generi.get(holder.getAdapterPosition()).setSelected(isChecked);
                mCollection.whereArrayContains("generi", generi.get(position).getGenere())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Giochi game = documentSnapshot.toObject(Giochi.class);
                                    String name = game.getNome();
                                    if(isChecked)
                                    {
                                    if (!nomige.contains(name)) {
                                        giochige.add(game);
                                        nomige.add(name);
                                    }
                                    }
                                    else
                                    {
                                        if(nomige.contains(name))
                                        {
                                            giochige.remove(game);
                                            nomige.remove(name);
                                        }
                                    }
                                }

                            }
                        });
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

            textgeneri.setText(gen.getGenere());
        }


    }
}
