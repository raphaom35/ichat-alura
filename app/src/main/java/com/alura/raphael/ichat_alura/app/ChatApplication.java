package com.alura.raphael.ichat_alura.app;

import android.app.Application;

import com.alura.raphael.ichat_alura.component.ChatComponet;
import com.alura.raphael.ichat_alura.component.DaggerChatComponet;
import com.alura.raphael.ichat_alura.module.ChatModule;

/**
 * Created by raphael on 09/03/2017.
 */

public class ChatApplication extends Application {

    private ChatComponet component;

    @Override
    public void onCreate() {
        component = DaggerChatComponet.builder().chatModule(new ChatModule(this)).build();
    }

    public ChatComponet getComponent() {
        return component;
    }
}
