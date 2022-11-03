package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {

    //Acá están las variables
    ImageView backkBtn;
    Button next, login;
    TextView tittleText;

    RadioGroup genero;
    RadioButton selectedGender;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);

        //Acá se capturan
        backkBtn = findViewById(R.id.sigup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        tittleText = findViewById(R.id.signup_tittle_text);

        genero = findViewById(R.id.generoSelection);
        datePicker = findViewById(R.id.nacimiento);


        backkBtn = findViewById(R.id.sigup_back_button);
        backkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp2ndClass.super.onBackPressed();
            }
        });
    }

    public void callNextSignUpScreen(View view) {

        if(!validateGender() | !validateAge()){
            return;
        }

        selectedGender = findViewById(genero.getCheckedRadioButtonId());
        String gender  = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String date = day+"/"+month+"/"+year;

        Intent intent = new Intent(getApplicationContext(), SignUp_3rd.class);

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View, String>(backkBtn, "transition_back_arrow_btn");
        pairs[1] = new Pair<View, String>(next, "transition_next_btn");
        pairs[2] = new Pair<View, String>(login, "transition_login_btn");
        pairs[3] = new Pair<View, String>(tittleText, "transition_tittle_text");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
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

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }

    //VALIDACIONES


    private boolean validateGender() {
        if (genero.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Por favor seleccione el género", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;
        if (isAgeValid < 14) {
            Toast.makeText(this, "No cuentas con suficiente edad para registrarte.", Toast.LENGTH_SHORT).show();
            return false;
        } else
            return true;
    }

}