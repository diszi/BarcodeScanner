package com.example.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button scanBtn;
    TextView content,format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = findViewById(R.id.button);
        content = (TextView) findViewById(R.id.content);
        format = (TextView) findViewById(R.id.format);


        scanBtn.setOnClickListener(this);


    }

    public void onClick(View view) {
            if (view.getId() == R.id.button) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(this);
                intentIntegrator.initiateScan();

            }
        }



    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if (scanningResult != null){

            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
           format.setText("FORMAT = "+scanFormat);
            content.setText("CONTENT = "+scanContent);


        }else{
            Toast.makeText(getApplicationContext(),"No scan data received!",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
