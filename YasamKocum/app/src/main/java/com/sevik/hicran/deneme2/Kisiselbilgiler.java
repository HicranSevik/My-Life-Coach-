package com.sevik.hicran.deneme2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Kisiselbilgiler extends AppCompatActivity {
    public EditText ad,soyad,kilo,boy,yas;
    public RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiselbilgiler);

         ad=(EditText)findViewById(R.id.editTextName);
         soyad=(EditText)findViewById(R.id.editTextSurname);
         kilo= (EditText)findViewById(R.id.editTextWeight);
         boy=(EditText)findViewById(R.id.editTextHeight);
         yas=(EditText)findViewById(R.id.editTextage);
         radioGroup = (RadioGroup)findViewById(R.id.radioGrup);
        Button btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis= new Intent(Kisiselbilgiler.this,MainActivity.class);
                startActivity(gecis);
            }
        });
        Button kaydet=(Button)findViewById(R.id.buttonKaydet);
        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tiklananradioId = radioGroup.getCheckedRadioButtonId();
                RadioButton cinsiyet = (RadioButton)findViewById(tiklananradioId);
                Person person=new Person();
                person.setAd(ad.getText().toString());
                person.setSoyad(soyad.getText().toString());
                person.setKilo(Float.parseFloat(kilo.getText().toString()));
                person.setBoy(Float.parseFloat(boy.getText().toString()));
                person.setCinsiyet(cinsiyet.getText().toString());
                person.setYas(Integer.parseInt(yas.getText().toString()));
                DataBase vtIslemleri=new DataBase(Kisiselbilgiler.this);
                long id = vtIslemleri.veriEkle(person);
                if (id!=-1)
                {
                    Toast.makeText(Kisiselbilgiler.this,"Ekleme Başarılı",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Kisiselbilgiler.this,"Ekleme işlemi Başarısız",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
