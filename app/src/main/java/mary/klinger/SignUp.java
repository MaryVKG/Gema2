package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {

    //Acá están las variables
    ImageView backkBtn;
    Button next, login;
    TextView tittleText;

    TextInputLayout fullname, username, email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        //Acá se capturan
        backkBtn = findViewById(R.id.sigup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        tittleText = findViewById(R.id.signup_tittle_text);

        fullname = findViewById(R.id.completeName);
        username = findViewById(R.id.completeUser);
        email = findViewById(R.id.completeEmail);
        password = findViewById(R.id.completePassword);

        backkBtn = findViewById(R.id.sigup_back_button);
        backkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp.super.onBackPressed();
            }
        });
    }

    public void callNextSignUpScreen(View view) {

        if(!validateFullName() | !validateUsername() | !validateEmail() | !validatePassword()){
            return;
        }
        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backkBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tittleText, "transition_tittle_text");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent,options.toBundle());
        }else{
            startActivity(intent);
        }

    }


    public void callLoginScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.signup_login_button), "transitionLogin");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }



    //Acá crearemos las validaciones del usaurio

    //VALIDACIÓN DEL NOMBRE
    private boolean validateFullName(){
        String val = fullname.getEditText().getText().toString().trim();

        if(val.isEmpty()){
            fullname.setError("Este campo no puede estar vacío.");
            return false;
        }else{
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }

    }

    //VALIDACIÓN DEL USERNAME
    private boolean validateUsername(){
        String val = username.getEditText().getText().toString().trim();
        String signos = "\\A\\w{1,20}\\z";

        if(val.isEmpty()) {
            username.setError("Este campo no puede estar vacío.");
            return false;

        }else if(val.length() > 20){
            username.setError("Su nombre de usuario es muy largo.");
            return false;
        }
        else if(!val.matches(signos)) {
            username.setError("Todos los campos deben ser completados.");
            return false;

        }else{
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }

    }


    //VALIDACIÓN DEL EMAIL
    private boolean validateEmail(){
        String val = email.getEditText().getText().toString().trim();
        String signosEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            email.setError("Este campo no puede estar vacío.");
            return false;
        }
        else if(!val.matches(signosEmail)) {
            email.setError("El Email ingresado no es correcto.");
            return false;

        }else{
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }

    }

    //VALIDACIÓN DEL PASSWORD
    private boolean validatePassword() {
        String val = password.getEditText().getText().toString().trim();

        if (val.isEmpty()) {
            password.setError("Este campo no puede estar vacío.");
            return false;

        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}

