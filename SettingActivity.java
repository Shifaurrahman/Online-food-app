package com.example.myfinalproject.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myfinalproject.FirstPage;
import com.example.myfinalproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class SettingActivity extends AppCompatActivity {
    Button logout,btnHelp;
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        EditText editText=findViewById(R.id.edit_text);
        Button buttonLogOut=findViewById(R.id.button);
        Button btnHelp=findViewById(R.id.btnHelp);
        ImageView imageView=findViewById(R.id.qr_code);

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Share this content!");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });

        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MultiFormatWriter multiFormatWriter=new MultiFormatWriter();

                try {
                    BitMatrix bitMatrix=multiFormatWriter.encode(editText.getText().toString(), BarcodeFormat.QR_CODE,300,300);

                    BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
                    Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);

                    imageView.setImageBitmap(bitmap);




                }catch (WriterException e){
                    throw new RuntimeException(e);
                }
            }
        });

        logout=findViewById(R.id.btnLogOut);
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getApplicationContext(), FirstPage.class);
                startActivity(intent);
                finish();
            }
        });




    }
}