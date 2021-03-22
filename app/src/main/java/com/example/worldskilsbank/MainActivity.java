package com.example.worldskilsbank;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CustomPopUp customPopUp = new CustomPopUp();
    ImageButton values;
    ImageButton maps;
    ImageButton enter;
    static TextView dollar;
    static TextView euro;

//    @BindView(R.id.main_layout)
//    LinearLayout parent;
//
//    @BindView(R.id.progress_bar_holder)
//    FrameLayout progressBarHolder;
//
//    @BindView(R.id.amount_from)
//    EditText amountFrom;
//
//    @BindView(R.id.currency_recycler)
//    RecyclerView currencyRecycler;
//
//    CustomPopUp customPopUp = new CustomPopUp();
    private List<Currency> currencyList;
    Timer timer;

    public void getCurrency() {
        currencyList.clear();
        App.getCurrencyController().getAllCurrency().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {

                ServerResponse  serverResponse = response.body();

                for (Map.Entry<String, Currency> entry : serverResponse.getValute().entrySet()) {
                    Currency value = entry.getValue();
                    currencyList.add(value);

                }

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable throwable) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        values = (ImageButton) findViewById(R.id.values);
        maps = (ImageButton) findViewById(R.id.banks);
        enter = (ImageButton) findViewById(R.id.enterBtn);

        dollar = (TextView) findViewById(R.id.dollar);
        euro = (TextView) findViewById(R.id.euro);

        values.setOnClickListener(this);
        maps.setOnClickListener(this);
        enter.setOnClickListener(this);

        currencyList = new ArrayList<>();

        dollar.setText("Доллар: ");
        euro.setText("Евро: ");

    }






    @Override
    public void onClick(View v) {
        switch (v.getId())  {

            case R.id.values:
                Intent intent2 = new Intent(this, values.class);
                startActivity(intent2);
                break;
            case R.id.enterBtn:
                Intent intent3 = new Intent(this, enter.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }
}

