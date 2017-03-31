package com.raileanu.jsonparsingdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hitButtonInit();
    }

    private void hitButtonInit()
    {
        Button btnHit = (Button) findViewById(R.id.btnHit);
        final TextView tvItem = (TextView) findViewById(R.id.tvJsonItem);

        btnHit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                new JSONTask(tvItem).execute("https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesDemoItem.txt");
                new JSONTask(tvItem).execute("http://www.jrsdr.robertoderesu.com/swagger/v1/swagger.json");

            }
        });

    }
}
