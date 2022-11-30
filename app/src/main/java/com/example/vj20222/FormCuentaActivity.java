package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vj20222.entities.Contact;
import com.example.vj20222.entities.Cuenta;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.example.vj20222.services.CuentaService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormCuentaActivity extends AppCompatActivity {
    Button btnGuardarC;
    EditText etNombreC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cuenta);
        Retrofit retrofit = new RetrofitFactory(this).build();
        CuentaService service = retrofit.create(CuentaService.class);
        btnGuardarC = findViewById(R.id.btnGuardarC);
        etNombreC = findViewById(R.id.etNombreC);
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombreC.getText().toString().equals("")){
                    Toast.makeText(FormCuentaActivity.this,"Ingresar Nombre",Toast.LENGTH_LONG).show();
                }else{
                    Cuenta cuenta = new Cuenta();
                    cuenta.Nombre = etNombreC.getText().toString();

                    service.create(cuenta).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            String dato = 1+"";
                            Intent intent = new Intent(FormCuentaActivity.this, ListCuentaActivity.class);
                            intent.putExtra("dato",dato.toString());
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }
}