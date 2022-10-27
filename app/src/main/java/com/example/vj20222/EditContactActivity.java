package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditContactActivity extends AppCompatActivity {
    EditText etNameCe, etTelefonoCe;
    ImageView ivImageCe;
    Button btnEditarCe;
    public String img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        etNameCe = findViewById(R.id.etNameCe);
        etTelefonoCe = findViewById(R.id.etTelefonoCe);
        ivImageCe = findViewById(R.id.ivImageCe);
        btnEditarCe = findViewById(R.id.btnEditarCe);
        int id = Integer.parseInt(getIntent().getStringExtra("dato"));

        Retrofit retrofit = new RetrofitFactory(this).build();
        ContactService service = retrofit.create(ContactService.class);
        service.get(id).enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact data = response.body();
                etNameCe.setText(data.name);
                etTelefonoCe.setText(data.telefono);
                Picasso.get().load(data.avatar).into(ivImageCe);
                img = data.avatar;

            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {
                Toast.makeText(EditContactActivity.this,"Error",Toast.LENGTH_LONG);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        btnEditarCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNameCe.getText().toString().equals("") || etTelefonoCe.getText().toString().equals("")){
                    Toast.makeText(EditContactActivity.this,"Campos Vacios",Toast.LENGTH_LONG);
                }else{
                    Contact data = new Contact();
                    data.name = etNameCe.getText().toString();
                    data.telefono = etTelefonoCe.getText().toString();
                    data.avatar = img;

                    service.update(data,id).enqueue(new Callback<Contact>() {
                        @Override
                        public void onResponse(Call<Contact> call, Response<Contact> response) {
                            Intent intent = new Intent(EditContactActivity.this, ContactActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Contact> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}

