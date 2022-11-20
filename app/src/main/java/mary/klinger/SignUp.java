package mary.klinger;

import static android.net.wifi.WifiConfiguration.Status.strings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity  {
    //Acá están las variables


    Button buttonRegister;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        buttonRegister = (Button) findViewById(R.id.buttonRegistro);
        progressBar = (ProgressBar) findViewById(R.id.progressBarRegister);

        progressBar.setMax(19);

        AsyncTask tareaPesada= new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                for(int i=0; i<20;i++){
                    publishProgress(i);
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException exception){
                        exception.printStackTrace();
                    }

                }
                return  null;
            }

            @Override
            protected void onProgressUpdate(Object[] values) {
                super.onProgressUpdate(values);

                ProgressBar progressBar = ((ProgressBar) findViewById(R.id.progressBarRegister));
                progressBar.setProgress((Integer) values[0]);
            }
        };
        tareaPesada.execute();

    }




}

