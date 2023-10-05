package com.example.appapitest;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity
{
    ListView  lstPersons;
    Button btnAdd;
    ActivityResultLauncher<Intent> AddPersonActivityLauncher;
    private static final int ADD_NEWPERSON_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstPersons = findViewById(R.id.lstPersons);
        btnAdd = findViewById(R.id.btnAdd);

        IPersonService personService = ServiceBuilder.buildService(IPersonService.class);
        Call<List<Person>> request = personService.getAllPersons();
        request.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response)
            {
                if(response.isSuccessful())
                {
                    List<Person> persons = response.body();

                    Log.d("MainActivity", "Number of persons received; " + persons.size());

                    PersonAdapter adapter = new PersonAdapter(MainActivity.this, persons);
                    lstPersons.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                else
                {
                    Log.e("MainActivity", "API request failed with code: " + response.code());
                    Toast.makeText(MainActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
                }
//                List<Person> persons = response.body();
//
//                PersonAdapter adapter = new PersonAdapter(MainActivity.this, persons);
//                lstPersons.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t)
            {
                Log.e("MainActivity", "API request failed with exception: " + t.getMessage());
                Toast.makeText(MainActivity.this, "API request failed", Toast.LENGTH_SHORT).show();
            }
        });
        AddPersonActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if(result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent resultIntent = result.getData();
                        }
                    }
                });
    }

    public void onClickAdd(View v)
    {
        Intent intent = new Intent(this,AddPersonActivity.class);
        AddPersonActivityLauncher.launch(intent);
    }
}