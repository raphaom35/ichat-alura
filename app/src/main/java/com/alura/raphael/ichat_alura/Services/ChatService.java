package com.alura.raphael.ichat_alura.Services;

import com.alura.raphael.ichat_alura.activity.MainActivity;
import com.alura.raphael.ichat_alura.modelo.Mensagens;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by raphael on 08/03/2017.
 */

public interface ChatService {


    @POST("polling")
     Call<Void> enviar( @Body Mensagens mensagens);
    @GET("polling")
    Call<Mensagens> ouvirMenssagens();
}
