package com.example.tpintegrador;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class MyReciver extends BroadcastReceiver {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    public static final String EVENTO_01="frsf.isi.dam.app04.EVENTO_01_MSG";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("APP04","Recibido "+intent.getAction());
        Toast.makeText(context,"=>"+intent.getAction(),Toast.LENGTH_LONG).show();
        if(intent.getAction().equalsIgnoreCase(EVENTO_01)){
             enviarNotificacion(context,intent);
            // enviarNotificacionImagen(context,intent);


        }

    }

    private void enviarNotificacion(Context context,Intent intent){
        Intent opcion1 = new Intent(context, PropiedadesRecycler.class);
        opcion1.putExtra("datox", 9999);
        opcion1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent opcion1Pi = PendingIntent.getBroadcast(context, 0, opcion1, 0);

        //Patron builder para configurar
        // como quiero que se construyan los objetos
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.drawable.casa)
                // .setContentIntent(opcion1Pi)
                .setContentTitle(intent.getExtras().getString("data1"))
                .setContentText(intent.getExtras().getString("data2"))
                .setAutoCancel(true)
                .addAction(android.R.drawable.ic_media_play,"EJECUTAR",opcion1Pi)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Notification laNotificacion =  mBuilder.build();

        // obtengo el notification manager
        NotificationManager notificationManager =
                context.getSystemService(NotificationManager.class);
        // envio la notificacion
        notificationManager.notify(99,laNotificacion);
    }


    private void enviarNotificacionImagen(Context context,Intent intent){
       // Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.utn_icon);
       // Bitmap aBigBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.utnsantafe);

        Intent opcion1 = new Intent(context, MainActivity.class);
        opcion1.putExtra("datox", 9999);
        PendingIntent opcion1Pi = PendingIntent.getBroadcast(context, 0, opcion1, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
              //  .setSmallIcon(R.drawable.utn_icon)
                .setContentIntent(opcion1Pi)
                .setContentTitle(intent.getExtras().getString("data1"))
                .setContentText(intent.getExtras().getString("data2"))
             //   .setLargeIcon(largeIcon)
                .setStyle(
                        new NotificationCompat.BigPictureStyle()
                              //  .bigPicture(aBigBitmap)
                                .setBigContentTitle("Mi Notificacion con imagen"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManager notificationManager =
                context.getSystemService(NotificationManager.class);
        notificationManager.notify(99, mBuilder.build());
    }




}
