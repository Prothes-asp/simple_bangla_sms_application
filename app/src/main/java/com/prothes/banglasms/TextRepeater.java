package com.prothes.banglasms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class TextRepeater extends AppCompatActivity {
    private TextInputEditText repeatValue,messageBox;
    private AutoCompleteTextView emojiInput;
    private CheckBox addNewLine,addPreEmoji,addPostEmoji;
    private AppCompatButton submitBtn,copyBtn,shareBtn,resetBtn;
    private TextView display;
    private String emojiList[];
    private ArrayAdapter arrayAdapter;
    private ImageView imageView;
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setNavigationBarColor(getColor(R.color.sky));
        this.getWindow().setStatusBarColor(getColor(R.color.sky));
        setContentView(R.layout.text_repeater);

        repeatValue = findViewById(R.id.repeatValue);
        messageBox = findViewById(R.id.messageBox);
        emojiInput = findViewById(R.id.emojiInput);
        addNewLine = findViewById(R.id.addNewLine);
        addPreEmoji = findViewById(R.id.addPreEmoji);
        addPostEmoji = findViewById(R.id.addPostEmoji);
        submitBtn = findViewById(R.id.submitBtn);
        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);
        imageView = findViewById(R.id.imageView);
        resetBtn = findViewById(R.id.resetBtn);
        display = findViewById(R.id.display);

        emojiList = getResources().getStringArray(R.array.emoji);
        arrayAdapter = new ArrayAdapter<>(TextRepeater.this,R.layout.emoji_list_layout,R.id.emojiItems,emojiList);
        emojiInput.setAdapter(arrayAdapter);




        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getValueNum = repeatValue.getText().toString();
                String getEmoji = emojiInput.getText().toString();
                String message = messageBox.getText().toString();

                if (getValueNum.length()>0 && message.length()>0 && addNewLine.isChecked() && addPreEmoji.isChecked() && addPostEmoji.isChecked()){
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getEmoji+" "+message+" "+getEmoji+"\n");
                    }
                    display.setText(stringBuilder);
                } else if (getValueNum.length()>0 && message.length()>0 && addNewLine.isChecked() && addPreEmoji.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getEmoji+" "+message+"\n");
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0 && addNewLine.isChecked() && addPostEmoji.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(message+" "+getEmoji+"\n");
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0 && addPreEmoji.isChecked() && addPostEmoji.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getEmoji+" "+message+" "+getEmoji);
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0 && addNewLine.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(message+"\n");
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0 && addPreEmoji.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(getEmoji+" "+message);
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0 && addPostEmoji.isChecked()) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(message+" "+getEmoji);
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0 && message.length()>0) {
                    int num = Integer.parseInt(getValueNum);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i=1; i<=num; i++){
                        stringBuilder.append(message);
                    }
                    display.setText(stringBuilder);
                }else if (getValueNum.length()>0) {
                    repeatValue.setError(null);
                    if (message.length()>0){
                        messageBox.setError(null);
                    }else{
                        messageBox.setError("Empty");
                        display.setText(null);
                    }
                }else if (message.length()>0) {
                    messageBox.setError(null);
                    if (getValueNum.length()>0){
                        repeatValue.setError(null);
                    }else{
                        repeatValue.setError("Empty");
                        display.setText(null);
                    }
                } else{
                    repeatValue.setError("Empty");
                    messageBox.setError("Empty");
                    display.setText(null);
                }

            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTxt = display.getText().toString();
                if (getTxt.length()>0){
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clipData = ClipData.newPlainText("Text",getTxt);
                    clipboardManager.setPrimaryClip(clipData);
                    Toast.makeText(TextRepeater.this, "Copied", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(TextRepeater.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTxt = display.getText().toString();
                if (getTxt.length()>0){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,getTxt);
                    intent.setType("text/plain");
                    Intent shareTxt = Intent.createChooser(intent,null);
                    startActivity(shareTxt);
                }else{
                    Toast.makeText(TextRepeater.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                String getTxt = display.getText().toString();
                if (i != TextToSpeech.ERROR && getTxt.length()>0){
                    int result = textToSpeech.setLanguage(new Locale("bn_IN"));
                }
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getTxt = display.getText().toString();
                if (getTxt.length()>0){
                    textToSpeech.speak(getTxt,TextToSpeech.QUEUE_FLUSH,null);
                }else{
                    Toast.makeText(TextRepeater.this, "Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                display.setText(null);
                repeatValue.setText(null);
                repeatValue.setError(null);
                messageBox.setText(null);
                messageBox.setError(null);
                emojiInput.setText(null);
                addNewLine.setChecked(false);
                addPreEmoji.setChecked(false);
                addPostEmoji.setChecked(false);
                textToSpeech.stop();
                Toast.makeText(TextRepeater.this, "Reset All", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        textToSpeech.stop();
    }
}