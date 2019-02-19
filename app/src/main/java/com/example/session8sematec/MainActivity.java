package com.example.session8sematec;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.session8sematec.praymodels.PrayTimesClass;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button btnAdan;
    EditText etCity;
    TextView tvAdan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdan = findViewById(R.id.btnadan);
        etCity = findViewById(R.id.etCity);
        tvAdan = findViewById(R.id.tvAdan);


        btnAdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = etCity.getText().toString();
                final String url = "https://api.aladhan.com/v1/timingsByCity?city=" + city + "&country=Iran&method=8";
                AsyncHttpClient client = new AsyncHttpClient();
                client.get(url, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        Gson gson = new Gson();
                        PrayTimesClass pray = gson.fromJson(response.toString(), PrayTimesClass.class);
                        String m = pray.getData().getTimings().getMaghrib();
                        // Log.d("Maghrib",m);
                        tvAdan.setText(m);

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                    }
                });
            }
        });


        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.AIRPLANE_MODE");
        BroadcastReciever reciever = new BroadcastReciever();
        registerReceiver(reciever, filter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.alertDialogue:
                Intent maintoAlertDialogue = new Intent(MainActivity.this, AlertDialogueActivity.class);
                startActivity(maintoAlertDialogue);
                break;

            case R.id.toastMessage:
                Toast.makeText(this, "Kebab Menu", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    Boolean hasUserClickOnBack = false;

    @Override
    public void onBackPressed() {

        if(!hasUserClickOnBack){
            Toast.makeText(this, "Please Click Again!", Toast.LENGTH_SHORT).show();
            hasUserClickOnBack = true;


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hasUserClickOnBack = false;
                }
            },2000);
        }else{
            super.onBackPressed();
        }

    }
}
