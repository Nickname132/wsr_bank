package com.example.worldskilsbank;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class values extends AppCompatActivity {

    @BindView(R.id.main_layout)
    LinearLayout parent;

    @BindView(R.id.progress_bar_holder)
    FrameLayout progressBarHolder;

    @BindView(R.id.amount_from)
    EditText amountFrom;

    @BindView(R.id.currency_recycler)
    RecyclerView currencyRecycler;

    CustomPopUp customPopUp = new CustomPopUp();
    private List<Currency> currencyList;
    Timer timer;

    private TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.values);
        ButterKnife.bind(this);

        progressBarHolder.setVisibility(View.VISIBLE);


        currencyList = new ArrayList<>();
        currencyRecycler.setLayoutManager(new LinearLayoutManager(this));
        CurrencyAdapter currencyAdapter = new CurrencyAdapter(this, currencyList);
        currencyRecycler.setAdapter(currencyAdapter);
        currencyAdapter.notifyDataSetChanged();

        // Запускаем таймер для обращения на сервер раз в минуту
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 0, 60000);

        // Следим за обновлением вводимого текста в edit text
        amountFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                currencyAdapter.calculateAmount(amountFrom.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private class RemindTask extends TimerTask {
        @Override
        public void run() {
            runOnUiThread(() -> getCurrency());
        }
    }

    public void getCurrency() {
        currencyList.clear();
        App.getCurrencyController().getAllCurrency().enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (!response.isSuccessful()) {
                    hideProgress();
                    customPopUp.showFailedPopUp(values.this, parent, "Не удалось получить курсы валют");
                    return;
                }
                ServerResponse  serverResponse = response.body();
                if (serverResponse == null) {
                    hideProgress();
                    customPopUp.showFailedPopUp(values.this, parent, "Не удалось получить курсы валют");
                    return;
                }
                for (Map.Entry<String, Currency> entry : serverResponse.getValute().entrySet()) {
                    Currency value = entry.getValue();
                    currencyList.add(value);

                }
                hideProgress();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable throwable) {
                progressBarHolder.setVisibility(View.GONE);
                customPopUp.showFailedPopUp(values.this, parent, "Не удалось получить курсы валют");
            }
        });
    }

    private void hideProgress() {
        if (progressBarHolder.getVisibility() == View.VISIBLE) {
            progressBarHolder.setVisibility(View.GONE);
        }
    }

}
