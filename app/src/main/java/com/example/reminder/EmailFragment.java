package com.example.reminder;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.reminder.model.Alarm;
import com.example.reminder.model.Email1;

import java.util.ArrayList;
import java.util.List;


public class EmailFragment extends Fragment {

    static EmailFragment ourInstance;
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter2 recyclerViewAdapter;
    private static ArrayList<Email1> AclarmArrayList;
    private ArrayAdapter<String> arrayAdapter;

    TextView title;
    FloatingActionButton add_email;
    public static FragmentTransaction transaction;

    public static synchronized EmailFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new EmailFragment();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email,
                container, false);
        findViews(view);
        title=getActivity().findViewById(R.id.title);
        title.setText("Email");
        title.setTextSize(25);

        add_email= view.findViewById(R.id.add_email);

        add_email.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,AddEmailFragment.getInstance());
                transaction.commit();
            }


        });
        RecyclerView recyclerView = view.findViewById(R.id.fgfdg);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        List<Email1> alarmList = home.db2.getEmail();
        AclarmArrayList = new ArrayList<>();
        for (Email1 alarm : alarmList) {
            Log.d("MainActivity", "onCreate: " + alarm.getDate());
            AclarmArrayList.add(alarm);
        }



        //setup adapter
        recyclerViewAdapter = new RecyclerViewAdapter2(getActivity().getApplicationContext(),AclarmArrayList);

        recyclerView.setAdapter(recyclerViewAdapter);


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
