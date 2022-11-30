package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.vj20222.Adapter.ContactAdapter;
import com.example.vj20222.database.AppDataBase;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactActivity extends AppCompatActivity {

    RecyclerView rvContact;
    Button btnAgregarC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        AppDataBase db = AppDataBase.getInstance(this);
        Retrofit retrofit = new RetrofitFactory(this).build();
        ContactService service = retrofit.create(ContactService.class);
        service.all().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> data = response.body();
                List<Contact> info = db.contactDao().getAll();

                Log.i("Main", new Gson().toJson(info));
                if(data.size() < info.size()){

                    for(int i=data.size(); i<info.size();i++){
                        Contact contactAux = info.get(i);
                        service.create(contactAux).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {

                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {


                            }
                        });
                    }

                }if(data.size() == info.size()){
                    for(int i=0; i<data.size();i++){
                        Contact contactAux = data.get(i);
                        Contact contactAux2 = info.get(i);
                        if(contactAux != null && contactAux2 != null){
                            if(contactAux != contactAux2){

                                service.update(contactAux2,contactAux2.id).enqueue(new Callback<Contact>() {
                                    @Override
                                    public void onResponse(Call<Contact> call, Response<Contact> response) {

                                    }

                                    @Override
                                    public void onFailure(Call<Contact> call, Throwable t) {

                                    }
                                });
                            }
                        }
                    }

                }if(data.size() > info.size()){
                    for(int i=0; i<data.size();i++){
                        Contact contactAux = data.get(i);
                        if(contactAux != null){
                            Contact dataaux = new Contact();
                            dataaux.avatar = contactAux.avatar;
                            dataaux.telefono = contactAux.telefono;
                            dataaux.name = contactAux.name;
                            db.contactDao().create(dataaux);
                        }
                    }
                }
                rvContact = findViewById(R.id.rvContact);
                rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvContact.setAdapter(new ContactAdapter((data)));
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                List<Contact> data = db.contactDao().getAll();
                rvContact = findViewById(R.id.rvContact);
                rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvContact.setAdapter(new ContactAdapter((data)));
            }
        });
        btnAgregarC = findViewById(R.id.btnAgregarC);
        btnAgregarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactActivity.this, FormContactActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}