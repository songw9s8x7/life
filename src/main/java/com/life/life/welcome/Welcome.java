package com.life.life.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.life.life.cipher.Cipher;

import java.util.Timer;

public class Welcome extends Activity {
    private TextView welcome_first_line, welcome_second_line, welcome_third_line;
    private Timer t = new Timer();
    private MyTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcome_first_line = (TextView) super.findViewById(R.id.welcome_first_line);
        welcome_second_line = (TextView) super.findViewById(R.id.welcome_second_line);
        welcome_third_line = (TextView) super.findViewById(R.id.welcome_third_line);
        String welcome_first_line_text = "<font color=\"#EE30A7\">莫</font><font color=\"#EE5C42\">等</font><font color=\"#EE9A00\">闲</font>";
        String welcome_second_line_text = "<font color=\"#EE3A8C\">白了少年头</font>";
        String welcome_third_line_text = "<font color=\"#8B0000\">空悲切</font>";
        welcome_first_line.setText(Html.fromHtml(welcome_first_line_text));
        welcome_second_line.setText(Html.fromHtml(welcome_second_line_text));
        welcome_third_line.setText(Html.fromHtml(welcome_third_line_text));
        task = new MyTask(Welcome.this);
        t.schedule(task, 3000, 100000);
    }

    public void MyFinish() {
        t.cancel();
//        Animation animation = AnimationUtils.loadAnimation(Welcome.this, R.anim.zoom_out);
//        welcome.startAnimation(animation);
        startActivity(new Intent(Welcome.this, Cipher.class));
        overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
        Welcome.this.finish();
    }
}
