package cl.tofcompany.appmovilg1.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Pattern;

import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.databinding.ActivityLoginBinding;
import cl.tofcompany.appmovilg1.provider.AuthProvider;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding loginBinding;
    AuthProvider mAuthProvider;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());

        initUi();
    }

    private void initUi() {
        mAuthProvider = new AuthProvider();
        mDialog = new ProgressDialog(this);
        loginBinding.createaccount.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        loginBinding.btnlogin.setOnClickListener(view -> {
            validate();
        });
    }

    private void validate() {
        final String email = loginBinding.emaillogin.getText().toString();
        final String password = loginBinding.passwordlogin.getText().toString();
        if (TextUtils.isEmpty(email)){
            loginBinding.textInputLayoutemaillogin.setHelperText("campo requerido");
            loginBinding.textInputLayoutemaillogin.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.design_default_color_error)));
            return;
        }
        if (!validaremail(email)){
            loginBinding.textInputLayoutemaillogin.setHelperText("Por favor ingrese un correo valido");
            loginBinding.textInputLayoutemaillogin.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(password)){
            loginBinding.textInputLayoutpasswordlogin.setHelperText("campo requerido");
            loginBinding.textInputLayoutpasswordlogin.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
        }else {
            loginMethode(email,password);
        }
    }

    private void loginMethode(String email,String password) {
        mDialog.setMessage("Loading...");
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(false);
        mAuthProvider.login(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    mDialog.dismiss();
                }else {
                    Toast.makeText(LoginActivity.this, "No ha podido ingresar revise si sus datos son correctos", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
            }
        });
    }

    private boolean validaremail (String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}