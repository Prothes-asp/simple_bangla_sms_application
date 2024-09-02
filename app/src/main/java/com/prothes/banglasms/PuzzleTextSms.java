package com.prothes.banglasms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class PuzzleTextSms extends AppCompatActivity {
    private ListView listView;
    private String message[],puzzle_answer[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        setContentView(R.layout.all_smsgrid_layout_design);

        listView = findViewById(R.id.listView);
        message = getResources().getStringArray(R.array.puzzle_sms);
        puzzle_answer = getResources().getStringArray(R.array.puzzle_sms_answer);

        PuzzleBaseAdapter puzzleBaseAdapter = new PuzzleBaseAdapter(this,message,puzzle_answer);
        listView.setAdapter(puzzleBaseAdapter);

    }



    /** @noinspection deprecation*/
    @Override
    public void onBackPressed() {
        PuzzleBaseAdapter.textToSpeech.stop();
        super.onBackPressed();
    }
}