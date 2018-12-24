package com.example.cirom.videogametime.tutorial.selezione_giochi;


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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
        TextView rece_title, nome;
        ImageView personal_img;
        RatingBar stars;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.textName);
            rece_title = (TextView) itemView.findViewById(R.id.textTitleRec);
            stars = (RatingBar) itemView.findViewById(R.id.gettingStar2);
            personal_img = (ImageView) itemView.findViewById(R.id.imageAccountRec);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }

        public void bindData(Recensione rece) {
            stars.setRating(rece.getStars());
            nome.setText(rece.getPersonName());
            rece_title.setText(rece.getTitolo());
            Picasso.with(itemView.getContext()).load(rece.getPersonImm()).into(personal_img);
        }
    }
}
