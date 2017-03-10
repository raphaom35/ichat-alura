package com.alura.raphael.ichat_alura.event;

import com.alura.raphael.ichat_alura.modelo.Mensagens;

/**
 * Created by raphael on 10/03/2017.
 */

public class MensagemEvent {
    public Mensagens mensagens;
    public MensagemEvent(Mensagens mensagens){
        this.mensagens = mensagens;
    }
}
