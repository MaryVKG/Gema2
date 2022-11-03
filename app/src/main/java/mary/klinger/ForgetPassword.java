package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class ForgetPassword extends AppCompatActivity {

    ImageView iconBackForgottpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        iconBackForgottpassword = findViewById(R.id.iconBackForgotPass);

        iconBackForgottpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPassword.super.onBackPressed();
            }
        });

    }

    public void goToNext(View view){
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
}