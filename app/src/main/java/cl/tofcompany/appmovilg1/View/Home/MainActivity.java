package cl.tofcompany.appmovilg1.View.Home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import cl.tofcompany.appmovilg1.Adapter.CategoryBebidasAdapter;
import cl.tofcompany.appmovilg1.Adapter.CategoryMasVendidoAdapter;
import cl.tofcompany.appmovilg1.Adapter.CategoryPlatoEntradaAdapter;
import cl.tofcompany.appmovilg1.Adapter.CategoryPostreAdapter;
import cl.tofcompany.appmovilg1.Adapter.PlatoFondoAdapter;
import cl.tofcompany.appmovilg1.Entities.CategoryMasVendidoModel;
import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.Entities.User;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.AuthProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.CategoriesBebidasProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.CategoriesPlatoEntradaProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.CategoriesPlatoFondoProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.CategoriesPlatoMasVendidoProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.CategoriesPostreProvider;
import cl.tofcompany.appmovilg1.Model.Firebase.provider.UserProvider;
import cl.tofcompany.appmovilg1.Presenter.Home.MainActivityPresenter;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.View.CardList.CardListActivity;
import cl.tofcompany.appmovilg1.View.Login.LoginActivity;
import cl.tofcompany.appmovilg1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        MainActivityPresenter activityPresenter = new MainActivityPresenter(this);
        activityPresenter.ShowData(
                mainBinding.masvendido, mainBinding.platoentrada,
                mainBinding.platofondo, mainBinding.postre,
                mainBinding.bebidas, mainBinding.cardBtn,
                mainBinding.homeBtn,mainBinding.cerrarsession,
                mainBinding.textname, mainBinding.textfecha);
    }


}