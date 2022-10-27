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

import com.example.vj20222.ContactActivity;
import com.example.vj20222.EditContactActivity;
import com.example.vj20222.FormContactActivity;
import com.example.vj20222.LoginActivity;
import com.example.vj20222.R;
import com.example.vj20222.entities.Contact;
import com.example.vj20222.factories.RetrofitFactory;
import com.example.vj20222.services.ContactService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ContactAdapter extends RecyclerView.Adapter{

    List<Contact> data;
    private SharedPreferences sharedPreferences;
    public ContactAdapter(List<Contact> data){
        this.data = data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_contact, parent, false);
        return new ContactAdapter.ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView tvNameC = holder.itemView.findViewById(R.id.tvNameC);
        tvNameC.setText(data.get(position).name);
        TextView tvTelefonoC = holder.itemView.findViewById(R.id.tvTelefonoC);
        tvTelefonoC.setText(data.get(position).telefono);
        ImageView ivImgC = holder.itemView.findViewById(R.id.ivImgC);
        //ivContac.setImageResource(R.drawable.avatar2);
        Picasso.get().load(data.get(position).avatar).into(ivImgC);
        Button btnEliminarC = holder.itemView.findViewById(R.id.btnEliminarC);
        int id = data.get(position).id;
        String dato = id+"";
        btnEliminarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new RetrofitFactory(holder.itemView.getContext()).build();
                ContactService service = retrofit.create(ContactService.class);
                service.delete(id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Intent intent = new Intent(holder.itemView.getContext(), LoginActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Intent intent = new Intent(holder.itemView.getContext(), LoginActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
            }
        });
        Button btnEditarC = holder.itemView.findViewById(R.id.btnEditarC);
        btnEditarC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), EditContactActivity.class);
                intent.putExtra("dato",dato.toString());
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class ContactViewHolder extends RecyclerView.ViewHolder{

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
