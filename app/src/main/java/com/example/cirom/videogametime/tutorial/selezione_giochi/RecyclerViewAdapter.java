package com.example.cirom.videogametime.tutorial.selezione_giochi;

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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Giochi> mData ;


    public RecyclerViewAdapter(Context mContext, List<Giochi> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_game, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.game_title.setText(mData.get(position).getNome());
        holder.game_img.setImageResource(mData.get(position).getGameImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,GiochiActivity.class);

                // passing data to the game activity
                intent.putExtra("Title",mData.get(position).getNome());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Generi",mData.get(position).getPiattaforma());
                intent.putExtra("Piattaforme",mData.get(position).getGenere());
                intent.putExtra("Image",mData.get(position).getGameImage());
                // start the activity
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
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
    }
}

