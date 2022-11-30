package com.example.vj20222.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movimientos")
public class Movimiento {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="Tipo")
    public Boolean Tipo;
    @ColumnInfo(name="Monto")
    public Float Monto;
    @ColumnInfo(name="Motivo")
    public String Motivo;
    @ColumnInfo(name="latitude")
    public float latitude;
    @ColumnInfo(name="longitude")
    public float longitude;
    @ColumnInfo(name="IdCuenta")
    public int IdCuenta;
}
