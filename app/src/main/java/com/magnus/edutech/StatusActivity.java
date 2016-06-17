package com.magnus.edutech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.magnus.edutech.BackgroundService.LoadDataInBackGround;
import com.magnus.edutech.webservices.GlobalConstants;


public class StatusActivity extends ActionBarActivity {

    private LoadDataInBackGround loadDataInBackGround;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        String status = "Status Not Known!";
        try
        {
            Intent mainIntent = getIntent();
            String html = mainIntent.getStringExtra("transStatus");
            // process the html as needed by the app

            if(html.indexOf("Failure")!=-1){
                status = "Transaction Declined!";
            }else if(html.indexOf("Success")!=-1){
                status = "Transaction Successful!";
                String amount = mainIntent.getStringExtra(GlobalConstants.AMOUNT);
                loadDataInBackGround.updateStatusToServerAfterPayment(this,amount);
            }else if(html.indexOf("Aborted")!=-1){
                status = "Transaction Cancelled!";
            }else{
                status = "Status Not Known!";
            }
        }
        catch (Exception ex)
        {

        }


        TextView tv4 = (TextView) findViewById(R.id.textView1);
        tv4.setText(status);
    }

    
    public void showToast(String msg) {
		Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
	}


}
