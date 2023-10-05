package com.example.appapitest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends BaseAdapter
{
    List<Person> personList;
    MainActivity main;
    public PersonAdapter(MainActivity mainActivity, List<Person> list)
    {
        this.personList = list;
        this.main = mainActivity;
    }

    @Override
    public int getCount() {
        return this.personList.size();
    }

    @Override
    public Object getItem(int position) {
        return personList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        Person person = personList.get(position);
        View v = main.getLayoutInflater().inflate(R.layout.person_item, null);

        TextView name = v.findViewById(R.id.txtName);
        name.setText(person.getName());

        TextView phone = v.findViewById(R.id.txtPhone);
        phone.setText(person.getPhone());

        TextView note = v.findViewById(R.id.txtNote);
        note.setText(person.getNote());

        return v;
    }
}
