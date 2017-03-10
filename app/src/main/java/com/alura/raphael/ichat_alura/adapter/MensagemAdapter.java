package com.alura.raphael.ichat_alura.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alura.raphael.ichat_alura.R;
import com.alura.raphael.ichat_alura.modelo.Mensagens;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raphael on 08/03/2017.
 */

public class MensagemAdapter extends BaseAdapter {
    private List<Mensagens> mensagens;
    private  Activity activity;
    private int idDoCliente;
    @BindView(R.id.tv_texto)
    TextView texto;
    @BindView(R.id.iv_avatar_menssagens)
    ImageView imageView;
    @Inject
    Picasso picasso;

    public MensagemAdapter(int idDoCliente,List<Mensagens> mensagens, Activity activity){
        this.mensagens = mensagens;
        this.activity =activity;
        this.idDoCliente=idDoCliente;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Mensagens getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View linha = activity.getLayoutInflater().inflate(R.layout.mensagem,parent,false);
        ButterKnife.bind(linha);
        Mensagens mensagens =getItem(position);
        int idDaMenssagen =mensagens.getId();

        TextView texto = (TextView) linha.findViewById(R.id.tv_texto);
        ImageView imgem = (ImageView) linha.findViewById(R.id.iv_avatar_menssagens);
        picasso.with(activity).load("http://api.adorable.io/avatars/285/"+idDaMenssagen+".png").into(imgem);

        if(idDoCliente != mensagens.getId()){
            linha.setBackgroundColor(Color.CYAN);
        }


        texto.setText(mensagens.getTexto());
        return linha;
    }
}
