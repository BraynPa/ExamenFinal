package com.example.vj20222.dao;

import androidx.room.Dao;

import com.example.vj20222.entities.Contact;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    List<Contact> getAll();

    @Query("SELECT * FROM contacts WHERE id = :id")
    Contact find(int id);

    @Insert
    void create(Contact contact);

    @Update
    void update(Contact contact);
    @Delete
    void delete(Contact contact);
}
