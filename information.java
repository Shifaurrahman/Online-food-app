package com.example.finalsupport;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class information extends AppCompatActivity {

    private TextView plusBtn,minusBtn,titleTxt,feeTxt,numberOrderTxt,itemTotalTxt;

    private int numberOrder=1,itemTotal=0;
    private Button addToCartBtn;

    private MyItems object;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        titleTxt = findViewById(R.id.foo);
        feeTxt = findViewById(R.id.fee);

        addToCartBtn = findViewById(R.id.addToCartBtn);


        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartBtn);
        minusBtn = findViewById(R.id.minusCartBtn);
        itemTotalTxt = findViewById(R.id.itemTotalTxt);



        Intent intent = getIntent();
        String food = intent.getStringExtra("FOO");
        String fee = intent.getStringExtra("PRICE");


        titleTxt.setText(food);
        feeTxt.setText("Rs" + fee);
        itemTotalTxt.setText("Rs "+fee);


        object = (MyItems) getIntent().getSerializableExtra("object");

        numberOrderTxt.setText("" + numberOrder);
        plusBtn.setOnClickListener(view -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText("" + numberOrder);
            addToCartBtn.setText("Add to cat-Rs"+Math.round(numberOrder*object.getPrice()));

        });

        minusBtn.setOnClickListener(view -> {
            numberOrder = numberOrder - 1;

            if (numberOrder < 0) {
                numberOrder = 0;
            } else {
                numberOrderTxt.setText("" + numberOrder);
            }

        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), cart.class);
                startActivity(intent1);
                addToCart();
            }
        });


    }
    private void addToCart(){
        Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show();
    }
}