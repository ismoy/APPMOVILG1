package cl.tofcompany.appmovilg1.View.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import cl.tofcompany.appmovilg1.View.Login.LoginActivity;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;

public class SplashActivity extends AppCompatActivity {
  AuthProvider mAuthProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuthProvider = new AuthProvider();
        //duration splash screen
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (mAuthProvider.existSession()){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

            }
        } , 1000);
    }
}