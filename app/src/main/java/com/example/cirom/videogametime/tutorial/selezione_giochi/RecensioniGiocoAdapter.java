package com.example.cirom.videogametime.tutorial.selezione_giochi;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.utilizzo.Account;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class RecensioniGiocoAdapter extends RecyclerView.Adapter<RecensioniGiocoAdapter.MyViewHolder> {

    private ArrayList<Recensione> recensiones;
    private Context mContext;

    public RecensioniGiocoAdapter(Context mContext, ArrayList<Recensione> recensiones) {
        this.mContext = mContext;
        this.recensiones = recensiones;
    }

    @Override
    public void onBindViewHolder(RecensioniGiocoAdapter.MyViewHolder holder, final int position) {
        holder.bindData(recensiones.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account.posizione_recensione = true;
                Intent intent = new Intent(mContext,RecensioneCompletaActivity.class);
                intent.putExtra("idgioco", recensiones.get(position).getIdgioco());
                intent.putExtra("id", recensiones.get(position).getId());
                intent.putExtra("Title",recensiones.get(position).getTitolo());
                intent.putExtra("Recensione",recensiones.get(position).getRecensione());
                intent.putExtra("PersonImm",recensiones.get(position).getPersonImm());
                intent.putExtra("PersonName",recensiones.get(position).getPersonName());
                intent.putExtra("Stars",recensiones.get(position).getStars());
                intent.putExtra("Time", recensiones.get(position).getData().toLocaleString());
                intent.putExtra("Gioco", recensiones.get(position).getTitle());
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public RecensioniGiocoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recensione_item, parent, false);
        return new RecensioniGiocoAdapter.MyViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return recensiones.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rece_title, nome, data;
        ImageView personal_img;
        RatingBar stars;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textName);
            rece_title = itemView.findViewById(R.id.textTitleRec);
            data = itemView.findViewById(R.id.textDate);
            stars = itemView.findViewById(R.id.gettingStar2);
            personal_img = itemView.findViewById(R.id.image_account_rec);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bindData(Recensione rece) {
            stars.setRating(rece.getStars());
            nome.setText(rece.getPersonName());
            rece_title.setText(rece.getTitolo());
            data.setText(rece.getData().toLocaleString());
            Picasso.with(itemView.getContext()).load(rece.getPersonImm()).into(personal_img);
        }
    }
}