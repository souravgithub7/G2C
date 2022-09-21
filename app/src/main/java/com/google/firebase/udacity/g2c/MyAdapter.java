package com.google.firebase.udacity.g2c;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    DatabaseReference databaseReference;
    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return  new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        User user= list.get(position);
        holder.itemname.setText(user.getItem());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class myViewHolder extends RecyclerView.ViewHolder {
        TextView itemname;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            itemname=itemView.findViewById(R.id.textView);
        }
    }
}
