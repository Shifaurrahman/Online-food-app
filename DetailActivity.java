package com.example.myfinalproject.Activity;

import static java.util.ResourceBundle.getBundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myfinalproject.Domain.FoodDomain;
import com.example.myfinalproject.Helper.ManagementCart;
import com.example.myfinalproject.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView plusBtn,minusBtn,titleTxt,feeTxt,descriptionTxt,numberOrderTxt;
    private ImageView picFood;
    private FoodDomain object;
    private int numberOrder=1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart=new ManagementCart(DetailActivity.this);
                initView();
                getBundle();
    }

    private void getBundle() {
        object=(FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId=this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTittle());
        feeTxt.setText("Rs."+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(""+numberOrder);
        addToCartBtn.setText("Add to cat-Rs"+Math.round(numberOrder*object.getPrice()));

        plusBtn.setOnClickListener(view -> {
            numberOrder=numberOrder+1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cat-Rs"+Math.round(numberOrder*object.getPrice()));

        });

        minusBtn.setOnClickListener(view -> {
            numberOrder=numberOrder-1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cat-Rs"+Math.round(numberOrder*object.getPrice()));

        });

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberinCart(numberOrder);
                managementCart.insertFood(object);
            }
        });


    }

    private void initView() {
        addToCartBtn=findViewById(R.id.addToCartBtn);
        titleTxt=findViewById(R.id.titleTxt);
        feeTxt=findViewById(R.id.priceTxt);
        descriptionTxt=findViewById(R.id.descriptionTxt);
        numberOrderTxt=findViewById(R.id.numberItemTxt);
        plusBtn=findViewById(R.id.plusCartBtn);
        minusBtn =findViewById(R.id.minusCartBtn);
        picFood=findViewById(R.id.foodPic);


    }



}