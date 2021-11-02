package cl.tofcompany.appmovilg1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

import cl.tofcompany.appmovilg1.Adapter.CategoryAdapter;
import cl.tofcompany.appmovilg1.Adapter.PlatoFondoAdapter;
import cl.tofcompany.appmovilg1.Adapter.PopularAdapter;
import cl.tofcompany.appmovilg1.Model.CategoryModel;
import cl.tofcompany.appmovilg1.Model.FoodModel;
import cl.tofcompany.appmovilg1.Model.User;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.databinding.ActivityMainBinding;
import cl.tofcompany.appmovilg1.provider.AuthProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesProvider;
import cl.tofcompany.appmovilg1.provider.UserProvider;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2,adapter3;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    CategoriesProvider mCategoriesProvider;
    private int dia,mes,ano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        initUi();
    }

    private void initUi() {
        mAuthProvider = new AuthProvider();
        mUserProvider = new UserProvider();
        mCategoriesProvider = new CategoriesProvider();
        recycleviewCategory();
        recycleviewEntrada();
        recycleviewPlatofondo();
        bottomNavigation();
        getUser();
        FechaActual();
    }

    private void getUser() {
        mUserProvider.getUsers(mAuthProvider.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    String name = user.getNombrecompleto();
                    mainBinding.textname.setText(" Bienvenido " +name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void FechaActual(){
        Calendar fecha = Calendar.getInstance();
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = fecha.get(Calendar.MONTH);
        ano = fecha.get(Calendar.YEAR);
        mainBinding.textfecha.setText(dia+"/"+mes+"/"+ano);
    }

    private void recycleviewPlatofondo() {
        GridLayoutManager GridLayoutManager = new GridLayoutManager(this, 2);
        mainBinding.recyclerView3.setLayoutManager(GridLayoutManager);
        ArrayList<FoodModel> foodlist = new ArrayList<>();
        foodlist.add(new FoodModel("CHANCHO AL PALO","canchoalpaloplatosegundo",getString(R.string.chanchoalpalo),27));
        foodlist.add(new FoodModel("CHICHARRÓN DE CONEJO","chicharondeconejoplatosegundo",getString(R.string.chicharonconejo),17));
        foodlist.add(new FoodModel("CARAPULCRA","carapulgrasegundoplato",getString(R.string.carpulcra),16));
        foodlist.add(new FoodModel("PATO EN AJI ", "patoenajiplatosegundo",getString(R.string.patoenaji),20));
        adapter3=new PlatoFondoAdapter(foodlist);
        mainBinding.recyclerView3.setAdapter(adapter3);
    }
    private void recycleviewEntrada() {
        GridLayoutManager GridLayoutManager = new GridLayoutManager(this, 2);
        mainBinding.recyclerView2.setLayoutManager(GridLayoutManager);
        ArrayList<FoodModel> foodlist = new ArrayList<>();
        foodlist.add(new FoodModel("CEBICHE","cevicheplatoentrada",getString(R.string.ceviche),5));
        foodlist.add(new FoodModel("OCOPA","ocopaplatoentrada",getString(R.string.ocopa),5));
        foodlist.add(new FoodModel("CAUSA","causaplatoentrada",getString(R.string.causa),5));
        adapter2=new PopularAdapter(foodlist);
        mainBinding.recyclerView2.setAdapter(adapter2);
    }

    private void recycleviewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mainBinding.recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<CategoryModel> categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel("CEBICHE","cevicheplatoentrada"));
        categoryList.add(new CategoryModel("CAUSA","causaplatoentrada"));
        categoryList.add(new CategoryModel("CHANCHO AL PALO","canchoalpaloplatosegundo"));
        categoryList.add(new CategoryModel("CARAPULCRA","carapulgrasegundoplato"));
        categoryList.add(new CategoryModel("PATO EN AJI","patoenajiplatosegundo"));
        adapter=new CategoryAdapter(categoryList);
        mainBinding.recyclerView.setAdapter(adapter);

    }
    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CardListActivity.class)));

        homeBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        });

        mainBinding.cerrarsession.setOnClickListener( v-> {
            logout();
        });
        mainBinding.imgcerrar.setOnClickListener(v->{
            logout();
        });
    }

    private void logout() {
        mAuthProvider.logout();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}