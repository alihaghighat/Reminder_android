package com.example.reminder;

import android.app.AliasActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.reminder.data.DatabaseHandler;
import com.example.reminder.model.Alarm;

import java.util.ArrayList;
import java.util.List;


public class alarmOnFragment extends Fragment {

    static alarmOnFragment ourInstance;
    private Context context;
    public static FragmentTransaction transaction;
    FloatingActionButton add_saturdy;
    TextView tilti;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private static ArrayList<Alarm> AclarmArrayList;
    private ArrayAdapter<String> arrayAdapter;

    Button satueday;
    Button sunday;
    Button monday;
    Button tuesday;
    Button wednesday;
    Button thursday;
    Button friday;



    public static synchronized alarmOnFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new alarmOnFragment();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_on,
                container, false);
        tilti= getActivity().findViewById(R.id.title);
        tilti.setText("Home");

        sunday=view.findViewById(R.id.Sunday1);
        sunday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmTwoFragment.getInstance());
                transaction.commit();
            }


        });
        monday=view.findViewById(R.id.Monday1);
        monday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmThreeFragment.getInstance());
                transaction.commit();
            }


        });
        tuesday=view.findViewById(R.id.Tuesday1);
        tuesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmFourFragment.getInstance());
                transaction.commit();
            }


        });
        wednesday=view.findViewById(R.id.Wednesday1);
        wednesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmFiveFragment.getInstance());
                transaction.commit();
            }


        });
        thursday=view.findViewById(R.id.Thursday1);
        thursday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmSixFragment.getInstance());
                transaction.commit();
            }


        });
        friday=view.findViewById(R.id.Friday1);
        friday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmSevenFragment.getInstance());
                transaction.commit();
            }


        });



        add_saturdy= view.findViewById(R.id.add_saturdy);

        add_saturdy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,addsatrdyForm.getInstance());
                transaction.commit();
            }


        });
        RecyclerView recyclerView = view.findViewById(R.id.satyrdayR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
       List<Alarm> alarmList = home.db.getAlram1();
        AclarmArrayList = new ArrayList<>();
        for (Alarm alarm : alarmList) {
            Log.d("MainActivity", "onCreate: " + alarm.getWeek());
            AclarmArrayList.add(alarm);
        }



        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity().getApplicationContext(),AclarmArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);




        findViews(view);
        return view;
    }



    private void setData() {




    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    private void findViews(View view){

    }

}
