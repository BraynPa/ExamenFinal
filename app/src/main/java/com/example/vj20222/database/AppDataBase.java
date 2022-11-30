package com.example.vj20222.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vj20222.dao.ContactDao;
import com.example.vj20222.dao.CuentaDao;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.entities.Cuenta;

@Database(entities = {Contact.class, Cuenta.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase{
    public abstract ContactDao contactDao();
    public abstract CuentaDao cuentaDao();
    public static AppDataBase getInstance(Context context){
        return Room.databaseBuilder(context, AppDataBase.class, "vj2022_db").allowMainThreadQueries().build();
    }
}
