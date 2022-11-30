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
import com.example.vj20222.Adapter.CuentaAdapter;
import com.example.vj20222.database.AppDataBase;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.entities.Cuenta;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.example.vj20222.services.CuentaService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListCuentaActivity extends AppCompatActivity {
    RecyclerView rvContact;
    Button btnAgregarC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cuenta);
        AppDataBase db = AppDataBase.getInstance(this);
        Retrofit retrofit = new RetrofitFactory(this).build();
        CuentaService service = retrofit.create(CuentaService.class);
        int id = Integer.parseInt(getIntent().getStringExtra("dato"));
        if(id == 1){
            service.all().enqueue(new Callback<List<Cuenta>>() {
                @Override
                public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                    List<Cuenta> data = response.body();
                    rvContact = findViewById(R.id.rvContact);
                    rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rvContact.setAdapter(new CuentaAdapter((data)));
                }

                @Override
                public void onFailure(Call<List<Cuenta>> call, Throwable t) {

                }
            });


            btnAgregarC = findViewById(R.id.btnAgregarC);
            btnAgregarC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListCuentaActivity.this, FormCuentaActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }else{
            List<Cuenta> data =  db.cuentaDao().getAll();
            rvContact = findViewById(R.id.rvContact);
            rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rvContact.setAdapter(new CuentaAdapter((data)));
        }
    }
}