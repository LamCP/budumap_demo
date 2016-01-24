package com.marlinl.android.budumap.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.trace.LBSTraceClient;
import com.baidu.trace.OnStartTraceListener;
import com.baidu.trace.OnStopTraceListener;
import com.baidu.trace.Trace;
import com.marlinl.android.budumap.R;

/**
 * Created by MarlinL on 1/23/16.
 */
public class MainActivity extends Activity {

    private static final String TAG = "Trace";
    private static final String TYPE_0 = "string";
    private static final String TYPE_1 = "int";
    private static final String TYPE_2 = "byte";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button startBtn = (Button)findViewById(R.id.start_btn);
        Button closeBtn = (Button)findViewById(R.id.close_btn);

        final LBSTraceClient client = new LBSTraceClient(getApplicationContext());
        long serviceId = 109388;
        String entryName = "test";
        int tracType = 2;
        final Trace trace = new Trace(getApplicationContext(),serviceId,entryName,tracType);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final OnStartTraceListener onStartTraceListener = new OnStartTraceListener() {
                    @Override
                    public void onTraceCallback(int i, String s) {
                        Log.i(TYPE_1,i+"");
                        Log.i(TYPE_0,s);
                    }

                    @Override
                    public void onTracePushCallback(byte b, String s) {
                        Log.i(TYPE_2,b+"");
                        Log.i(TYPE_0,s);
                    }
                };
                client.startTrace(trace,onStartTraceListener);
            }
        });
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final OnStopTraceListener onStopTraceListener = new OnStopTraceListener() {
                    @Override
                    public void onStopTraceSuccess() {
                        Log.i(TAG,"Stop");
                    }

                    @Override
                    public void onStopTraceFailed(int i, String s) {
                        Log.i(TYPE_1,i+"");
                        Log.i(TYPE_0,s);
                    }
                };
                client.stopTrace(trace,onStopTraceListener);
            }
        });
    }



}
