package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class ForgetPassword extends AppCompatActivity {

    ImageView iconBackForgottpassword;
    TextInputLayout ingreseEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


        ingreseEmail = findViewById(R.id.introduceEmail);
        iconBackForgottpassword = findViewById(R.id.iconBackForgotPass);

        iconBackForgottpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPassword.super.onBackPressed();
            }
        });

    }

    public void goToNext(View view){
        if(!validateEmail()){
            return;
        }
        Intent intent = new Intent(getApplicationContext(), MakeSelection.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.nextBtnForgot), "transitionNextForgot");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgetPassword.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }


    //VALIDACIÓN DEL EMAIL
    private boolean validateEmail(){
        String val = ingreseEmail.getEditText().getText().toString().trim();
        String signosEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()) {
            ingreseEmail.setError("Este campo no puede estar vacío.");
            return false;
        }
        else if(!val.matches(signosEmail)) {
            ingreseEmail.setError("El Email ingresado no es correcto.");
            return false;

        }else{
            ingreseEmail.setError(null);
            ingreseEmail.setErrorEnabled(false);
            return true;
        }

    }
}