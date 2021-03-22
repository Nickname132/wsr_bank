package com.example.worldskilsbank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<com.example.worldskilsbank.CurrencyAdapter.MyViewHolder> {

    private List<Currency> currencyList;
    private LayoutInflater mInflater;
    private String amountString = "";
    public static String dollar ;
    public static String euro ;

    CurrencyAdapter(Context context, List<Currency> currencyList) {
        this.mInflater = LayoutInflater.from(context);
        this.currencyList = currencyList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.currency_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Currency currency = currencyList.get(position);
        final Currency currency1 = currencyList.get(20);
        final Currency currency2 = currencyList.get(18);



        holder.title.setText(currency.getName());
        holder.course.setText(currency.getCharCode().concat("/RUB: ").concat(currency.getValue().toString()));
        if (amountString.length() != 0) {
            BigDecimal finalAmountDecimal = new BigDecimal(amountString);
            BigDecimal course = currency.getValue();
            holder.finalAmount.setText((finalAmountDecimal.divide(course, 2, RoundingMode.HALF_EVEN).toString()));
        } else {
            dollar  = currency1.getValue().toString();
            euro  = currency2.getValue().toString();
            holder.finalAmount.setText("");
            MainActivity.dollar.setText("Доллар: " + dollar);
            MainActivity.euro.setText("Евро: " + euro);

        }
    }

    public void calculateAmount(String amountString) {
        this.amountString = amountString;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, course, finalAmount;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_currency_adapter);
            course = view.findViewById(R.id.course_currency_adapter);
            finalAmount = view.findViewById(R.id.final_amount);
        }
    }

}