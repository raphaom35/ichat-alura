package com.alura.raphael.ichat_alura.module;

import android.app.Application;

import com.alura.raphael.ichat_alura.Services.ChatService;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by raphael on 09/03/2017.
 */
@Module
public class ChatModule  {


    private Application app;

    public ChatModule(Application app) {
        this.app = app;
    }
    @Provides
    public ChatService getChatService(){
        Retrofit retrofit =new Retrofit.Builder().baseUrl("http://10.50.50.140:8080/").addConverterFactory(GsonConverterFactory.create()).build();
        ChatService chatService =retrofit.create(ChatService.class);
        return chatService;
    }
    @Provides
    public Picasso getPicasso(){
        Picasso picasso =new Picasso.Builder(app).build();
        return picasso;
    }
    @Provides
    public EventBus getEventbus(){
        return EventBus.builder().build();
    }

}
