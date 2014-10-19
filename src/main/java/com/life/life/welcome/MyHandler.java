package com.life.life.welcome;

import android.content.Context;
import android.os.Handler;

public class MyHandler extends Handler {
    private Welcome context;
    public MyHandler(Context context) {
        this.context = (Welcome) context;
    }

    @Override
    public void handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case 1:
                context.MyFinish();
                break;
        }
    }
}
