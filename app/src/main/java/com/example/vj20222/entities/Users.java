package com.example.vj20222.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Users {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name="name")
    public String username;
    @ColumnInfo(name="pass")
    public String password;
    @ColumnInfo(name="token")
    public String token;
}
