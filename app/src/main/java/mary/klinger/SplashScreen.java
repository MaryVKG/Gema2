package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Network;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    //Se crean las variables
    ImageView backgroundImage;
    TextView poweredByLine;








    //Acá se crean las variables de animación
    Animation sideAnim, bottonAnim;

    SharedPreferences onBoardingScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        //Acá capturamos las variables
        backgroundImage = findViewById(R.id.backgound_image);
        poweredByLine = findViewById(R.id.powered_by_line);





        //Animaciones
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottonAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);


        //Acá se cambian las animaciones
        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottonAnim);








        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();
                    Intent intent = new Intent(getApplicationContext(), OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(),UserDashboard.class);
                    startActivity(intent);
                    finish();
                }


            }
        },SPLASH_TIMER);

    }



}




