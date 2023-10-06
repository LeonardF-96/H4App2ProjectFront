package com.example.appapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonDetailsActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    TextView txtId;
    EditText txtName, txtPhone, txtAddress, txtNote;
    CheckBox chkFavorite;
    Spinner spnHairColor;
    RadioGroup rdgProgLang;
    Button btnUpdate, btnDelete, btnCancel;
    String[] hairColors = {"Black", "Meanblond", "Brown"};
    int chosenHairColor, chosenProgLang, selectedRadioButtonId;
    ArrayAdapter<String> hairColorAdapter;

    private IPersonService personService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        personService = ServiceBuilder.buildService(IPersonService.class);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtNote = findViewById(R.id.txtNote);

        chkFavorite = findViewById(R.id.chkFavorite);
        spnHairColor = findViewById(R.id.spnHairColor);
        rdgProgLang = findViewById(R.id.rdgProgLang);
        rdgProgLang.setOnCheckedChangeListener(this);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        ArrayAdapter<String> adapterHair = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hairColors);
        adapterHair.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnHairColor.setAdapter(adapterHair);

        spnHairColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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


        //Retrieve the selected person's data from the intent
        Intent intent = getIntent();
        if(intent != null)
        {
            Person selectedPerson = intent.getParcelableExtra("selectedPerson");
            //Populate UI elements with the selected person's data
            txtId.setText(String.valueOf(selectedPerson.getId()));
            txtName.setText(selectedPerson.getName());
            txtPhone.setText(String.valueOf(selectedPerson.getPhone()));
            txtAddress.setText(selectedPerson.getAddress());
            txtNote.setText(selectedPerson.getNote());

            chkFavorite.setChecked(selectedPerson.getFavorite());

            ArrayAdapter<String> adapter = (ArrayAdapter<String>) spnHairColor.getAdapter();
            int position = adapter.getPosition(selectedPerson.getHairColor());
            spnHairColor.setSelection(position);

            // Set the selected radio button in rdgProgLang
            switch (selectedPerson.getProgramLanguageId()) {
                case 1000:
                    Log.d("MainActivity", "Checking C# radio button");
                    rdgProgLang.check(R.id.rdbCSharp);
                    chosenProgLang = 1000;
                    break;
                case 1001:
                    Log.d("MainActivity", "Checking PHP radio button");
                    rdgProgLang.check(R.id.rdbPHP);
                    chosenProgLang = 1001;
                    break;
                case 1002:
                    Log.d("MainActivity", "Checking Java radio button");
                    rdgProgLang.check(R.id.rdbJava);
                    chosenProgLang = 1002;
                    break;
                // Handle default case or any other values if needed
            }
            int programLanguageId = selectedPerson.getProgramLanguageId();
            Log.d("MainActivity", "Program Language ID: " + programLanguageId);
        }
    }
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i)
    {
        Log.d("MainActivity", "Radio Button checked: " + i);
        selectedRadioButtonId = rdgProgLang.getCheckedRadioButtonId();
        if (selectedRadioButtonId == R.id.rdbCSharp)
        {
            chosenProgLang = 1000;
        }
        else if (selectedRadioButtonId == R.id.rdbPHP)
        {
            chosenProgLang = 1001;
        }
        else if (selectedRadioButtonId == R.id.rdbJava)
        {
            chosenProgLang = 1002;
        }
    }

    private int mapHairColor(int chosenHairColor) {
        // Map chosenHairColor to hairId
        switch (chosenHairColor) {
            case 1000:
                return 1000; // Map to the corresponding hairId value
            case 1001:
                return 1001; // Map to the corresponding hairId value
            case 1002:
                return 1002; // Map to the corresponding hairId value
            default:
                return -1; // Handle default case or invalid input
        }
    }

    private int mapProgLang(int chosenProgLang) {
        // Map chosenProgLang to programLanguageId
        switch (chosenProgLang) {
            case 1000:
                return 1000; // Map to the corresponding programLanguageId value
            case 1001:
                return 1001; // Map to the corresponding programLanguageId value
            case 1002:
                return 1002; // Map to the corresponding programLanguageId value
            default:
                return -1; // Handle default case or invalid input
        }
    }

    public void onClickUpdate(View view)
    {
        Log.d("MainActivity", "onClickUpdate called");
        try {
            String idText = String.valueOf(txtId.getText());
            String name = String.valueOf(txtName.getText());
            String phoneText = String.valueOf(txtPhone.getText());
            String address = String.valueOf(txtAddress.getText());
            String note = String.valueOf(txtNote.getText());

            int phone = 0;

            try {
                phone = Integer.parseInt(phoneText);
            } catch (NumberFormatException e) {
                // Handle the case where phone number parsing fails (e.g., invalid input)
                e.printStackTrace();
                // Optionally, show an error message to the user
            }

            int id = 0;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                // Handle the case where phone number parsing fails (e.g., invalid input)
                e.printStackTrace();
                // Optionally, show an error message to the user
            }

            boolean isFavorite = chkFavorite.isChecked();

            int mappedHairColor = mapHairColor(chosenHairColor);
            int mappedProgLang = mapProgLang(chosenProgLang);

            Person updatedPerson = new Person(id, name, phone, address, note, isFavorite, mappedHairColor, mappedProgLang);

            Log.d("MainActivity", "name: " + name);
            Log.d("MainActivity", "phone: " + phone);
            Log.d("MainActivity", "address: " + address);
            Log.d("MainActivity", "note: " + note);
            Log.d("MainActivity", "isFavorite: " + isFavorite);
            Log.d("MainActivity", "chosenHairColor: " + chosenHairColor);
            Log.d("MainActivity", "chosenProgLang: " + chosenProgLang);

            Log.d("Update", "Updated Person: " + updatedPerson.toString());

            Call<Void> updatePersonCall = personService.updatePerson(id, updatedPerson);

            updatePersonCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        finish();
                    } else {
                        int errorCode = response.code();
                        Log.e("UpdateError", "Update failed with error code: " + errorCode);
                        try {
                            String errorBody = response.errorBody().string();
                            Log.e("UpdateError", "Error response body: " + errorBody);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("UpdateError", "Update failed with exception: " + t.getMessage());
                    // Handle the failure (e.g., show an error message)
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other potential exceptions here
            // Optionally, show an error message to the user
        }
    }
    public void onClickDelete(View view)
    {
        Log.d("MainActivity", "onClickDelete called");
        try
        {
            String idText = String.valueOf(txtId.getText());

            int id = 0;
            try {
                id = Integer.parseInt(idText);
            } catch (NumberFormatException e) {
                // Handle the case where phone number parsing fails (e.g., invalid input)
                e.printStackTrace();
                // Optionally, show an error message to the user
            }
            Call<Void> deletePersonCall = personService.deletePerson(id);
            deletePersonCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response)
                {
                    if(response.isSuccessful())
                    {
                        Log.d("MainActivity", "Update/Delete successful");
                        finish();
                    }
                    else
                    {
                        int errorCode = response.code();
                        Log.e("MainActivity", "Update/Delete failed with error code: " + errorCode);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    Log.e("MainActivity", "Update/Delete failed with exception: " + t.getMessage());
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onClickCancel(View view)
    {
        finish();
    }
}