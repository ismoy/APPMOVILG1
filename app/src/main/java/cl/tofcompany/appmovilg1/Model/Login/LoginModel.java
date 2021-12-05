package cl.tofcompany.appmovilg1.Model.Login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.regex.Pattern;

import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.View.Login.LoginActivity;
import cl.tofcompany.appmovilg1.View.Register.RegisterActivity;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;

public class LoginModel extends LoginActivity {
    Context context;
    AuthProvider mAuthProvider;
    ProgressDialog mDialog;
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    public LoginModel(Context context, AuthProvider mAuthProvider, ProgressDialog mDialog) {
        this.context = context;
        this.mAuthProvider = mAuthProvider;
        this.mDialog = mDialog;
        callbackManager = CallbackManager.Factory.create();
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login(LoginButton loginButton, Button button, TextView view, TextInputEditText email, TextInputLayout layoutemail, TextInputEditText password, TextInputLayout layoutpassword) {

        button.setOnClickListener(v -> {
            validate(email, layoutemail, password, layoutpassword);
        });
        view.setOnClickListener(v -> {
            context.startActivity(new Intent(context, RegisterActivity.class));
        });
        loginButton.setOnClickListener(v -> {
            IniciarSession();
        });
    }


    private void validate(TextInputEditText email, TextInputLayout layoutemail, TextInputEditText password, TextInputLayout layoutpassword) {
        final String correo = email.getText().toString();
        final String pass = password.getText().toString();
        if (TextUtils.isEmpty(correo)) {
            layoutemail.setHelperText("campo requerido");
            layoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.design_default_color_error)));
            return;
        }
        if (!validaremail(correo)) {
            layoutemail.setHelperText("Por favor ingrese un correo valido");
            layoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            layoutpassword.setHelperText("campo requerido");
            layoutpassword.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.design_default_color_error)));
        } else {
            loginMethode(correo, pass);
        }
    }

    private void loginMethode(String email, String password) {
        mDialog.setMessage("Loading...");
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.setCanceledOnTouchOutside(false);
        mAuthProvider.login(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    context.startActivity(new Intent(context, MainActivity.class));
                    mDialog.dismiss();
                } else {
                    Toast.makeText(context, "No ha podido ingresar revise si sus datos son correctos", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }

    private void IniciarSession() {
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("email", "public_profile"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            context.startActivity(new Intent(context, MainActivity.class));
                        } else {
                            Toast.makeText(context, "No ha podido iniciar session con facebook", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private boolean validaremail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
