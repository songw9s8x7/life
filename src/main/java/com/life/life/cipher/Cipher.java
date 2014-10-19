package com.life.life.cipher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.life.life.we.We;
import com.life.life.welcome.R;

public class Cipher extends Activity implements View.OnClickListener {
    private TextView cipher_text;
    private Button cipher_first_button, cipher_second_button, cipher_third_button, cipher_fourth_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);
        cipher_text = (TextView) super.findViewById(R.id.cipher_text);
        cipher_first_button = (Button) super.findViewById(R.id.cipher_first_button);
        cipher_second_button = (Button) super.findViewById(R.id.cipher_second_button);
        cipher_third_button = (Button) super.findViewById(R.id.cipher_third_button);
        cipher_fourth_button = (Button) super.findViewById(R.id.cipher_fourth_button);
        cipher_text.setText("");
        cipher_text.setOnClickListener(this);
        cipher_first_button.setOnClickListener(this);
        cipher_second_button.setOnClickListener(this);
        cipher_third_button.setOnClickListener(this);
        cipher_fourth_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cipher_text:
                cipher_text.setText("");
                break;
            case R.id.cipher_first_button:
                cipher_text.setText(cipher_text.getText().toString().trim() + "1");
                StartIntent();
                break;
            case R.id.cipher_second_button:
                cipher_text.setText(cipher_text.getText().toString().trim() + "2");
                StartIntent();
                break;
            case R.id.cipher_third_button:
                cipher_text.setText(cipher_text.getText().toString().trim() + "3");
                StartIntent();
                break;
            case R.id.cipher_fourth_button:
                cipher_text.setText(cipher_text.getText().toString().trim() + "4");
                StartIntent();
                break;
        }
    }

    public void StartIntent() {
        if (cipher_text.getText().toString().trim().equals("4124123")) {
            startActivity(new Intent(Cipher.this, We.class));
            overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            Cipher.this.finish();
        }
    }

    @Override
    public boolean onKeyDown(int key, KeyEvent event) {
        if (key == event.KEYCODE_BACK) {
            Cipher.this.finish();
        }
        return false;
    }
}