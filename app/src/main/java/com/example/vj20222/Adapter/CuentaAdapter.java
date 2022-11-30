package com.example.vj20222.Adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vj20222.DetalleCuentaActivity;
import com.example.vj20222.EditContactActivity;
import com.example.vj20222.LoginActivity;
import com.example.vj20222.R;
import com.example.vj20222.database.AppDataBase;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.entities.Cuenta;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CuentaAdapter extends RecyclerView.Adapter{
    List<Cuenta> data;
    private SharedPreferences sharedPreferences;
    public CuentaAdapter(List<Cuenta> data){
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_cuenta, parent, false);
        return new CuentaAdapter.CuentaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvNameC = holder.itemView.findViewById(R.id.tvNameC);
        tvNameC.setText(data.get(position).Nombre);
        Button btnEliminarC = holder.itemView.findViewById(R.id.btnEliminarC);
        int id = data.get(position).id;
        String dato = id+"";
        Button btnEditarC = holder.itemView.findViewById(R.id.btnEditarC);
        btnEditarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalleCuentaActivity.class);
                intent.putExtra("dato",dato.toString());
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class CuentaViewHolder extends RecyclerView.ViewHolder{

        public CuentaViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
