package cl.tofcompany.appmovilg1.Presenter.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cl.tofcompany.appmovilg1.Model.Login.LoginModel;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;

public class LoginPresenter {
    Context context;
    AuthProvider mAuthProvider;
    ProgressDialog mDialog;
    LoginModel model;
    public LoginPresenter(Context context, AuthProvider mAuthProvider, ProgressDialog mDialog) {
        this.context = context;
        this.mAuthProvider = mAuthProvider;
        this.mDialog = mDialog;
        model = new LoginModel(context, mAuthProvider, mDialog);
    }

    public  void loginpresenter(LoginButton loginButton,Button button, TextView view, TextInputEditText email,
                                TextInputLayout layoutemail, TextInputEditText password,
                                TextInputLayout layoutpassword){
        try {
            model.Login(loginButton,button, view, email, layoutemail, password, layoutpassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
