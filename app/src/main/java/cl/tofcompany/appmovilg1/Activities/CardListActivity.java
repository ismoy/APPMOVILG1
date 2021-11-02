package cl.tofcompany.appmovilg1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cl.tofcompany.appmovilg1.Adapter.CartListAdapter;
import cl.tofcompany.appmovilg1.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Helper.TinyDB;
import cl.tofcompany.appmovilg1.Interface.ChangeNumberItemsListener;
import cl.tofcompany.appmovilg1.R;

public class CardListActivity extends AppCompatActivity {

    //inicializar los variables
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt,pagar;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);
        managementCart = new ManagementCart(this);
        //asignar id a las vistas
        initView();
        //asignar valor en la lista
        initList();
        //calcular el precio
        calculateCard();
        //bottom para dar click sobre los iconos abajo
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
          //clic en el boton carrito para ir al metodo de pago
        floatingActionButton.setOnClickListener(v -> startActivity(new Intent(CardListActivity.this, CardListActivity.class)));

        //clic sobre el icono home para ir al inicio
        homeBtn.setOnClickListener(v -> startActivity(new Intent(CardListActivity.this, MainActivity.class)));
    }
    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCard().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {
        double percentTax = 0.00;
        double delivery = 0;

        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("Sol " + itemTotal);
        taxTxt.setText("Sol " + tax);
        deliveryTxt.setText("Sol " + delivery);
        totalTxt.setText("Sol " + total);
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        pagar = findViewById(R.id.pagar);

        pagar.setOnClickListener(v->{
            Toast.makeText(CardListActivity.this, "Escanea el codigo QR para pagar y ingrese el monto", Toast.LENGTH_SHORT).show();
            finish();
            TinyDB db = new TinyDB(this);
            //eliminar los articulos despues de comprar
            db.clear();
        });
    }
}