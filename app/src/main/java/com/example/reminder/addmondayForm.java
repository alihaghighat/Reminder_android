package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.reminder.data.DatabaseHandler;
import com.example.reminder.model.Alarm;

import java.util.Calendar;
import java.util.List;


public class addmondayForm extends Fragment {
    public static FragmentTransaction transaction;
    static addmondayForm ourInstance;
    private Context context;
    public  int privty1;
    TextView title;
    ImageButton back;

    EditText title1;
    EditText url;
    EditText des;
    TimePicker time;
    RadioGroup priority;
    Button subbtn;


    public static synchronized addmondayForm getInstance() {
        if (ourInstance == null) {
            ourInstance = new addmondayForm();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addmonday_form,
                container, false);

        title=getActivity().findViewById(R.id.title);
        title.setText("Add Remider");
        title.setTextSize(25);
        //back to shanbe
        back=view.findViewById(R.id.backbtn_f_mondy);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,alarmThreeFragment.getInstance());
                transaction.commit();
            }


        });
        //end  back to shanbe
        // getdate
        subbtn=view.findViewById(R.id.subbtn_3);
        title1=view.findViewById(R.id.titleedite_3);
        url=view.findViewById(R.id.url_monday);
        des=view.findViewById(R.id.description_monday);
        time=view.findViewById(R.id.timePicker3);
        priority=view.findViewById(R.id.radioGroup_3);
        privty1=1;
        time.setIs24HourView(true);

        priority.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected

                switch(checkedId) {
                    case R.id.radio_High_3:
                        privty1=1;
                        break;
                    case R.id.radio_Low_3:
                        privty1=3;
                        break;
                    case R.id.radio_Medium_3:
                        privty1=2;
                        break;
                }
            }
        });


        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;
                hour = time.getCurrentHour();
                minute = time.getCurrentMinute();
                String strTime ;
                if(minute<10){
                    strTime=String.valueOf(hour)+":0"+String.valueOf(minute);

                }else{
                    strTime=String.valueOf(hour)+":"+String.valueOf(minute);
                }
                if(!title1.getText().toString().isEmpty()){
                    DatabaseHandler db =new DatabaseHandler(getActivity());
                    Alarm newAlarm= new Alarm();
                    newAlarm.setTilte(title1.getText().toString());
                    newAlarm.setWeek(3);
                    newAlarm.setPriority(privty1);
                    newAlarm.setDes(des.getText().toString());
                    newAlarm.setTime(strTime);
                    newAlarm.setUrl(url.getText().toString());
                    db.addArlam(newAlarm);
                    List<Alarm> alarmList = db.getAlram3();
                    Alarm temp=new Alarm();
                    for (Alarm alarm : alarmList) {
                        Log.d("MainActivity", "onCreate: " + alarm.getWeek());
                        temp=alarm;
                    }
                    //ID DAtabase
                    int ID = temp.getId();

                    //set calender
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY,hour);
                    cal.set(Calendar.MINUTE,minute);
                    cal.set(Calendar.SECOND,0);
                    int today=cal.get(Calendar.DAY_OF_WEEK);
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    if(today>Calendar.MONDAY){
                        cal.add(Calendar.DATE,7);

                    }


                    //Pas dadan data be resiver
                    Intent intent = new Intent(getActivity(), AlertReceiver.class);
                    intent.putExtra("alarm_ID",ID);
                    intent.putExtra("title",title1.getText().toString());
                    intent.putExtra("des",des.getText().toString());

                    if(!url.getText().toString().isEmpty()){
                        intent.putExtra("type",0);
                        intent.putExtra("url",url.getText().toString());
                    }
                    if(url.getText().toString().isEmpty()){
                        intent.putExtra("type",1);
                        intent.putExtra("week",3);
                    }

                    //alaem manger
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), ID, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                    alarmManager.setRepeating(alarmManager.RTC_WAKEUP,cal.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7,pendingIntent);



                    Toast.makeText(context.getApplicationContext(),"The alarm was successfully add",Toast.LENGTH_LONG).show();
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.farmain,alarmThreeFragment.getInstance());
                    transaction.commit();
                }else {
                    Toast.makeText(context.getApplicationContext(),"Please enter a title",Toast.LENGTH_LONG).show();
                }



            }
        });




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
