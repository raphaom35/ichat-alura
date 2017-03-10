package com.alura.raphael.ichat_alura.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PersistableBundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.alura.raphael.ichat_alura.FailureEvent;
import com.alura.raphael.ichat_alura.app.ChatApplication;
import com.alura.raphael.ichat_alura.R;
import com.alura.raphael.ichat_alura.Services.ChatService;
import com.alura.raphael.ichat_alura.adapter.MensagemAdapter;

import com.alura.raphael.ichat_alura.callback.EnviarMenssagensCallback;
import com.alura.raphael.ichat_alura.callback.OuvirMenssagensCallback;

import com.alura.raphael.ichat_alura.component.ChatComponet;
import com.alura.raphael.ichat_alura.event.MensagemEvent;
import com.alura.raphael.ichat_alura.modelo.Mensagens;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private int idDoCliente =1;
    @BindView(R.id.edt_text)
      EditText editText;
    @BindView(R.id.btn_enviar)
     Button button;
    @BindView(R.id.lv_menssages)
     ListView listaDeMenssagens;
    @BindView(R.id.iv_avatar_usuario)
    ImageView avatar;
    private List<Mensagens> mensagens;
    @Inject
    ChatService chatService;
    @Inject
    EventBus eventBus;

    private ChatComponet componet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        Picasso.with(this).load("http://api.adorable.io/avatars/285/"+idDoCliente+".png").into(avatar);
        ChatApplication app = (ChatApplication) getApplication();
        componet =app.getComponent();
        componet.inject(this);

        if(savedInstanceState != null){
            mensagens= (List<Mensagens>) savedInstanceState.getSerializable("menssagens");
        }else {
            mensagens = new ArrayList<>();
        }



        MensagemAdapter adapter = new MensagemAdapter(idDoCliente,mensagens,this);
        listaDeMenssagens.setAdapter(adapter);


       Call<Mensagens> call = chatService.ouvirMenssagens();
        call.enqueue(new OuvirMenssagensCallback(eventBus,this));

        eventBus.getDefault().register(this);

    }
    @OnClick(R.id.btn_enviar)
    public void enviarMenssagens(){
        chatService.enviar(new Mensagens(idDoCliente,editText.getText().toString())).enqueue(new EnviarMenssagensCallback());
        editText.getText().clear();
        InputMethodManager inputMethodManager  = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(),0);
    }
    @Subscribe
    public void colocaNalista(MensagemEvent mensagemEvent){
        mensagens.add(mensagemEvent.mensagens);
        MensagemAdapter adapter =new MensagemAdapter(idDoCliente,mensagens,this);
        listaDeMenssagens.setAdapter(adapter);
    }
    @Subscribe
    public void ouvirMenssagens(MensagemEvent mensagemEvent){
        Call<Mensagens> call = chatService.ouvirMenssagens();
        call.enqueue(new OuvirMenssagensCallback(eventBus,this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void lidarCom(FailureEvent event) {
        ouvirMenssagens(null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("menssagens",(ArrayList<Mensagens>)mensagens);

    }
}
