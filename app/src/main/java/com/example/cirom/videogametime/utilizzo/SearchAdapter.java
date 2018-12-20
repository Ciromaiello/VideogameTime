package com.example.cirom.videogametime.utilizzo;



import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cirom.videogametime.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dushyant Mainwal on 29-Oct-17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    ArrayList<String> giochiNameList;
    ArrayList<String> giochiPicList;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView giocoImage;
        TextView  gioco_name;

        public SearchViewHolder(View itemView) {
            super(itemView);
            giocoImage = (ImageView) itemView.findViewById(R.id.giocoimg);
            gioco_name = (TextView) itemView.findViewById(R.id.gioco_name);

        }
    }

    public SearchAdapter(Context context, ArrayList<String> giochiNameList,ArrayList<String> giochiPicList) {
        this.context = context;
        this.giochiNameList = giochiNameList;
        this.giochiPicList = giochiPicList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        return new SearchAdapter.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.gioco_name.setText(giochiNameList.get(position));
       Picasso.with(context).load(giochiPicList.get(position)).placeholder(R.mipmap.ic_launcher_round).transform(new CircleTransform(35,10)).into(holder.giocoImage);

        holder.gioco_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Full Name Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return giochiNameList.size();
    }
}
