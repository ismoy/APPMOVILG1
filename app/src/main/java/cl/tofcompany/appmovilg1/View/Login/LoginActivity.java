package cl.tofcompany.appmovilg1.View.Login;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import cl.tofcompany.appmovilg1.Presenter.Login.LoginPresenter;
import cl.tofcompany.appmovilg1.databinding.ActivityLoginBinding;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;

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
        LoginPresenter  presenter = new LoginPresenter(this,mAuthProvider,mDialog);
        presenter.loginpresenter(loginBinding.loginButton,loginBinding.btnlogin, loginBinding.createaccount, loginBinding.emaillogin, loginBinding.textInputLayoutemaillogin,
                loginBinding.passwordlogin, loginBinding.textInputLayoutpasswordlogin);

    }
}