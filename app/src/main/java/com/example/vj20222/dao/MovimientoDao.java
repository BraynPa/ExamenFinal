package com.example.vj20222.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.vj20222.entities.Movimiento;

import java.util.List;

@Dao
public interface MovimientoDao {
    @Query("SELECT * FROM movimientos")
    List<Movimiento> getAll();

    @Query("SELECT * FROM movimientos WHERE id = :id")
    Movimiento find(int id);

    @Insert
    void create(Movimiento movimiento);

    @Update
    void update(Movimiento movimiento);
    @Delete
    void delete(Movimiento movimiento);
}
