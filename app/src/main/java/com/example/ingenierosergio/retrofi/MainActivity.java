package com.example.ingenierosergio.retrofi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ingenierosergio.retrofi.modelos.Catalogo;
import com.example.ingenierosergio.retrofi.modelos.Course;
import com.example.ingenierosergio.retrofi.modelos.Instructor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="Funciona:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service=retrofit.create(Service.class);
        Call<Catalogo> requestCatalogo=service.listaCatalogo();

        requestCatalogo.enqueue(new Callback<Catalogo>() {
            @Override
            public void onResponse(Call<Catalogo> call, Response<Catalogo> response) {
                       if(!response.isSuccessful()){
                           Log.i(TAG,"ERROR"+response.body());

                       }else{
                           Catalogo catalogo=response.body();
                           Log.i(TAG,"devolucion de la Peticion :"+response.body());

                           for(Course c:catalogo.courses){
                               Log.i(TAG,String.format("%s: %s",c.title,c.subtitle));

                               for (Instructor i:c.instructors){
                                   Log.i(TAG,i.name);
                               }
                               Log.i(TAG,"----------");
                           }


                       }
            }

            @Override
            public void onFailure(Call<Catalogo> call, Throwable t) {
                     Log.i(TAG,"-------------");
            }
        });



    }
}
