package cl.tofcompany.appmovilg1.Model.Home;

import android.content.Context;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.View.CardList.CardListActivity;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.View.Login.LoginActivity;

public class MainActivityModel {
    Context context;
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

    public MainActivityModel(Context context) {
        this.context = context;
        mAuthProvider = new AuthProvider();
        mUserProvider = new UserProvider();
        mCategoriesPlatoFondoProvider = new CategoriesPlatoFondoProvider();
        mCategoriesPlatoEntradaProvider = new CategoriesPlatoEntradaProvider();
        masVendidoProvider = new CategoriesPlatoMasVendidoProvider();
        mBebidasProvider = new CategoriesBebidasProvider();
        mPostreProvider = new CategoriesPostreProvider();
    }

    public void  initUi(RecyclerView masvendido,
                        RecyclerView platoentrada,
                        RecyclerView platofondo,
                        RecyclerView postre,
                        RecyclerView bebidas,
                        FloatingActionButton floatingActionButton,
                        LinearLayout homeBtn,
                        LinearLayout logout,
                        TextView nombre,
                        TextView fech) {
        recycleviewCategory(masvendido);
        recycleviewEntrada(platoentrada);
        recycleviewPlatofondo(platofondo);
        recycleviewPostre(postre);
        recycleviewBebidas(bebidas);
        bottomNavigation(floatingActionButton,homeBtn,logout);
        getUser(nombre);
        FechaActual(fech);

    }




    private void recycleviewBebidas(RecyclerView bebidas) {getCategoriesBebidas(bebidas); }
    private void recycleviewPlatofondo(RecyclerView platofondo) {
        getPlatoFondo(platofondo);
    }
    private void recycleviewEntrada(RecyclerView platoentrada) {
        getPlatoEntrada(platoentrada);
    }
    private void recycleviewCategory(RecyclerView masvendido) {
        getCategoryMasVendidos(masvendido);
    }
    private void recycleviewPostre(RecyclerView postre) { getCategoryCategoryPostre(postre);}

    private void bottomNavigation(FloatingActionButton floatingActionButton, LinearLayout homeBtn,LinearLayout logout) {
        floatingActionButton.setOnClickListener(v -> context.startActivity (new Intent(context, CardListActivity.class)));

        homeBtn.setOnClickListener(v -> {
            context.startActivity(new Intent(context, MainActivity.class));
        });

        logout.setOnClickListener( v-> {
            logout(logout);
        });
    }

    private void logout(LinearLayout logout) {
        mAuthProvider.logout();
        context.startActivity(new Intent(context, LoginActivity.class));
    }
    private void getUser(TextView nombre) {
        mUserProvider.getUsers(mAuthProvider.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    String name = user.getNombrecompleto();
                    nombre.setText(" Bienvenido " +name);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void FechaActual(TextView fech){
        Calendar fecha = Calendar.getInstance();
        dia = fecha.get(Calendar.DAY_OF_MONTH);
        mes = fecha.get(Calendar.MONTH);
        ano = fecha.get(Calendar.YEAR);
        fech.setText(dia+"/"+mes+"/"+ano);
    }
    private void getPlatoFondo(RecyclerView platofondo){
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
                    mPlatoFondoAdapter=new PlatoFondoAdapter((MainActivity) context,foodlistfundo);
                    platofondo.setLayoutManager(new GridLayoutManager(context,2));
                    platofondo.setAdapter(mPlatoFondoAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getPlatoEntrada(RecyclerView platoentrada) {
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
                    mEntradaAdapter=new CategoryPlatoEntradaAdapter((MainActivity) context,foodlist);
                    platoentrada.setLayoutManager(new GridLayoutManager(context,2));
                    platoentrada.setAdapter(mEntradaAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getCategoryMasVendidos(RecyclerView masvendido) {
        masVendidoProvider.getCategories().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds:snapshot.getChildren()){
                        String title = ds.child("title").getValue().toString();
                        String pic = ds.child("pic").getValue().toString();
                        categoryList.add(new CategoryMasVendidoModel(title,pic));
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                    masvendido.setLayoutManager(linearLayoutManager);
                    masVendidoAdapter=new CategoryMasVendidoAdapter((MainActivity) context,categoryList);
                    masvendido.setAdapter(masVendidoAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void getCategoriesBebidas(RecyclerView bebidas) {
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
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                    bebidas.setLayoutManager(gridLayoutManager);
                    mBebidasAdapter=new CategoryBebidasAdapter((MainActivity) context,foodlistbebidas);
                    bebidas.setAdapter(mBebidasAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void getCategoryCategoryPostre(RecyclerView postre) {
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
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
                    postre.setLayoutManager(gridLayoutManager);
                    mPostreAdapter=new CategoryPostreAdapter((MainActivity) context,foodlistpostre);
                    postre.setAdapter(mPostreAdapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

}
