package com.example.vj20222.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vj20222.entities.Cuenta;

import java.util.List;
@Dao
public interface CuentaDao {
    @Query("SELECT * FROM cuentas")
    List<Cuenta> getAll();

    @Query("SELECT * FROM cuentas WHERE id = :id")
    Cuenta find(int id);

    @Insert
    void create(Cuenta cuenta);

    @Update
    void update(Cuenta cuenta);
    @Delete
    void delete(Cuenta cuenta);
}
