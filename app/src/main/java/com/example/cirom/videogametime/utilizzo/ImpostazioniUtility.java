package com.example.cirom.videogametime.utilizzo;

import android.content.Context;

import com.example.cirom.videogametime.R;

import java.util.ArrayList;
import java.util.List;

public class ImpostazioniUtility {

    private String [] info;
    private Context context;
    private List<Impostazioni> lista;
    private static ImpostazioniUtility impostazioniUtility;

    private ImpostazioniUtility(Context context){
        this.context=context;
        info =context.getResources().getStringArray(R.array.info);

        lista=new ArrayList<Impostazioni>();
        for(int i=0;i<info.length;i++){
            Impostazioni imp =new Impostazioni(info[i]);
        }
    }

    public static ImpostazioniUtility getImpostazioniUtility(Context context){
        if(impostazioniUtility==null){
            impostazioniUtility=new ImpostazioniUtility(context);
        }
        return impostazioniUtility;
    }

    public List<Impostazioni> getImpostazioni(){
        return this.lista;
    }

    public String[] getInfo() {
        return info;
    }
}



