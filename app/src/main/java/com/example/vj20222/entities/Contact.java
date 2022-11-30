package com.example.vj20222.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="name")
    public String name;
    @ColumnInfo(name="avatar")
    public String avatar;
    @ColumnInfo(name="telefono")
    public String telefono;
}
