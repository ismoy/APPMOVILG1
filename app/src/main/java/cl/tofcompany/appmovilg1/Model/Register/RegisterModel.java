package cl.tofcompany.appmovilg1.Model.Register;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Pattern;

import cl.tofcompany.appmovilg1.Entities.User;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.UserProvider;

public class RegisterModel {
    Context context;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    ProgressDialog mDialog;
    private int dia,mes,ano;

    public RegisterModel(Context context, AuthProvider mAuthProvider, UserProvider mUserProvider, ProgressDialog mDialog) {
        this.context = context;
        this.mAuthProvider = mAuthProvider;
        this.mUserProvider = mUserProvider;
        this.mDialog = mDialog;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Register(Button button, TextInputEditText fullname, TextInputLayout layoutfullname, TextInputEditText email,
                         TextInputLayout layoutemail, TextInputEditText password, TextInputLayout layoutpassword,
                         TextInputEditText phone, TextInputLayout layoutphone, TextInputEditText date, TextInputLayout layoutdate){
       button.setOnClickListener(v->{
           valide(fullname,layoutfullname,email,layoutemail,password,layoutpassword,phone,layoutphone,date,layoutdate);

       });

       date.setOnClickListener(v->{
           calendar(date);
       });

    }

    private void valide(TextInputEditText fullname, TextInputLayout layoutfullname,
                        TextInputEditText email, TextInputLayout layoutemail,
                        TextInputEditText password, TextInputLayout layoutpassword,
                        TextInputEditText phone, TextInputLayout layoutphone,
                        TextInputEditText date, TextInputLayout layoutdate) {
        if (TextUtils.isEmpty(fullname.getText().toString())){
            layoutfullname.setHelperText("campo requerido");
            layoutfullname.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.design_default_color_error)));
            return;
        }
        if (!validateonlyletter(fullname.getText().toString())){
            layoutfullname.setHelperText("solo permite letras");
            layoutfullname.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(email.getText().toString())){
            layoutemail.setHelperText("campo requerido");
            layoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
            return;
        }
        if (!validaremail(email.getText().toString())){
            layoutemail.setHelperText("Por favor ingrese un correo valido");
            layoutemail.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(password.getText().toString())){
            layoutpassword.setHelperText("campo requerido");
            layoutpassword.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(phone.getText().toString())){
            layoutphone.setHelperText("campo requerido");
            layoutphone.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
            return;
        }
        if (TextUtils.isEmpty(date.getText().toString())){
            layoutdate.setHelperText("campo requerido");
            layoutdate.setHelperTextColor(ColorStateList.valueOf(ContextCompat.getColor(context,R.color.design_default_color_error)));
        }else {
            createUser(fullname,email,password,phone,date);
        }
    }

    private void createUser(TextInputEditText fullname, TextInputEditText email, TextInputEditText password, TextInputEditText phone, TextInputEditText date) {
       final String nombrecompleto = fullname.getText().toString();
        final String correo = email.getText().toString();
        final String pass = password.getText().toString();
        final String telefono = phone.getText().toString();
        final String fechanacimiento = date.getText().toString();
        mDialog.setMessage("Loading...");
        mDialog.show();
        mDialog.setCanceledOnTouchOutside(false);
        mAuthProvider.register(correo , pass).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                String id = mAuthProvider.getId();
                User users = new User(id,nombrecompleto,correo,pass, telefono,fechanacimiento);
                createFunction(users);
                Toast.makeText(context,"Su cuenta ha sido creada exitosamente", Toast.LENGTH_LONG).show();
                mDialog.dismiss();
            } else {
                Toast.makeText(context , "Error" + Objects.requireNonNull(task.getException()).getMessage() , Toast.LENGTH_LONG).show();
                mDialog.dismiss();
            }
        });
    }

    private void createFunction(User iduser) {
        mUserProvider.create(iduser).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }else {
                Toast.makeText(context, "Error" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void calendar(TextInputEditText fechanacimiento){
        final Calendar c = Calendar.getInstance();
        dia=c.get(Calendar.DAY_OF_MONTH);
        mes=c.get(Calendar.MONTH);
        ano=c.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int moth, int day ) {
                int mess=moth+1;
                fechanacimiento.setText(day+"/"+mess+"/"+year);
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
