package com.example.nhom23.bookticket2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //you might want to check what's inside the Intent
//        if(intent.getStringExtra("myAction") != null &&
//                intent.getStringExtra("myAction").equals("notify")){
            NotificationManager manager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel1";
            String description = "default";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("my_channel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            manager.createNotificationChannel(channel);
        }

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"my_channel")
                    .setSmallIcon(R.drawable.bus_small_ic)
                    //example for large icon
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.bus_logo3))
                    .setContentTitle("Bus Reminder")
                    .setContentText("The route will be started in 30 min")
                    .setOngoing(false)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
            Intent i = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(
                            context,
                            0,
                            i,
                            PendingIntent.FLAG_ONE_SHOT
                    );
            // example for blinking LED
            builder.setLights(0xFFb71c1c, 1000, 2000);
            //builder.setSound(yourSoundUri);
            builder.setContentIntent(pendingIntent);
            manager.notify(12345, builder.build());

       // }
       // Toast.makeText(context,"You must be logged in before press button PAY",Toast.LENGTH_SHORT).show();
    }
}
