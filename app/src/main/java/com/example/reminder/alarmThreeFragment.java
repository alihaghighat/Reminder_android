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


public class alarmThreeFragment extends Fragment {

    static alarmThreeFragment ourInstance;
    private Context context;
    public static FragmentTransaction transaction;
    FloatingActionButton add_monday;
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



    public static synchronized alarmThreeFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new alarmThreeFragment();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_three,
                container, false);
        tilti= getActivity().findViewById(R.id.title);
        tilti.setText("Home");

        satueday=view.findViewById(R.id.saturday3);
        satueday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmOnFragment.getInstance());
                transaction.commit();
            }


        });
        sunday=view.findViewById(R.id.Sunday3);
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
        tuesday=view.findViewById(R.id.Tuesday3);
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
        wednesday=view.findViewById(R.id.Wednesday3);
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
        thursday=view.findViewById(R.id.Thursday3);
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
        friday=view.findViewById(R.id.Friday3);
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


        add_monday= view.findViewById(R.id.add_monday);

        add_monday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,addmondayForm.getInstance());
                transaction.commit();
            }


        });
        RecyclerView recyclerView = view.findViewById(R.id.mondyR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        List<Alarm> alarmList = home.db.getAlram3();
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
