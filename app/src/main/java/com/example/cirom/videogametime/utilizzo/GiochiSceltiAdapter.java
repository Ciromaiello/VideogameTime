package com.example.cirom.videogametime.utilizzo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.example.cirom.videogametime.tutorial.selezione_giochi.GiochiActivity;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class GiochiSceltiAdapter extends RecyclerView.Adapter<GiochiSceltiAdapter.MyViewHolder> {

    private ArrayList<Giochi> giochi;
    private Context mContext;

    public GiochiSceltiAdapter(Context mContext, ArrayList<Giochi> giochi) {
        this.giochi = giochi;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gamescelta, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.bindData(giochi.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account.fatto = true;
                Intent intent = new Intent(mContext,GiochiActivity.class);
                intent.putExtra("Title",giochi.get(position).getNome());
                intent.putExtra("Description",giochi.get(position).getDescrizione());
                intent.putExtra("Generi",giochi.get(position).getGeneri());
                intent.putExtra("Piattaforme",giochi.get(position).getPiattaforme());
                intent.putExtra("Image",giochi.get(position).getImmagine());
                intent.putExtra("id", giochi.get(position).getId_gioco());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return giochi.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView game_title;
        ImageView game_img;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            game_title = (TextView) itemView.findViewById(R.id.game_title) ;
            game_img = (ImageView) itemView.findViewById(R.id.game_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

        }

        public void bindData(Giochi game) {
            game_title.setText(game.getNome());
            Picasso.with(itemView.getContext()).load(game.getImmagine()).into(game_img);
        }

    }
}