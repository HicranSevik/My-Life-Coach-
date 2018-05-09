package com.sevik.hicran.deneme2;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Profil extends Fragment {


    public Profil() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        DataBase vtIslemleri = new DataBase(view.getContext());

        TextView ad = (TextView) view.findViewById(R.id.txtPerAd);
        TextView soyAd = (TextView) view.findViewById(R.id.txtPerSoyad);
        TextView boy = (TextView) view.findViewById(R.id.txtPerBoy);
        TextView kilo = (TextView) view.findViewById(R.id.txtPerKilo);
        TextView yas = (TextView) view.findViewById(R.id.txtPerYas);
        TextView cinsiyet = (TextView) view.findViewById(R.id.txtPerCinsiyet);
        TextView txtVucutIndex = (TextView) view.findViewById(R.id.txtPerVKI);
        TextView txtideKilo = (TextView) view.findViewById(R.id.txtPerIdKilo);
        TextView txtBazal = (TextView) view.findViewById(R.id.textViewBazal);
        Person getirPersonel = vtIslemleri.getPersonDB();
        if (getirPersonel != null) {
            ad.setText(getirPersonel.getAd());
            soyAd.setText(getirPersonel.getSoyad());
            boy.setText(String.format("%.2f",getirPersonel.getBoy()));
            kilo.setText(String.valueOf(getirPersonel.getKilo()));
            yas.setText(String.valueOf(getirPersonel.getYas()));
            cinsiyet.setText(getirPersonel.getCinsiyet());

            float vki = VucutKitleIndeksi(getirPersonel.getKilo(), getirPersonel.getBoy());
            txtVucutIndex.setText(String.valueOf(vki));
            float idealKilo = idealKiloHesapla(getirPersonel);
            txtideKilo.setText(String.valueOf(idealKilo));
            float bazal = BazalMetabolizmaHesapla(getirPersonel);
            txtBazal.setText(String.valueOf(bazal));

        } else {
            Intent gecis = new Intent(view.getContext(), Kisiselbilgiler.class);
            startActivity(gecis);

        }
        return view;

    }

    public float VucutKitleIndeksi(float kilo, float boy) {

        float sonuc = (kilo / (boy * boy));
        return sonuc;
    }

    public float idealKiloHesapla(Person person) {
        float idealKilo = 0;
        String boyConvert = String.format("%.2f",person.getBoy());
        int boyCm = Integer.parseInt(boyConvert.replace(".", ""));
        switch (person.getCinsiyet()) {
            case "Erkek":
                idealKilo = (50.0f + (2.3f) * ((boyCm - 152.4f) / 2.54f));
                break;
            case "Kadın":
                idealKilo = (45.5f + (2.3f) * ((boyCm - 152.4f) / 2.54f));
                break;
        }

        return idealKilo;
    }

    public float BazalMetabolizmaHesapla(Person person) {
        float BazalMetabolizma = 0;

        switch (person.getCinsiyet()) {
            case "Erkek":
                BazalMetabolizma = (person.getKilo() * 24 * 1);//Ağırlık(kg) X 24 x 1 kkal
                break;
            case "Kadın":
                BazalMetabolizma = (person.getKilo() * 24 * 0.95f);//Ağırlık(kg) X 24 x 0,95 kkal
                break;
        }

        return BazalMetabolizma;
    }

}