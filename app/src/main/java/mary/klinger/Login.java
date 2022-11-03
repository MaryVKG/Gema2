package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Login extends AppCompatActivity {

    ImageView loginBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        loginBack = findViewById(R.id.login_back_button);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.super.onBackPressed();
            }
        });

    }

    public void goToForgetPass(View view){
        Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.forgotPassword), "transitionForgotPassword");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }


    public void callSignUpScreen(View view){
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.crearCuenta), "transitionSignUp");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }



    }


}
