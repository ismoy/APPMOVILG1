package cl.tofcompany.appmovilg1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

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
import cl.tofcompany.appmovilg1.Model.CategoryMasVendidoModel;
import cl.tofcompany.appmovilg1.Model.FoodModel;
import cl.tofcompany.appmovilg1.Model.FoodModelEntradas;
import cl.tofcompany.appmovilg1.Model.User;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.databinding.ActivityMainBinding;
import cl.tofcompany.appmovilg1.provider.AuthProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesBebidasProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesPlatoEntradaProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesPlatoFondoProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesPlatoMasVendidoProvider;
import cl.tofcompany.appmovilg1.provider.CategoriesPostreProvider;
import cl.tofcompany.appmovilg1.provider.UserProvider;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;
    AuthProvider mAuthProvider;
    UserProvider mUserProvider;
    CategoriesPlatoFondoProvider mCategoriesPlatoFondoProvider;
    CategoriesPlatoEntradaProvider mCategoriesPlatoEntradaProvider;
    private PlatoFondoAdapter mPlatoFondoAdapter;
    private CategoryPlatoEntradaAdapter mEntradaAdapter;
    CategoriesPlatoMasVendidoProvider masVendidoProvider;
    CategoryMasVendidoAdapter masVendidoAdapter;
    CategoriesBebidasProvider mBebidasProvider;
    CategoryBebidasAdapter mBebidasAdapter;
    CategoriesPostreProvider mPostreProvider;
    CategoryPostreAdapter mPostreAdapter;

    private int dia,mes,ano;
    ArrayList<FoodModel> foodlist = new ArrayList<>();
    ArrayList<FoodModel> foodlistfundo = new ArrayList<>();
    ArrayList<FoodModel> foodlistbebidas = new ArrayList<>();
    ArrayList<FoodModel> foodlistpostre = new ArrayList<>();
    ArrayList<CategoryMasVendidoModel> categoryList = new ArrayList<>();

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
        mCategoriesPlatoFondoProvider = new CategoriesPlatoFondoProvider();
        mCategoriesPlatoEntradaProvider = new CategoriesPlatoEntradaProvider();
        masVendidoProvider = new CategoriesPlatoMasVendidoProvider();
        mBebidasProvider = new CategoriesBebidasProvider();
        mPostreProvider = new CategoriesPostreProvider();
        recycleviewCategory();
        recycleviewEntrada();
        recycleviewPlatofondo();
        recycleviewPostre();
        recycleviewBebidas();
        bottomNavigation();
        getUser();
        FechaActual();
    }

    private void recycleviewBebidas() {getCategoriesBebidas(); }
    private void recycleviewPlatofondo() {
    getPlatoFondo();
    }
    private void recycleviewEntrada() {
       getPlatoEntrada();
    }
    private void recycleviewCategory() {
        getCategoryMasVendidos();
    }
    private void recycleviewPostre() { getCategoryCategoryPostre();}




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
    private void getPlatoFondo(){
        mCategoriesPlatoFondoProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String description = ds.child("description").getValue().toString();
                        String fee = ds.child("fee").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        foodlistfundo.add(new FoodModel(title,pic,description,Integer.parseInt(fee)));
                    }
                    mPlatoFondoAdapter=new PlatoFondoAdapter(MainActivity.this,foodlistfundo);
                    mainBinding.platofondo.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    mainBinding.platofondo.setAdapter(mPlatoFondoAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getPlatoEntrada() {
        mCategoriesPlatoEntradaProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String description = ds.child("description").getValue().toString();
                        String fee = ds.child("fee").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        foodlist.add(new FoodModel(title,pic,description,Integer.parseInt(fee)));
                    }
                    mEntradaAdapter=new CategoryPlatoEntradaAdapter(MainActivity.this,foodlist);
                    mainBinding.platoentrada.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                    mainBinding.platoentrada.setAdapter(mEntradaAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getCategoryMasVendidos() {
        masVendidoProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        categoryList.add(new CategoryMasVendidoModel(title,pic));
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                    mainBinding.masvendido.setLayoutManager(linearLayoutManager);
                    masVendidoAdapter=new CategoryMasVendidoAdapter(MainActivity.this,categoryList);
                    mainBinding.masvendido.setAdapter(masVendidoAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getCategoriesBebidas() {
        mBebidasProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        String description = ds.child("description").getValue().toString();
                        String fee = ds.child("fee").getValue().toString();
                        foodlistbebidas.add(new FoodModel(title,pic,description,Integer.parseInt(fee)));
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    mainBinding.bebidas.setLayoutManager(gridLayoutManager);
                    mBebidasAdapter=new CategoryBebidasAdapter(MainActivity.this,foodlistbebidas);
                    mainBinding.bebidas.setAdapter(mBebidasAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getCategoryCategoryPostre() {
        mPostreProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        String description = ds.child("description").getValue().toString();
                        String fee = ds.child("fee").getValue().toString();
                        foodlistpostre.add(new FoodModel(title,pic,description,Integer.parseInt(fee)));
                    }
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2);
                    mainBinding.postre.setLayoutManager(gridLayoutManager);
                    mPostreAdapter=new CategoryPostreAdapter(MainActivity.this,foodlistpostre);
                    mainBinding.postre.setAdapter(mPostreAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}