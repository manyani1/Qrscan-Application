package com.smartshopping.testrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SerialScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serial_scan);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        scannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        scannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawresult) {
        String getbars=rawresult.getText();
        if(getbars!=null) {

            Intent intent=new Intent(this,Add_details.class);
            intent.putExtra("keyqrdetails", String.valueOf(getbars));
            Toast.makeText(getApplicationContext(), getbars, Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,100);
        }
        rawresult.getBarcodeFormat().toString();
        // Prints the scan format (qrcode, pdf417 etc.)
        //Toast.makeText(getbars, "",Toast.LENGTH_SHORT).show();
        // If you would like to resume scanning, call this method below:
        scannerView.resumeCameraPreview(this);
    }

}
