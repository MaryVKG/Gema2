package mary.klinger;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class ForgetPasswordSuccesMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_succes_message);
    }


    public void goToLogin(View view){
        Intent intent = new Intent(getApplicationContext(), Login.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.goLogin), "transitionLogin");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ForgetPasswordSuccesMessage.this, pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }

    }




}
