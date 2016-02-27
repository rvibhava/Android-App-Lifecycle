package com.example.rayala.androidassignment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    private TextView counterText;
    private long startTime;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterText = (TextView)findViewById(R.id.threadVal);

        Counter();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void Counter(){


        startTime = System.currentTimeMillis();
        handler.post(new Runnable() {

            @Override
            public void run() {

                long timeDifference = System.currentTimeMillis() - startTime;
                counterText.setText(Long.toString(timeDifference / 1000));
                handler.postDelayed(this, 1000);
            }


        });
    }


    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Hi!! I'm an alert Dialog!! What are you ??");

        alertDialogBuilder.setPositiveButton("Yes! Go Ahead ! Click me!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this, "The Counter is incrementing in the background!! Activity not changed!!", Toast.LENGTH_LONG).show();
            }
        });

        alertDialogBuilder.setNegativeButton("NO!! Don't touch me!!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(MainActivity.this, "The Counter Still Increments!!, Since the activity is not changed!!!", Toast.LENGTH_LONG).show();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void gotoB(View v){


        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);

    }

    public void exitApp(View v){

        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    @Override
    public void onResume() {
        super.onResume();
        Counter();

    }


}
