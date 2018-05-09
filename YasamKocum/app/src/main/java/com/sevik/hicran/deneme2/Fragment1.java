package com.sevik.hicran.deneme2;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment1 extends Fragment {

    static ListView besinListView;
    static BesinAdapter adapter;
    String[] araOgun = new String[]{"Kahvaltı", "Ara öğün-Sabah", "Öğle Yemeği","Ara Öğün -Öğleden Sonra","Akşam Yemeği","Ara öğün-Gece"};

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        BesinAsync veriCek = new BesinAsync(view.getContext());
        veriCek.execute();

        besinListView = (ListView) view.findViewById(R.id.besinListView);
        adapter = new BesinAdapter((Activity) view.getContext(), BesinAsync.besinArraylist);
        besinListView.setAdapter(adapter);

        besinListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String besinAdi = BesinAsync.besinArraylist.get(position).getBesinAdi();
                String besinMiktar = BesinAsync.besinArraylist.get(position).getMiktar();
                int kalori = BesinAsync.besinArraylist.get(position).getKalori();

                View popupView = inflater.inflate(R.layout.activity_popup, null);

                Spinner aragOgunSpin = (Spinner) popupView.findViewById(R.id.spinner);
                ArrayAdapter<String> spinAdapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_item,araOgun);
                spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                aragOgunSpin.setAdapter(spinAdapter);

                TextView txtBesinAdi= (TextView) popupView.findViewById(R.id.txtPopupBesinAdi);
                TextView txtKaloriMiktar = (TextView) popupView.findViewById(R.id.txtPopupKalori);

                txtBesinAdi.setText(besinAdi);
                txtKaloriMiktar.setText(besinMiktar+'x'+kalori);
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(popupView);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }

}
