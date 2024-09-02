package com.prothes.banglasms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class PoemSms extends AppCompatActivity {
    private ListView listView;
    private String message[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        setContentView(R.layout.all_smsgrid_layout_design);

        listView = findViewById(R.id.listView);
        message = getResources().getStringArray(R.array.poem_sms);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(this,message);
        listView.setAdapter(customBaseAdapter);
    }

    // Show Menu Item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Set Click effect menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.privacy){

        }
        if (item.getItemId() == R.id.myPoem){
            Intent intent = new Intent(PoemSms.this,ProthesPoem.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.exit){
            alertDialogBox();
        }
        return super.onOptionsItemSelected(item);
    }

    // Alert Dialog Box Create
    public void alertDialogBox(){
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Warning !!!")
                .setMessage("Do you want to exit this app ?")
                .setIcon(getDrawable(R.drawable.alert_orange))
                .setCancelable(false)
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
    }


    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        CustomBaseAdapter.textToSpeech.stop();
        super.onBackPressed();
    }

}