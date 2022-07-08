package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsAnimationController;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends Activity {
    private TextView textView;
    private Button[] buttons;
    private Button EqualButton;
    private Button ClearButton;
    private Button[] CalButton;
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        textView = (TextView) findViewById(R.id.result);
        buttons = new Button[10];
        for (i = 0; i < 10; i++) {
            int resID = getResources().getIdentifier("button" + "_" + i, "id", getPackageName());
            buttons[i] = (Button) findViewById(resID);
            buttons[i].setText(Integer.toString(i ));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button bSelf = (Button) findViewById(view.getId());
                    String showString = String.valueOf(textView.getText());
                    textView.setText(showString + bSelf.getText());
                }
            });
        }
        CalButton = new Button[4];
        CalButton[0] = (Button) findViewById(R.id.button_plus);
        CalButton[1] = (Button) findViewById(R.id.button_sub);
        CalButton[2] = (Button) findViewById(R.id.button_mul);
        CalButton[3] = (Button) findViewById(R.id.button_div);
        for (int i = 0; i < 4; i++) {
            CalButton[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button bSelf = (Button) findViewById(view.getId());
                    String showString = String.valueOf(textView.getText());
                    textView.setText(showString + bSelf.getText());
                }
            });
        }

        EqualButton = (Button) findViewById(R.id.button_equal);
        EqualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = new String("这是结果");
                String showString = String.valueOf(textView.getText());
                textView.setText(result);
            }
        });
        ClearButton = (Button) findViewById(R.id.button_clear);
        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result = new String("0");
                textView.setText(result);
            }
        });
    }
}