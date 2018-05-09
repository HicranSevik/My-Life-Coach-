package com.sevik.hicran.deneme2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by deneme on 27.12.2017.
 */

public class BesinAdapter extends BaseAdapter{

   LayoutInflater layoutInflater;
    ArrayList<BesinModel> besinList;

    public BesinAdapter(Activity activity,ArrayList<BesinModel> besinList)
    {
        layoutInflater= (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.besinList=besinList;
    }
    @Override
    public int getCount() {
        return besinList.size();
    }

    @Override
    public Object getItem(int position) {
        return besinList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View besin_row;
        besin_row=layoutInflater.inflate(R.layout.besinler_row,null);
        TextView besinAdi= (TextView) besin_row.findViewById(R.id.besinAdi);
        TextView besinMiktar= (TextView) besin_row.findViewById(R.id.besinMiktar);
        TextView besinKalori= (TextView) besin_row.findViewById(R.id.besinKalori);

        besinAdi.setText(besinList.get(position).getBesinAdi());
        besinMiktar.setText(besinList.get(position).getMiktar());
        besinKalori.setText(String.valueOf(besinList.get(position).getKalori()));

        return besin_row;
    }

}
