package com.alura.raphael.ichat_alura.callback;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.alura.raphael.ichat_alura.FailureEvent;
import com.alura.raphael.ichat_alura.activity.MainActivity;
import com.alura.raphael.ichat_alura.event.MensagemEvent;
import com.alura.raphael.ichat_alura.modelo.Mensagens;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by raphael on 09/03/2017.
 */

public class OuvirMenssagensCallback implements Callback<Mensagens> {

    private  EventBus bus;
    private Context context;
    public OuvirMenssagensCallback(EventBus bus, Context context) {
        this.context = context;
        this.bus =bus;
    }
    @Override
    public void onResponse(Call<Mensagens> call, Response<Mensagens> response) {
        if(response.isSuccessful()) {
            Mensagens mensagens = response.body();

            bus.getDefault().post(new MensagemEvent(mensagens));
        }
    }

    @Override
    public void onFailure(Call<Mensagens> call, Throwable t) {
        bus.post(new FailureEvent());
    }
}
