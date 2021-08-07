package com.example.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminder.model.Alarm;
import com.example.reminder.model.Email1;

import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.ViewHolder> {

    private List<Email1> AlarmList;
    private Context context;
    public static FragmentTransaction transaction;

    public RecyclerViewAdapter2(Context context, List<Email1> AlarmList) {
        this.context = context;
        this.AlarmList = AlarmList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;


             view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cart1_post, viewGroup, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Email1 alarm = AlarmList.get(position); // each contact object inside of our list

            viewHolder.title1.setText(alarm.getSubject());
            viewHolder.des1.setText(alarm.getSend_to()+ "\n"+alarm.getDate());
        viewHolder.iconButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                home.db2.deleteEmail(alarm.getId());
                Toast.makeText(context.getApplicationContext(),"Successfully Delet Email auto "+ alarm.getSubject(),Toast.LENGTH_LONG).show();
                AlarmList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,AlarmList.size());
                Intent intent = new Intent(context, AlertReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);


            }


        });








    }


    @Override
    public int getItemCount() {
        return AlarmList.size();
    }
    @Override
    public int getItemViewType(int position){
        return AlarmList.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title1;
        public TextView des1;
        public ImageView iconButton1;

        public TextView title2;
        public TextView des2;
        public ImageView iconButton2;

        public TextView title3;
        public TextView des3;
        public ImageView iconButton3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            title1=itemView.findViewById(R.id.title_cart_1);
            des1=itemView.findViewById(R.id.descrep_cart_1);
            iconButton1=itemView.findViewById(R.id.delletbtn_cart_1);

            title2=itemView.findViewById(R.id.title_cart_2);
            des2=itemView.findViewById(R.id.descrep_cart_2);
            iconButton2=itemView.findViewById(R.id.delletbtn_cart_2);

            title3=itemView.findViewById(R.id.title_cart_3);
            des3=itemView.findViewById(R.id.descrep_cart_3);
            iconButton3=itemView.findViewById(R.id.delletbtn_cart_3);





        }

        @Override
        public void onClick(View v) {




//            switch (v.getId()) {
//                case R.id.icon_button:
//                    Log.d("IconClicked", "onClick: " + contact.getPhoneNumber());
//                    break;
//            }


            // Log.d("Clicked", "onClick: " + contact.getName());

        }
    }
}