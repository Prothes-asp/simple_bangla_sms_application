package com.prothes.banglasms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CardView gridItem1,gridItem2,gridItem3,gridItem4,gridItem5,gridItem6,gridItem7,gridItem8;
    private Typeface typeface;
    private TextView title_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        setContentView(R.layout.activity_main);

        gridItem1 = findViewById(R.id.gridItem1);
        gridItem2 = findViewById(R.id.gridItem2);
        gridItem3 = findViewById(R.id.gridItem3);
        gridItem4 = findViewById(R.id.gridItem4);
        gridItem5 = findViewById(R.id.gridItem5);
        gridItem6 = findViewById(R.id.gridItem6);
        gridItem7 = findViewById(R.id.gridItem7);
        gridItem8 = findViewById(R.id.gridItem8);

        title_view = findViewById(R.id.title_view);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/english.ttf");
        title_view.setTypeface(typeface);


        gridItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PuzzleTextSms.class);
                startActivity(intent);
            }
        });
        gridItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LifeRealQuote.class);
                startActivity(intent);
            }
        });
        gridItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoveSms.class);
                startActivity(intent);
            }
        });
        gridItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WishSms.class);
                startActivity(intent);
            }
        });
        gridItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PoemSms.class);
                startActivity(intent);
            }
        });
        gridItem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, JokesSms.class);
                startActivity(intent);
            }
        });
        gridItem7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptionSms.class);
                startActivity(intent);
            }
        });
        gridItem8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextRepeater.class);
                startActivity(intent);
            }
        });


    }


    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        if (isTaskRoot()){
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Warning !!!")
                    .setMessage("Do you want exit this apps ?")
                    .setIcon(getDrawable(R.drawable.alert_orange))
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
            alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getColor(R.color.sky));
            alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getColor(R.color.sky));
        }else{
            super.onBackPressed();
        }
    }
}