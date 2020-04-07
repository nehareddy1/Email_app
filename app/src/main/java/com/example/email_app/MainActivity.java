// main class which displays a button and when clicked email will be sent to the specified email
package com.example.email_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declarations
    Button button;
    GMailSender sender;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.mybtn); // accessed button from xml
        // Here we add email id and password of the account we are sending an email
        sender = new GMailSender("memcommity@gmail.com", "memcomm2020");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.

                Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


            button.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {

                    try {
                        new MyAsyncClass().execute();
                    }

                    catch (Exception ex)

                    {
                        Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        class MyAsyncClass extends AsyncTask<Void, Void, Void> {

            ProgressDialog pDialog;

            protected void onPreExecute() {

                super.onPreExecute();

                pDialog = new ProgressDialog(MainActivity.this);

                pDialog.setMessage("Please wait...");

                pDialog.show();

            }

            protected Void doInBackground(Void... mApi) {

                try {
                    // Here we add subject, Body, your mail Id, and receiver mail Id's .
                    sender.sendMail("Meeting tomorrow", " Hi how are you!", "memcommity@gmail.com", "nikhineha1@gmail.com,pnehareddy1@gmail.com");
                }

                catch (Exception ex) {

                }
                return null;
            }
            protected void onPostExecute(Void result) {

                super.onPostExecute(result);

                pDialog.cancel();

                Toast.makeText(getApplicationContext(), "Email sent",Toast.LENGTH_SHORT).show();

            }
        }
    }


