package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.reminder.data.DatabaseHandler;
import com.example.reminder.data.DatabaseHandlerTo;
import com.example.reminder.model.Alarm;
import com.example.reminder.model.Email1;

import java.util.Calendar;
import java.util.List;


public class AddEmailFragment extends Fragment {
    public static FragmentTransaction transaction;
    static AddEmailFragment ourInstance;
    private Context context;
    public  int privty1;
    TextView title;
    ImageButton back;

    EditText email;
    EditText subject;
    EditText masseg;
    TimePicker time;
    DatePicker calender;
    Button btn;
    Email1 email1=new Email1();


    public static synchronized AddEmailFragment getInstance() {
        if (ourInstance == null) {
            ourInstance = new AddEmailFragment();
        }
        return ourInstance;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_email,
                container, false);

        title=getActivity().findViewById(R.id.title);
        title.setText("Add Eamil");
        title.setTextSize(25);
        //back to shanbe
        back=view.findViewById(R.id.backbtn_f_saturdy);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.farmain,EmailFragment.getInstance());
                transaction.commit();
            }


        });
        btn=view.findViewById(R.id.ali_btn);
        email=view.findViewById(R.id.email);
        subject=view.findViewById(R.id.subject);
        masseg=view.findViewById(R.id.masseg);
        time=view.findViewById(R.id.timePicker1);
        calender=view.findViewById(R.id.datapuker);
        time.setIs24HourView(true);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour, minute;
                hour = time.getCurrentHour();
                minute = time.getCurrentMinute();
                int day = calender.getDayOfMonth();
                int month = calender.getMonth();
                int year =  calender.getYear();

                Calendar calendar = Calendar.getInstance();


                String Date= " "+year+"/"+(month+1)+"/"+day+" "+hour+" : "+minute;

                if(!masseg.getText().toString().isEmpty() & !subject.getText().toString().isEmpty() & !email.getText().toString().isEmpty()){
                   email1.setSend_to(email.getText().toString());
                   email1.setSubject(subject.getText().toString());
                   email1.setMasseg(masseg.getText().toString());
                    email1.setDate(Date);
                    DatabaseHandlerTo db =new DatabaseHandlerTo(getActivity());
                    db.addemail(email1);
                    List<Email1> email1List = db.getEmail();
                    Email1 temp=new Email1();
                    for (Email1 email : email1List) {
                        Log.d("MainActivity", "onCreate: " + email.getMasseg());
                        temp=email;
                    }
                    //ID DAtabase
                    int ID = temp.getId();
                    calendar.set(year,month,day);
                    calendar.set(Calendar.HOUR_OF_DAY,hour);
                    calendar.set(Calendar.MINUTE,minute);
                    calendar.set(Calendar.SECOND,0);


                    Intent intent = new Intent(getActivity(), emailReceiver.class);
                    intent.putExtra("Email_ID",ID);
                    intent.putExtra("sendto",email.getText().toString());
                    intent.putExtra("subject",subject.getText().toString());
                    intent.putExtra("Masseg", masseg.getText().toString());

                    //alaem manger
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), ID, intent, 0);

                    AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                    alarmManager.setRepeating(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY*7,pendingIntent);


                    Toast.makeText(context.getApplicationContext(),"The alarm was successfully add",Toast.LENGTH_LONG).show();
                    transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.farmain,EmailFragment.getInstance());
                    transaction.commit();
                }else {
                    Toast.makeText(context.getApplicationContext(),"Please Enter the essentials ",Toast.LENGTH_LONG).show();
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
