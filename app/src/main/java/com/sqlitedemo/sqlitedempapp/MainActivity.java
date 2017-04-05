package com.sqlitedemo.sqlitedempapp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Notification.Builder notify_builder;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveData(View v){
        String name =((EditText)findViewById(R.id.editText)).getText().toString();
        String password =((EditText)findViewById(R.id.editText2)).getText().toString();
        DatabaseHelperClass dbHelper = new DatabaseHelperClass(this);
        dbHelper.addUser(name,password);
        Toast.makeText(this,"User Data is Saved",Toast.LENGTH_LONG).show();
    }

    public void searchUser(View v){
        String name =((EditText)findViewById(R.id.editText)).getText().toString();
        DatabaseHelperClass dbHelper = new DatabaseHelperClass(this);
        Toast.makeText(this,dbHelper.getUsers(name),Toast.LENGTH_LONG).show();
    }

    public void updatePassword(View v){
        String name =((EditText)findViewById(R.id.editText)).getText().toString();
        String password =((EditText)findViewById(R.id.editText2)).getText().toString();
        DatabaseHelperClass dbHelper = new DatabaseHelperClass(this);
        dbHelper.updateUser(name,password);
        Toast.makeText(this,"Your Password is Saved",Toast.LENGTH_LONG).show();
    }

    public void deleteLogin(View v){
        String name =((EditText)findViewById(R.id.editText)).getText().toString();
        DatabaseHelperClass dbHelper = new DatabaseHelperClass(this);
        dbHelper.deleteUser(name);
        Toast.makeText(this,"User is deleted",Toast.LENGTH_LONG).show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(View v){
        notify_builder = new Notification.Builder(this);
        notify_builder.setSmallIcon(R.mipmap.ic_launcher);
        notify_builder.setContentText("Task is Completed");
        notify_builder.setContentTitle("Success");

        Intent resultIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        notify_builder.setContentIntent(resultPendingIntent);

        NotificationManager manager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(1,notify_builder.build());
    }
}
