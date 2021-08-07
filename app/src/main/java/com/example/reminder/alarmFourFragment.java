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


public class alarmFourFragment extends Fragment {

    static alarmFourFragment ourInstance;
    private Context context;
    public static FragmentTransaction transaction;
    FloatingActionButton add_tuesday;
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



    public static synchronized alarmFourFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new alarmFourFragment();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm_four,
                container, false);
        tilti= getActivity().findViewById(R.id.title);
        tilti.setText("Home");

        satueday=view.findViewById(R.id.saturdy4);
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
        sunday=view.findViewById(R.id.Sunday4);
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
        monday=view.findViewById(R.id.Monday4);
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
        wednesday=view.findViewById(R.id.Wednesday4);
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
        thursday=view.findViewById(R.id.Thursday4);
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
        friday=view.findViewById(R.id.Friday4);
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


        add_tuesday= view.findViewById(R.id.add_tuesday);

        add_tuesday.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,addtuesdayForm.getInstance());
                transaction.commit();
            }


        });
        RecyclerView recyclerView = view.findViewById(R.id.tuesdayR);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        List<Alarm> alarmList = home.db.getAlram4();
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
