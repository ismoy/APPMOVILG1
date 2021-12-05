package cl.tofcompany.appmovilg1.View.Register;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import cl.tofcompany.appmovilg1.Presenter.Register.RegisterPresenter;
import cl.tofcompany.appmovilg1.databinding.ActivityRegisterBinding;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.UserProvider;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding registerBinding;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    ProgressDialog mDialog;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(registerBinding.getRoot());
        initUi();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initUi() {
        mAuthProvider = new AuthProvider();
        mUserProvider = new UserProvider();
        mDialog = new ProgressDialog(this);
        RegisterPresenter presenter = new RegisterPresenter(this,mAuthProvider,mUserProvider,mDialog);
        presenter.register(registerBinding.btnregistrar,registerBinding.nombrecompleto, registerBinding.textInputLayoutnombre,registerBinding.email, registerBinding.textInputLayoutemail,
                registerBinding.password, registerBinding.textInputLayoutpassword,
                registerBinding.telefono, registerBinding.textInputLayouttelefono, registerBinding.fechanacimiento, registerBinding.textInputLayoutfecha);

    }
}