package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.vj20222.Adapter.ContactAdapter;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;

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
        Retrofit retrofit = new RetrofitFactory(this).build();
        ContactService service = retrofit.create(ContactService.class);
        service.all().enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> data = response.body();
                rvContact = findViewById(R.id.rvContact);
                rvContact.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rvContact.setAdapter(new ContactAdapter((data)));
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

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