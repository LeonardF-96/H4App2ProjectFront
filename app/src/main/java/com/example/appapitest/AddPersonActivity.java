package com.example.appapitest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class AddPersonActivity extends AppCompatActivity {

    TextView txtIdAdd;
    EditText txtNameAdd, txtPhoneAdd, txtAddressAdd, txtNoteAdd;
    CheckBox chkFavoriteAdd;
    Spinner spnHairColorAdd;
    RadioGroup rdgProgLangAdd;
    Button btnAddPerson, btnCancelAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        txtIdAdd = findViewById(R.id.txtIdAdd);
        txtNameAdd = findViewById(R.id.txtNameAdd);
        txtPhoneAdd = findViewById(R.id.txtPhoneAdd);
        txtAddressAdd = findViewById(R.id.txtAddressAdd);
        txtNoteAdd = findViewById(R.id.txtNoteAdd);

        chkFavoriteAdd = findViewById(R.id.chkFavoriteAdd);
        spnHairColorAdd = findViewById(R.id.spnHairColorAdd);
        rdgProgLangAdd = findViewById(R.id.rdgProgLangAdd);

        btnAddPerson = findViewById(R.id.btnAddPerson);
        btnCancelAdd = findViewById(R.id.btnCancelAdd);
    }
    public void onClickCancel(View view)
    {
        finish();
    }
}