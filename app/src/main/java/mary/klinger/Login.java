package mary.klinger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    ImageView loginBack;
    Button sigUnp, login;
    EditText iniciarSesion, contrasena;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        sigUnp = findViewById(R.id.crearCuenta);
        login = findViewById(R.id.btnIniciarSession);
        iniciarSesion = findViewById(R.id.idEmail);
        contrasena = findViewById(R.id.idPass);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correoUsuario = iniciarSesion.getText().toString().trim();
                String passUsuario = contrasena.getText().toString().trim();

                if (correoUsuario.isEmpty() && passUsuario.isEmpty()) {
                    Toast.makeText(Login.this, "Ingrese los datos", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(correoUsuario, passUsuario);
                }
            }
        });
        loginBack = findViewById(R.id.login_back_button);

        loginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login.super.onBackPressed();
            }
        });

    }

    public void goToForgetPass(View view) {
        Intent intent = new Intent(getApplicationContext(), ForgetPassword.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.forgotPassword), "transitionForgotPassword");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }


    public void callSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.crearCuenta), "transitionSignUp");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }

    }

    public void loginUser(String correoUsuario, String passUsuario) {
        firebaseAuth.signInWithEmailAndPassword(correoUsuario, passUsuario).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, UserDashboard.class));
                    Toast.makeText(Login.this, "Bienvenido a la página principal", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "No estás registrado en la App", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
