package com.example.cirom.videogametime.utilizzo;



import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cirom.videogametime.R;
import com.example.cirom.videogametime.tutorial.selezione_giochi.Giochi;
import com.example.cirom.videogametime.tutorial.selezione_giochi.GiochiActivity;
import com.example.cirom.videogametime.tutorial.selezione_giochi.SelectionGiochiActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Dushyant Mainwal on 29-Oct-17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    Context context;
    private FirebaseFirestore mFirestore;
    private CollectionReference mGiochi;
    ArrayList<String> giochiNameList;
    ArrayList<String> giochiPicList;
    ArrayList<String> key;
    ArrayList<String> piattaforme;
    ArrayList<String> generi;
    static String description;
    float NumRating;

    class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView giocoImage;
        TextView  gioco_name;

        public SearchViewHolder(View itemView) {
            super(itemView);
            giocoImage = (ImageView) itemView.findViewById(R.id.giocoimg);
            gioco_name = (TextView) itemView.findViewById(R.id.gioco_name);
        }
    }

    public SearchAdapter(Context context, ArrayList<String> giochiNameList,ArrayList<String> giochiPicList, ArrayList<String> key) {
        this.context = context;
        this.giochiNameList = giochiNameList;
        this.giochiPicList = giochiPicList;
        this.key=key;
    }


    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_list_item, parent, false);
        mFirestore = FirebaseFirestore.getInstance();
        mGiochi = mFirestore.collection("Giochi");
        return new SearchAdapter.SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(SearchViewHolder holder, final int position) {
        holder.gioco_name.setText(giochiNameList.get(position));
       Picasso.with(context).load(giochiPicList.get(position)).placeholder(R.mipmap.ic_launcher_round).transform(new CircleTransform(35,10)).into(holder.giocoImage);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mGiochi.document(key.get(position))
                       .get()
                       .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                           @Override
                           public void onSuccess(DocumentSnapshot documentSnapshot) {
                               Giochi g = documentSnapshot.toObject(Giochi.class);
                               Intent intent = new Intent(context,GiochiActivity.class);
                               intent.putStringArrayListExtra("Generi",g.getGeneri());
                               intent.putStringArrayListExtra("Piattaforme",g.getPiattaforme());
                               intent.putExtra("Description",g.getDescrizione());
                               intent.putExtra("Title",g.getNome());
                               intent.putExtra("Image",g.getImmagine());
                               intent.putExtra("id",g.getId_gioco());
                               context.startActivity(intent);
                           }
                       });
           }
       });
    }

    @Override
    public int getItemCount() {
        return giochiNameList.size();
    }
}
