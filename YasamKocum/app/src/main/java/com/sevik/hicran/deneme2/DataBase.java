package com.sevik.hicran.deneme2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="YasamKocu";
    private static String TABLE_PROFİL="Profil";
    private static int DATABASE_VERSION=1;

    public static final String ROW_ID="id";
    public static final String ROW_NAME="Ad";
    public static final String ROW_SURNAME="Soyad";
    public static final String ROW_WEIGHT="Kilo";
    public static final String ROW_HEIGHT="Boy";
    public static final String ROW_AGE="Yas";
    public static final String ROW_GENDER="Cinsiyet";

    public DataBase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+
                TABLE_PROFİL +"("+ ROW_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," +
                ROW_NAME + " TEXT ,"+
                ROW_SURNAME + " TEXT NOT NULL ,"+
                ROW_WEIGHT+" REAL ,"+
                ROW_HEIGHT +" REAL ,"+
                ROW_AGE+" INTEGER ,"+
                ROW_GENDER+" TEXT );";
        db.execSQL(query);
        /* db.execSQL("CREATE TABLE " + TABLE_PROFİL +" ( " + ROW_ID +" INTEGER PRIMARY KEY, "+ ROW_NAME +" TEXT NOT NULL, "+ ROW_SURNAME + " TEXT NOT NULL");
                +" TEXT NOT NULL, "+ROW_HEIGHT+" TEXT NOT NULL, "+ROW_WEIGHT+" TEXT NOT NULL, "+ROW_AGE+" TEXT NOT NULL, "+ROW_GENDER+" TEXT NOT NULL)");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXIST "+ TABLE_PROFİL);
        onCreate(db);
    }

    public long veriEkle(Person nesne){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(ROW_NAME,nesne.getAd().trim());
        cv.put(ROW_SURNAME,nesne.getSoyad().trim());
        cv.put(ROW_WEIGHT,nesne.getKilo());
        cv.put(ROW_HEIGHT,nesne.getBoy());
        cv.put(ROW_AGE,nesne.getYas());
        cv.put(ROW_GENDER,nesne.getCinsiyet().trim());
        long kontrol = db.insert(TABLE_PROFİL , null , cv);
        db.close();
        return kontrol;
    }

    public Person getPersonDB()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Person personel = new Person();
        String sqlSorgusu="Select * from " + TABLE_PROFİL;
        Cursor c=db.rawQuery(sqlSorgusu, null);
        int kolonAd=c.getColumnIndex(ROW_NAME);
        int kolonKilo=c.getColumnIndex(ROW_WEIGHT);
        int kolonSoyad=c.getColumnIndex(ROW_SURNAME);
        int kolonBoy=c.getColumnIndex(ROW_HEIGHT);
        int kolonYas=c.getColumnIndex(ROW_AGE);//
        int kolonCinsiyet=c.getColumnIndex(ROW_GENDER);

        try {
            c.moveToLast();
            personel.setAd(c.getString(kolonAd));
            personel.setSoyad(c.getString(kolonSoyad));
            personel.setCinsiyet(c.getString(kolonCinsiyet));
            personel.setKilo(c.getFloat(kolonKilo));
            personel.setBoy(c.getFloat(kolonBoy));
            personel.setYas(c.getInt(kolonYas));
        }
        catch (Exception e){
            return  null;
        }
        finally {
            c.close();
            db.close();
        }
        return personel;
    }
}
