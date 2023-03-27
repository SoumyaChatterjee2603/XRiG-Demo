package com.example.xrig;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class XRiG_Details extends AppCompatActivity {

    ImageView detailsImage;
    TextView detailsName, detailsPrice, detailsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xri_gdetails);

        detailsImage = findViewById(R.id.displayImage);
        detailsName = findViewById(R.id.displayName);
        detailsPrice = findViewById(R.id.displayPrice);
        detailsDescription = findViewById(R.id.displayDesc);

        Intent intent = getIntent();
        String name = intent.getStringExtra("detailsName");
        String price = intent.getStringExtra("detailsPrice");
        String image = intent.getStringExtra("detailsImgUrl");
        String desc = intent.getStringExtra("detailsDesc");

        detailsName.setText(name);
        detailsPrice.setText(price);
        detailsDescription.setText(desc);
        Picasso.get().load(image).into(detailsImage);
    }
}