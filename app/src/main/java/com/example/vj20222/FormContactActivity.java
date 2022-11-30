package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vj20222.database.AppDataBase;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FormContactActivity extends AppCompatActivity {
    Button btnGuardarC;
    EditText etNombreC, etTelefonoC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_contact);
        AppDataBase db = AppDataBase.getInstance(this);
        Retrofit retrofit = new RetrofitFactory(this).build();
        ContactService service = retrofit.create(ContactService.class);
        btnGuardarC = findViewById(R.id.btnGuardarC);
        etNombreC = findViewById(R.id.etNombreC);
        etTelefonoC = findViewById(R.id.etTelefonoC);
        btnGuardarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNombreC.getText().toString().equals("") || etTelefonoC.getText().toString().equals("")){
                    Toast.makeText(FormContactActivity.this,"Ingresar Campos",Toast.LENGTH_LONG);
                }else{
                    Contact contact = new Contact();
                    contact.name = etNombreC.getText().toString();
                    contact.telefono = etTelefonoC.getText().toString();
                    contact.avatar = "https://cloudflare-ipfs.com/ipfs/Qmd3W5DuhgHirLHGVixi6V76LhCkZUz6pnFt5AJBiyvHye/avatar/1068.jpg";
                    service.create(contact).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            db.contactDao().create(contact);
                            Intent intent = new Intent(FormContactActivity.this, ContactActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            db.contactDao().create(contact);
                            Intent intent = new Intent(FormContactActivity.this, ContactActivity.class);
                            startActivity(intent);

                        }
                    });
                }

            }
        });
    }
}
