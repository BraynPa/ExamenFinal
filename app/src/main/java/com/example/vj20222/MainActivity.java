package com.example.vj20222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vj20222.entities.Users;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.UsersService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvSaludo = findViewById(R.id.tvSaludo);
        EditText etSaludo = findViewById(R.id.etSaludo);

//        Log.i("MAIN_APP", sharedPreferences.getString("AUTHORIZATION", null));

        Retrofit retrofit = new RetrofitFactory(this).build();
        UsersService service = retrofit.create(UsersService.class);

        service.all().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.i("MAIN_APP", new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Log.i("MAIN_APP", "FAIlED");
                Log.e("MAIN_APP", t.toString());
            }
        });




        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };


//        Retrofit retrofit = RetrofitFactory.build("abc");





        Button btnAlert = findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String text = etSaludo.getText().toString();
//                tvSaludo.setText(text);
//                Log.d("MAIN_APP", "Hola Mundo desde implementación en variable");

                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                intent.putExtra("NOMBRE", "Luis");
                intent.putExtra("APELLIDO", "Mendoza");
                intent.putExtra("SALUDO", etSaludo.getText().toString());

                startActivity(intent);

            }
        });
    }


}