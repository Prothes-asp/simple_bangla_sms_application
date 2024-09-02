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

public class PuzzleBaseAdapter extends BaseAdapter {
    private Context context;
    private String message[],puzzle_answer[];
    private LayoutInflater inflater;
    public static TextToSpeech textToSpeech;
    private Typeface typeface;
    public PuzzleBaseAdapter(PuzzleTextSms puzzleTxtSms, String[] message, String[] puzzle_answer) {
        this.context = puzzleTxtSms;
        this.message = message;
        this.puzzle_answer = puzzle_answer;
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
            view = inflater.inflate(R.layout.puzzle_layout_items,viewGroup,false);
        }
        TextView textView = view.findViewById(R.id.smsTxt);
        AppCompatButton copyBtn = view.findViewById(R.id.copyBtn);
        AppCompatButton shareBtn = view.findViewById(R.id.shareBtn);
        ImageView speaker = view.findViewById(R.id.speaker);
        ImageView answer = view.findViewById(R.id.answer);
        textView.setText(message[i]);

        typeface = Typeface.createFromAsset(context.getAssets(), "fonts/bangla.ttf");
        textView.setTypeface(typeface);

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getSms = textView.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
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

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt = textView.getText().toString();
                textToSpeech.speak(txt,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText() == message[i]){
                    textView.setText(puzzle_answer[i]);
                }else{
                    textView.setText(message[i]);
                }

            }
        });

        return view;
    }
}
