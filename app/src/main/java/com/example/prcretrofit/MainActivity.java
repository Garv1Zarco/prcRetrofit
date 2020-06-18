package com.example.prcretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado = findViewById(R.id.tv_resultado);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    resultado.setText("Code: "+response.code());
                    return;
                }

                List<Post> contenido = response.body();

                for (Post p:contenido){
                    String mostrar = "";
                    mostrar += "el id es: "+ p.getId();
                    mostrar += "el user id es: "+p.getUserId();
                    mostrar += "el titulo es: "+p.getTitle();
                    mostrar += "el texto es: "+p.getText();

                    resultado.append(mostrar);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                resultado.setText(t.getMessage());
            }
        });
    }
}