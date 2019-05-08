package com.example.progressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity<progressBar> extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus=0;
    private TextView textView;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= findViewById(R.id.progressBar);
        textView=(TextView)findViewById(R.id.textViewpr);
        //start long running operation in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus<100){
                    progressStatus+=1;
                    //Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            textView.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });
                    try{
                        //sleep for miliseconds.
                        Thread.sleep(200);//sleep for miliseconds.
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
