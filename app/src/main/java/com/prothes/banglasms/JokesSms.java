package com.prothes.banglasms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class JokesSms extends AppCompatActivity {
    private ListView listView;
    private String message[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        setContentView(R.layout.all_smsgrid_layout_design);

        listView = findViewById(R.id.listView);
        message = getResources().getStringArray(R.array.jokes_sms);
        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(this,message);
        listView.setAdapter(customBaseAdapter);
    }

    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        CustomBaseAdapter.textToSpeech.stop();
        super.onBackPressed();
    }
}