package com.life.life.welcome;

import android.content.Context;
import android.os.Message;

import java.util.TimerTask;

public class MyTask extends TimerTask {
    private Context context = null;
    private MyHandler handler = null;

    public MyTask(Context context) {
        this.context = context;
        handler = new MyHandler(this.context);
    }

    @Override
    public void run() {
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }
}
