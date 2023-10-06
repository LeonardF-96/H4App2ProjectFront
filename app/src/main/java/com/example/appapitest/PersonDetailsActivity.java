package com.example.appapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class PersonDetailsActivity extends AppCompatActivity {
    TextView txtId;
    EditText txtName, txtPhone, txtAddress, txtNote;
    CheckBox chkFavorite;
    Spinner spnHairColor;
    RadioGroup rdgProgLang;
    Button btnUpdate, btnDelete, btnCancel;
    ArrayAdapter<String> hairColorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_details);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtNote = findViewById(R.id.txtNote);

        chkFavorite = findViewById(R.id.chkFavorite);
        spnHairColor = findViewById(R.id.spnHairColor);
        rdgProgLang = findViewById(R.id.rdgProgLang);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);


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


        }
    }
    public void onClickCancel(View view)
    {
        finish();
    }
}