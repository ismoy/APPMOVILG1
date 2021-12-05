package cl.tofcompany.appmovilg1.Presenter.Register;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import cl.tofcompany.appmovilg1.Model.Register.RegisterModel;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.UserProvider;

public class RegisterPresenter {
    Context context;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    ProgressDialog mDialog;
    RegisterModel model ;

    public RegisterPresenter(Context context, AuthProvider mAuthProvider, UserProvider mUserProvider, ProgressDialog mDialog) {
        this.context = context;
        this.mAuthProvider = mAuthProvider;
        this.mUserProvider = mUserProvider;
        this.mDialog = mDialog;
        model = new RegisterModel(context, mAuthProvider, mUserProvider, mDialog);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void register(Button button, TextInputEditText fullname, TextInputLayout layoutfullname, TextInputEditText email,
                          TextInputLayout layoutemail, TextInputEditText password, TextInputLayout layoutpassword,
                          TextInputEditText phone, TextInputLayout layoutphone, TextInputEditText date, TextInputLayout layoutdate){
        try {
            model.Register(button, fullname, layoutfullname, email, layoutemail, password, layoutpassword, phone, layoutphone, date, layoutdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
