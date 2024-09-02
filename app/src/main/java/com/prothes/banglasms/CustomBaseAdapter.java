package com.prothes.banglasms;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import java.util.Locale;

public class CustomBaseAdapter extends BaseAdapter {
    private Context context;
    private String message[];
    private LayoutInflater inflater;
    public static TextToSpeech textToSpeech;
    private Typeface typeface;
    public CustomBaseAdapter(LifeRealQuote lifeRealQuote, String[] message) {
        this.context = lifeRealQuote;
        this.message = message;
    }

    public CustomBaseAdapter(LoveSms loveSms, String[] message) {
        this.context = loveSms;
        this.message = message;
    }

    public CustomBaseAdapter(WishSms wishSms, String[] message) {
        this.context = wishSms;
        this.message = message;
    }

    public CustomBaseAdapter(JokesSms jokesSms, String[] message) {
        this.context = jokesSms;
        this.message = message;
    }

    public CustomBaseAdapter(PoemSms poemSms, String[] message) {
        this.context = poemSms;
        this.message = message;
    }

    public CustomBaseAdapter(CaptionSms captionSms, String[] message) {
        this.context = captionSms;
        this.message = message;
    }

    public CustomBaseAdapter(ProthesPoem prothesPoem, String[] message) {
        this.context = prothesPoem;
        this.message = message;
    }

    @Override
    public int getCount() {
        return message.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_items_layout,viewGroup,false);
        }
        TextView textView = view.findViewById(R.id.smsTxt);
        AppCompatButton copyBtn = view.findViewById(R.id.copyBtn);
        AppCompatButton shareBtn = view.findViewById(R.id.shareBtn);
        ImageView imageView = view.findViewById(R.id.imageView);
        textView.setText(message[i]);

        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/bangla.ttf");
        textView.setTypeface(typeface);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getSms = textView.getText().toString();
                ClipboardManager  clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Text",getSms);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getSms = textView.getText().toString();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,getSms);
                intent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(intent,null);
                context.startActivity(shareIntent);
            }
        });

        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(new Locale("bn_IN"));
                }
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = textView.getText().toString();
                textToSpeech.speak(txt,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        return view;
    }

}
