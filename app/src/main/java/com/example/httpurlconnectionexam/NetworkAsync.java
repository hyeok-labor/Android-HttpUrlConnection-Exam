package com.example.httpurlconnectionexam;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class NetworkAsync extends AsyncTask<Integer, String, Integer> {



    final static String TAG = "NetworkAsync";

    Context mContext = null;

    String mAddr;

    ProgressDialog dialog = null;



    public NetworkAsync(Context c, String a){

        mContext = c;

        mAddr = a;

    }



    @Override
    protected void onPreExecute() {

        Log.i(TAG,"onPreExecute()");

        dialog = new ProgressDialog(mContext);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        dialog.setTitle("Dialog");

        dialog.setMessage("down...");

        dialog.show();

    }



    @Override

    protected Integer doInBackground(Integer... integers) {

        Log.i(TAG,"doInBackground()");



        StringBuffer sb = new StringBuffer();

        InputStream is = null;

        InputStreamReader isr = null;

        BufferedReader br = null;

        Log.i(TAG,"doInBackground2()");

        try{

            URL url = new URL(mAddr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setConnectTimeout(10000);



            if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){

                String res = Integer.toString(conn.getResponseCode());

                String http = Integer.toString(HttpURLConnection.HTTP_OK);

                Log.i(TAG,res);

                Log.i(TAG,http);

                Log.i(TAG,"doInBackground3()");

                is = conn.getInputStream();

                isr = new InputStreamReader(is);

                br = new BufferedReader(isr);



                while(true){

                    String strLine = br.readLine();

                    if(strLine == null) break;

                    sb.append(strLine + "\n");

                }

                Log.i(TAG, "sb : " + sb.toString());

            }

        }catch(Exception e){

            e.printStackTrace();

        }finally {

            try{

                if(br!= null) br.close();

                if(isr!= null) isr.close();

                if(is!= null) is.close();

            }catch(Exception e2){

                e2.printStackTrace();

            }

        }

        return  null;

    }



    @Override

    protected void onProgressUpdate(String... values) {

        Log.i(TAG,"onProgressUpdate()");

        super.onProgressUpdate(values);

    }



    @Override

    protected void onPostExecute(Integer integer) {
        Log.i(TAG,"onPostExecute()");
        dialog.dismiss();
    }



    @Override

    protected void onCancelled(Integer integer) {

        Log.i(TAG,"onCancelled()");

        super.onCancelled();

    }

}

