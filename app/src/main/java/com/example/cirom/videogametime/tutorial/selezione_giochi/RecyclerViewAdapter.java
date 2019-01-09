package com.example.cirom.videogametime.tutorial.selezione_giochi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.cirom.videogametime.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<Giochi> giochi;
    private Context mContext;
    private final SparseBooleanArray array = new SparseBooleanArray();

    public RecyclerViewAdapter(Context mContext, ArrayList<Giochi> giochi) {
        this.giochi = giochi;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new MyViewHolder(v);



    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if(array.get(position)){
            holder.checkBox.setChecked(true);
        }else{
            holder.checkBox.setChecked(false);
        }

        holder.bindData(giochi.get(position));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              if(!giochi.get(position).isSelezionato()){
                  array.put(position,true);
                giochi.get(position).setSelezionato(true);
                  Log.e("hai cliccato", "onClick: yes");}
                  else{ giochi.get(position).setSelezionato(false);
                  array.put(position,false);}

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
        CheckBox checkBox;

        public MyViewHolder(final View itemView) {
            super(itemView);
            game_title = (TextView) itemView.findViewById(R.id.game_title) ;
            game_img = (ImageView) itemView.findViewById(R.id.game_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
            checkBox =itemView.findViewById(R.id.checkBox4);


        }

        public void bindData(Giochi game) {
            game_title.setText(game.getNome());
            Picasso.with(itemView.getContext()).load(game.getImmagine()).into(game_img);

        }
    }
}