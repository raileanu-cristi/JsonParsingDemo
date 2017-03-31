package com.raileanu.jsonparsingdemo;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Cristi on 31.03.2017.
 */

public class JSONTask extends AsyncTask<String, String, String>
{
    private TextView mTvData;

    public JSONTask(TextView tView)
    {
        mTvData = tView;
    }

    @Override
    protected String doInBackground(String... params)
    {
//        Log.println(Log.DEBUG, "[Debug] url= ", params[0]);
        String jsonResult = getJsonFromWeb(params[0]);
//        Log.println(Log.DEBUG, "[Debug] result= = ", jsonResult);
        return jsonResult;
    }

    @Override
    protected void onPostExecute(String result)
    {
        super.onPostExecute(result);

        if (result == null)
            result = "null!!!";


        mTvData.setText(result);
    }

    private String getJsonFromWeb(String urlString)
    {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try
        {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            Log.println(Log.DEBUG, "[Debug] ", "connecting..");
            connection.connect();
            Log.println(Log.DEBUG, "[Debug] ", "connected!");

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ( (line = reader.readLine()) != null)
            {
                buffer.append(line);

            }

            Log.println(Log.DEBUG, "[Debug] buffer = ", buffer.toString());
            return buffer.toString();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            Log.println(Log.ERROR, "[ERR] ", "could not connect!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            Log.println(Log.ERROR, "[ERR] ", "could not read!");
        }
        finally
        {
            if (connection != null)
                connection.disconnect();
            try
            {
                if (reader != null)
                    reader.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}
