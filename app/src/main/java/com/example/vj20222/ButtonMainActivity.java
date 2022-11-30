package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vj20222.database.AppDataBase;
import com.example.vj20222.entities.Cuenta;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.CuentaService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ButtonMainActivity extends AppCompatActivity {
    Button btnLista,btnBd,btnSinc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_main);
        AppDataBase db = AppDataBase.getInstance(this);
        Retrofit retrofit = new RetrofitFactory(this).build();
        CuentaService service = retrofit.create(CuentaService.class);
        btnLista = findViewById(R.id.btnLista);
        btnBd = findViewById(R.id.btnBd);
        btnSinc = findViewById(R.id.btnSinc);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato = 1+"";
                Intent intent = new Intent(ButtonMainActivity.this, ListCuentaActivity.class);
                intent.putExtra("dato",dato.toString());
                startActivity(intent);
            }
        });
        btnBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dato = 2+"";
                Intent intent = new Intent(ButtonMainActivity.this, ListCuentaActivity.class);
                intent.putExtra("dato",dato.toString());
                startActivity(intent);
            }
        });
        btnSinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.all().enqueue(new Callback<List<Cuenta>>() {
                    @Override
                    public void onResponse(Call<List<Cuenta>> call, Response<List<Cuenta>> response) {
                        List<Cuenta> data = response.body();
                        List<Cuenta> info = db.cuentaDao().getAll();

                        if(data.size() > info.size()){
                            for(int i=info.size(); i<data.size();i++){
                                Cuenta contactAux = data.get(i);
                                if(contactAux != null){
                                    Cuenta dataaux = new Cuenta();
                                    dataaux.Nombre = contactAux.Nombre;
                                    db.cuentaDao().create(dataaux);
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Cuenta>> call, Throwable t) {

                    }
                });
                Intent intent = new Intent(ButtonMainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}