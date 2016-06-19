package com.magnus.edutech.view.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.magnus.edutech.R;
import com.magnus.edutech.App.GlobalConstants;


public class PriceListScreenActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView textPackageOneSub, textPackageTwoSub, textPackageThreeSub;
    private TextView textPackageOnePrice,textPackageTwoPrice,textPackageThreePrice;
    private Button buttonBuyPackage_1,buttonBuyPackage_2,buttonBuyPackage_3;
    private Typeface typeface;
    private Context context;
    private String [] price  = {"7500","12500","17000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_pricing);

        context = this;
        typeface = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);

        init();

    }

    private void init() {
        textPackageOneSub = (TextView) findViewById(R.id.textPackageOneSub);
        textPackageOneSub.setTypeface(typeface);

        textPackageTwoSub = (TextView) findViewById(R.id.textPackageTwoSub);
        textPackageTwoSub.setTypeface(typeface);

        textPackageThreeSub = (TextView) findViewById(R.id.textPackageThreeSub);
        textPackageThreeSub.setTypeface(typeface);
        //Price
        textPackageOnePrice = (TextView) findViewById(R.id.textPackageOnePrice);
        textPackageOnePrice.setTypeface(typeface);
        textPackageTwoPrice = (TextView) findViewById(R.id.textPackageTwoPrice);
        textPackageTwoPrice.setTypeface(typeface);
        textPackageThreePrice = (TextView) findViewById(R.id.textPackageThreePrice);
        textPackageThreePrice.setTypeface(typeface);
        // pay button
        buttonBuyPackage_1 = (Button) findViewById(R.id.buttonBuyPackage_1);
        buttonBuyPackage_1.setTypeface(typeface);
        buttonBuyPackage_1.setOnClickListener(this);
        buttonBuyPackage_2 = (Button) findViewById(R.id.buttonBuyPackage_2);
        buttonBuyPackage_2.setTypeface(typeface);
        buttonBuyPackage_2.setOnClickListener(this);
        buttonBuyPackage_3 = (Button) findViewById(R.id.buttonBuyPackage_3);
        buttonBuyPackage_3.setTypeface(typeface);
        buttonBuyPackage_3.setOnClickListener(this);




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.buttonBuyPackage_1:
                  BuyUsingGateWay(price[0]);
                break;
            case R.id.buttonBuyPackage_2:
                BuyUsingGateWay(price[1]);
                break;
            case R.id.buttonBuyPackage_3:
                BuyUsingGateWay(price[2]);
                break;

        }

    }

    private void BuyUsingGateWay(String amount)
    {
         Intent intent = new Intent(context, WebViewActivity.class);
                    intent.putExtra("amount", amount);
                    context.startActivity(intent);
        finish();
    }
}
