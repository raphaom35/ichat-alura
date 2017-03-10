package com.alura.raphael.ichat_alura.component;

import com.alura.raphael.ichat_alura.activity.MainActivity;
import com.alura.raphael.ichat_alura.adapter.MensagemAdapter;
import com.alura.raphael.ichat_alura.module.ChatModule;

import dagger.Component;

/**
 * Created by raphael on 09/03/2017.
 */
@Component(modules = ChatModule.class)
public interface ChatComponet {
    void inject(MainActivity activity);
    void inject(MensagemAdapter adapter);
}
