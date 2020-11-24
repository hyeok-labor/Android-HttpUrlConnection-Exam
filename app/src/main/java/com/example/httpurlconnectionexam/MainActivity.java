package com.example.httpurlconnectionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    final static String TAG = "MainActivity";

    String urlAddr = "https://m.naver.com/";

    Button btnNetCon;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        btnNetCon = (Button) findViewById(R.id.bt_network_con);

        btnNetCon.setOnClickListener(listener);

    }



    View.OnClickListener listener = new View.OnClickListener() {

        @Override

        public void onClick(View view) {

            switch (view.getId()){

                case R.id.bt_network_con:

                    NetworkAsync network = new NetworkAsync(MainActivity.this, urlAddr);

                    network.execute(100);

                    break;

            }

        }

    };

}
