package com.sevik.hicran.deneme2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by deneme on 26.12.2017.
 */

public class BesinAsync extends AsyncTask<Void, Void, Void> {
    String data = "";
    ProgressDialog progressDialog;
    Context context;
    public static ArrayList<BesinModel> besinArraylist=new ArrayList<>();//
    //public static ArrayList<String> besinArraylist = new ArrayList<>();

    public BesinAsync(Context ctx) {
        this.context = ctx;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            URL url = new URL("http://10.34.143.174:8080/");
            HttpURLConnection baglanti = (HttpURLConnection) url.openConnection();
            InputStream inInputStream = baglanti.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inInputStream));
            String line = "";
            while (line != null) {
                line = reader.readLine();
                data += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(this.context);
        progressDialog.setMessage("Veriler Yükleniyor..");
        progressDialog.setTitle("Lütfen Bekleyin...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println(data);

        try {
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                BesinModel besinNesne = new BesinModel();
                besinNesne.setBesinAdi((String) object.getString("besin"));
                besinNesne.setKalori((Integer) object.getInt("kalori"));
                besinNesne.setMiktar((String) object.getString("birim"));

                besinArraylist.add(besinNesne);
            }
            Fragment1.besinListView.setAdapter(Fragment1.adapter);
            progressDialog.hide();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
