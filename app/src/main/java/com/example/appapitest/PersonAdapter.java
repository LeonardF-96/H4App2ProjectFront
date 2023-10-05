package com.example.appapitest;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class PersonAdapter extends BaseAdapter
{
    List<Person> personList;
    MainActivity main;
    public PersonAdapter(MainActivity mainActivity, List<Person> list)
    {
        this.personList = list;
        this.main = mainActivity;
        Log.d("PersonAdapter", "Number of persons in the list: " + list.size());
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
        Log.d("PersonAdapter", "getView called for position: " + position);
        Person person = personList.get(position);
        View v = main.getLayoutInflater().inflate(R.layout.person_item, null);

        TextView name = v.findViewById(R.id.txtName);
        name.setText(person.getName());

        ImageView imv = v.findViewById(R.id.drwFavorite);
        imv.setImageResource(R.drawable.ic_thestarsmol);
        if (person.getFavorite())
        {
            imv.setVisibility(View.VISIBLE);
        }else
        {
            imv.setVisibility(View.GONE);
        }

        TextView phone = v.findViewById(R.id.txtPhone);
        phone.setText(String.valueOf(person.getPhone()));

        TextView note = v.findViewById(R.id.txtNote);
        note.setText(person.getNote());

        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Person selectedPerson = personList.get(position);

                Intent intent = new Intent(main, PersonDetailsActivity.class);
                intent.putExtra("selectedPerson", selectedPerson);
                main.startActivity(intent);
            }
        });

        return v;
    }
}
