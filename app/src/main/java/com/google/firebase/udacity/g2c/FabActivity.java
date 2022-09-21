package com.google.firebase.udacity.g2c;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FabActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);
        editText=findViewById(R.id.txt);
        btn=findViewById(R.id.btn);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertData();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(FabActivity.this,MainActivity.class);
        startActivity(i);
    }

    private void InsertData() {
        String s1=editText.getText().toString();
        String id=databaseReference.push().getKey();

        User user=new User(s1);
        databaseReference.child("users").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                    Toast.makeText(FabActivity.this,"Task Successful",Toast.LENGTH_LONG).show();
            }
        });
    }
}