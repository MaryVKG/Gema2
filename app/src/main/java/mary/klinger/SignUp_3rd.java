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
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp_3rd extends AppCompatActivity {

    //Acá están las variables
    ImageView backkBtn;
    Button next, login;
    TextView tittleText;
    ScrollView scrollView;
    CountryCodePicker countryCodePicker;
    TextInputLayout phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up3rd);

        //Acá se capturan
        backkBtn = findViewById(R.id.sigup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        tittleText = findViewById(R.id.signup_tittle_text);
        countryCodePicker = findViewById(R.id.worldPhones);

        scrollView = findViewById(R.id.signUp3rdScrollView);
        phone = findViewById(R.id.signUpPhone);

        backkBtn = findViewById(R.id.sigup_back_button);
        backkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp_3rd.super.onBackPressed();
            }
        });
    }




    public void callVerififyOTPScreen(View view) {

        if(!validatePhoneNumber()){
            return;
        }

        String fullname = getIntent().getStringExtra("fullname");
        String email = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String genero = getIntent().getStringExtra("genero");
        String edad = getIntent().getStringExtra("edad");



        String getUserandPassword = phone.getEditText().getText().toString().trim();
        String phoneNumber = "+"+countryCodePicker.getFullNumber()+getUserandPassword;
        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);

        intent.putExtra("fullname",fullname);
        intent.putExtra("email", email);
        intent.putExtra("username", username);
        intent.putExtra("password", password);
        intent.putExtra("genero", genero);
        intent.putExtra("edad", edad);
        intent.putExtra("phone", phoneNumber);

        Pair[] pairs = new Pair[5];

        pairs[0] = new Pair<View, String>(backkBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tittleText, "transition_tittle_text");
        pairs[4] = new Pair<View, String>(scrollView, "transition_OTP_screen");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp_3rd.this, pairs);
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

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp_3rd.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }



    private boolean validatePhoneNumber() {
        String val = phone.getEditText().getText().toString().trim();
        String variedad = "961900294";


        if (val.isEmpty()) {
            phone.setError("Ingrese un número valido.");
            return false;

        }
        else if(!val.matches(variedad)) {
            phone.setError("El teléfono ingresado es inválido.");
            return false;

        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

}