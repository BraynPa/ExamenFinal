package com.example.vj20222.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cuentas")
public class Cuenta {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="Nombre")
    public String Nombre;
}
