package cl.tofcompany.appmovilg1.Activities;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

import cl.tofcompany.appmovilg1.Model.User;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.databinding.ActivityRegisterBinding;
import cl.tofcompany.appmovilg1.provider.AuthProvider;
import cl.tofcompany.appmovilg1.provider.UserProvider;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    ProgressDialog mDialog;
    private int dia,mes,ano;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
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
        registerBinding.btnregistrar.setOnClickListener(v->{
            validate();
        });
        registerBinding.fechanacimiento.setOnClickListener(v->{
            calendar();
        });

    }


    private void validate() {
     final String fullname = registerBinding.nombrecompleto.getText().toString();
     final String email = registerBinding.email.getText().toString();
     final String password = registerBinding.password.getText().toString();
     final String phone = registerBinding.telefono.getText().toString();
     final String fechanacimiento = registerBinding.fechanacimiento.getText().toString();

     if (TextUtils.isEmpty(fullname)){
         registerBinding.textInputLayoutnombre.setHelperText("campo requerido");
         registerBinding.textInputLayoutnombre.setHelperTextColor( ColorStateList.valueOf(ContextCompat.getColor(this, R.color.design_default_color_error)));
         return;
     }
     if (!validateonlyletter(fullname)){
         registerBinding.textInputLayoutnombre.setHelperText("solo permite letras");
         registerBinding.textInputLayoutnombre.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
         return;
     }
     if (TextUtils.isEmpty(email)){
         registerBinding.textInputLayoutemail.setHelperText("campo requerido");
         registerBinding.textInputLayoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
         return;
     }
     if (!validaremail(email)){
         registerBinding.textInputLayoutemail.setHelperText("Por favor ingrese un correo valido");
         registerBinding.textInputLayoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
         return;
     }
     if (TextUtils.isEmpty(password)){
         registerBinding.textInputLayoutpassword.setHelperText("campo requerido");
         registerBinding.textInputLayoutpassword.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
         return;
     }
     if (TextUtils.isEmpty(phone)){
         registerBinding.textInputLayouttelefono.setHelperText("campo requerido");
         registerBinding.textInputLayouttelefono.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
         return;
     }
     if (TextUtils.isEmpty(fechanacimiento)){
         registerBinding.textInputLayoutfecha.setHelperText("campo requerido");
         registerBinding.textInputLayoutfecha.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(this,R.color.design_default_color_error)));
     }else {
         createUser(fullname,email,password,phone,fechanacimiento);
     }

    }

    private void createUser(final String fullname,String email,String password,String phone,String fechanacimiento) {
            mDialog.setMessage("Loading...");
            mDialog.show();
            mDialog.setCanceledOnTouchOutside(false);
            mAuthProvider.register(email , password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String id = mAuthProvider.getId();
                    User users = new User(id,fullname,email,password, phone,fechanacimiento);
                    createFunction(users);
                        Toast.makeText(RegisterActivity.this,"Su cuenta ha sido creada exitosamente", Toast.LENGTH_LONG).show();
                        mDialog.dismiss();
                } else {
                    Toast.makeText(RegisterActivity.this , "Error" + Objects.requireNonNull(task.getException()).getMessage() , Toast.LENGTH_LONG).show();
                    mDialog.dismiss();
                }
            });

    }

    private void createFunction(User iduser) {
        mUserProvider.create(iduser).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }else {
                Toast.makeText(RegisterActivity.this, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calendar(){
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int moth, int day ) {
               int mess=moth+1;
                registerBinding.fechanacimiento.setText(day+"/"+mess+"/"+year);
            }
        }
                ,dia,mes,ano);
        datePickerDialog.show();
    }

    //Validate only letter
    public static boolean validateonlyletter (String datos){
        return datos.matches("[a-zA-Z ]*");
    }

    private boolean validaremail (String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}