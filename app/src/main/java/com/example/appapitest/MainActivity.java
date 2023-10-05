package com.example.appapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{
    ListView  lstPersons;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstPersons = findViewById(R.id.lstPersons);

        IPersonService personService = ServiceBuilder.buildService(IPersonService.class);
        Call<List<Person>> request = personService.getAllPersons();
        request.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response)
            {
                List<Person> persons = response.body();

                PersonAdapter adapter = new PersonAdapter(MainActivity.this, persons);
                lstPersons.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t)
            {
                Toast.makeText(MainActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}