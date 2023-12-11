package com.example.myfinalproject.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinalproject.Helper.ChangeNumberItemListner;
import com.example.myfinalproject.Helper.ManagementCart;
import com.example.myfinalproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity {



    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt,taxTxt,deliveryTxt,totalTxt,emptyTxt,facultyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn,arrowDetail;
    private Button orderBtn,orderBtn2;
    private EditText editTextName,editTextRegNumber,editTextItem;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference =firebaseDatabase.getReference();
        
        managementCart=new ManagementCart(this);
        
        initView();
        initList();
        calculateCart();
        setVariable();
    }

    private void setVariable() {
        backBtn.setOnClickListener(view -> finish());
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter=new CartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemListner() {
            @Override
            public void changed() {
                calculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);

        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    private void calculateCart(){
        double percentTax=0.02;
        double delivery=50;
        tax=Math.round((managementCart.getTotalFee()*percentTax*100.0))/100.0;

        double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100.0)/100;
        double itemTotal=Math.round(managementCart.getTotalFee()*100.0)/100.0;

        totalFeeTxt.setText("Rs"+itemTotal);
        taxTxt.setText("Rs"+tax);
        deliveryTxt.setText("Rs"+delivery);
        totalTxt.setText("Rs"+total);



    }



    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view4);
        scrollView=findViewById(R.id.scrollView);
        backBtn=findViewById(R.id.backBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
        arrowDetail=findViewById(R.id.arrowDetail);
        facultyTxt=findViewById(R.id.facultyTxt);
        orderBtn=findViewById(R.id.orderBtn);
        editTextName=findViewById(R.id.editTextName);
        editTextRegNumber=findViewById(R.id.editTextRegNumber);
        editTextItem=findViewById(R.id.editTextItem);
        orderBtn2=findViewById(R.id.orderBtn2);





        orderBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                String getName= editTextName.getText().toString();
                String getFaculty=facultyTxt.getText().toString();
                String getRegNumber=editTextRegNumber.getText().toString();
                String getItem=editTextItem.getText().toString();


                HashMap<String,Object> hashMap=new HashMap<>();
                hashMap.put("Name",getName);
                hashMap.put("Faculty",getFaculty);
                hashMap.put("Registration Number",getRegNumber);
                hashMap.put("Item",getItem);
                


                databaseReference.child("users")
                        .child(getName)
                        .setValue(hashMap)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(CartActivity.this,"Data added successfully",Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CartActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });


            }
        });

        orderBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(CartActivity.this,SettingActivity.class);
                startActivity(i);
                finish();
            }
        });


        

    }


}


