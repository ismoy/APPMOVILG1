package cl.tofcompany.appmovilg1.Model.CardList;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import cl.tofcompany.appmovilg1.Adapter.CartListAdapter;
import cl.tofcompany.appmovilg1.Entities.Interface.ChangeNumberItemsListener;
import cl.tofcompany.appmovilg1.Model.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Model.Helper.TinyDB;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.View.CardList.CardListActivity;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;

public class CardListModel extends CardListActivity{
    Context context;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private double tax;

    public CardListModel(Context context) {
        this.context = context;
        managementCart = new ManagementCart(context);

    }
    public void initUi(RecyclerView recyclerViewList,
                       ScrollView scrollView,
                       TextView totalFeeTxt,
                       TextView taxTxt,
                       TextView deliveryTxt,
                       TextView totalTxt,
                       TextView emptyTxt,
                       TextView pagar,
                       FloatingActionButton floatingActionButton,
                       LinearLayout homeBtn){
        initView(pagar);
        initList(recyclerViewList,scrollView, totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt);
        calculateCard(totalFeeTxt, taxTxt, deliveryTxt, totalTxt);
        bottomNavigation(floatingActionButton, homeBtn);
    }

    private void bottomNavigation(FloatingActionButton floatingActionButton,LinearLayout homeBtn) {
        floatingActionButton.setOnClickListener(v -> startActivity(new Intent(context, CardListActivity.class)));
        homeBtn.setOnClickListener(v -> startActivity(new Intent(context, MainActivity.class)));
    }
    private void initList(RecyclerView recyclerViewList,
                          ScrollView scrollView,
                          TextView totalFeeTxt,
                          TextView taxTxt,
                          TextView deliveryTxt,
                          TextView totalTxt,
                          TextView emptyTxt) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                calculateCard(totalFeeTxt,taxTxt,deliveryTxt,totalTxt);
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

    private void calculateCard( TextView totalFeeTxt,
                                TextView taxTxt,
                                TextView deliveryTxt,
                                TextView totalTxt) {
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

    private void initView(TextView pagar) {

        pagar.setOnClickListener(v->{
            finish();
            Toast.makeText(context, "Gracias por su compra", Toast.LENGTH_SHORT).show();
            TinyDB db = new TinyDB(context);
            //eliminar los articulos despues de comprar
            db.clear();
        });
    }
}
