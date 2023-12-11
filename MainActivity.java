package com.example.finalsupport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    private final List<MyItems> myItemsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView=findViewById(R.id.recyclerview);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                myItemsList.clear();

                for (DataSnapshot users :snapshot.child("users").getChildren()){

                    if (users.hasChild("foodname") && users.hasChild("price") && users.hasChild("duration")){
                        final String getFoodname=users.child("foodname").getValue(String.class);
                        // final String getMobile=users.child("mobile").getValue(String.class);
                        Long priceValue = users.child("price").getValue(Long.class);
                        String getPrice = String.valueOf(priceValue);

                        final String getDuration=users.child("duration").getValue(String.class);

                        // final String getMobile = String.valueOf(users.child("mobile").getValue(String.class));
                        MyItems myItems=new MyItems(getFoodname,getPrice,getDuration);

                        myItemsList.add(myItems);
                    }

                }

                recyclerView.setAdapter(new MyAdapter(myItemsList,MainActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}