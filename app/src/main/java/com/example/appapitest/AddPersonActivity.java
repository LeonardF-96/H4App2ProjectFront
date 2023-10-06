package com.example.appapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPersonActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    TextView txtIdAdd;
    EditText txtNameAdd, txtPhoneAdd, txtAddressAdd, txtNoteAdd;
    CheckBox chkFavoriteAdd;
    Spinner spnHairColorAdd;
    RadioGroup rdgProgLangAdd;
    Button btnAddPerson, btnCancelAdd;

    String[] hairColors = {"Black", "Meanblond", "Brown"};

    int chosenHairColor, chosenProgLang, selectedRadioButtonId;
    private IPersonService personService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        personService = ServiceBuilder.buildService(IPersonService.class);

        txtIdAdd = findViewById(R.id.txtIdAdd);
        txtNameAdd = findViewById(R.id.txtNameAdd);
        txtPhoneAdd = findViewById(R.id.txtPhoneAdd);
        txtAddressAdd = findViewById(R.id.txtAddressAdd);
        txtNoteAdd = findViewById(R.id.txtNoteAdd);

        chkFavoriteAdd = findViewById(R.id.chkFavoriteAdd);
        spnHairColorAdd = findViewById(R.id.spnHairColorAdd);
        rdgProgLangAdd = findViewById(R.id.rdgProgLangAdd);
        rdgProgLangAdd.setOnCheckedChangeListener(this);

        btnAddPerson = findViewById(R.id.btnAddPerson);
        btnCancelAdd = findViewById(R.id.btnCancelAdd);

        ArrayAdapter<String> adapterHair = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hairColors);

        adapterHair.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnHairColorAdd.setAdapter(adapterHair);

        spnHairColorAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                if(i == 0)
                {
                    chosenHairColor = 1000;
                }
                else if (i == 1)
                {
                    chosenHairColor = 1001;
                }
                else if (i == 2)
                {
                    chosenHairColor = 1002;
                }
                else
                {
                    chosenHairColor = -1;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i)
    {
        selectedRadioButtonId = rdgProgLangAdd.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.rdbCSharpAdd)
        {
            chosenProgLang = 1000;
        }
        else if (selectedRadioButtonId == R.id.rdbPHPAdd)
        {
            chosenProgLang = 1001;
        }
        else if (selectedRadioButtonId == R.id.rdbJavaAdd)
        {
            chosenProgLang = 1002;
        }
    }
    public void onClickAddPerson(View view) {
        try {
            String name = String.valueOf(txtNameAdd.getText());
            String phoneText = String.valueOf(txtPhoneAdd.getText());
            String address = String.valueOf(txtAddressAdd.getText());
            String note = String.valueOf(txtNoteAdd.getText());

            int phone = 0;

            try {
                phone = Integer.parseInt(phoneText);
            } catch (NumberFormatException e) {
                // Handle the case where phone number parsing fails (e.g., invalid input)
                e.printStackTrace();
                // Optionally, show an error message to the user
            }

            boolean isFavorite = chkFavoriteAdd.isChecked();

            JSONObject personJSON = new JSONObject();
            personJSON.put("name", name);
            personJSON.put("phone", phone);
            personJSON.put("address", address);
            personJSON.put("note", note);
            personJSON.put("favorite", isFavorite);
            personJSON.put("hairId", chosenHairColor);
            personJSON.put("programLanguageId", chosenProgLang);

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSON, personJSON.toString());

            //IPersonService personService = ServiceBuilder.buildService(IPersonService.class);
            Call<Integer> createPersonCall = personService.createNewPersonJson(requestBody);

            createPersonCall.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful()) {
                        int createdPersonId = response.body();
                        txtIdAdd.setText(String.valueOf(createdPersonId)); // Convert to String
                        finish();
                    } else {
                        int errorCode = response.code();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    t.printStackTrace();
                    // Handle the failure (e.g., show an error message)
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other potential exceptions here
            // Optionally, show an error message to the user
        }
    }
    public void onClickCancel(View view)
    {
        finish();
    }
}