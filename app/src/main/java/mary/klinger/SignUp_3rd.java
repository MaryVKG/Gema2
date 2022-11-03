package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp_3rd extends AppCompatActivity {

    //Acá están las variables
    ImageView backkBtn;
    Button next, login;
    TextView tittleText;
    ScrollView scrollView;

    TextInputLayout phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd);

        //Acá se capturan
        backkBtn = findViewById(R.id.sigup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        tittleText = findViewById(R.id.signup_tittle_text);

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

    public void callNextSignUpScreen(View view) {

        if(!validatePhoneNumber()){
            return;
        }

        String fullname = getIntent().getStringExtra("fullname");
        String email = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String genero = getIntent().getStringExtra("genero");
        String edad = getIntent().getStringExtra("edad");



        Intent intent = new Intent(getApplicationContext(), VerifyCode.class);

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backkBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tittleText, "transition_tittle_text");


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
        String checkspaces = "{1,9}";
        if (val.isEmpty()) {
            phone.setError("Ingrese un número valido.");
            return false;
        } else if (!val.matches(checkspaces)) {
            phone.setError("No se permiten espacios en blanco.");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }

}