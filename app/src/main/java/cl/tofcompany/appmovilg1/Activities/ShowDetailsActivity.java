package cl.tofcompany.appmovilg1.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import cl.tofcompany.appmovilg1.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Model.FoodModel;
import cl.tofcompany.appmovilg1.Model.FoodModelEntradas;
import cl.tofcompany.appmovilg1.databinding.ActivityShowDetailsBinding;

public class ShowDetailsActivity extends AppCompatActivity {
    ActivityShowDetailsBinding showDetailsBinding;
    private FoodModel object;
    private int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDetailsBinding =ActivityShowDetailsBinding.inflate(getLayoutInflater());
        setContentView(showDetailsBinding.getRoot());
        initUi();
    }

    private void initUi() {
        managementCart = new ManagementCart(this);
        getBundle();
    }
    private void getBundle() {
        object = (FoodModel) getIntent().getSerializableExtra("object");
        Glide.with(this)
                .load(object.getPic())
                .into(showDetailsBinding.foodPic);


        showDetailsBinding.titleTxt.setText(object.getTitle());
        showDetailsBinding.priceTxt.setText("$" + object.getFee());
        showDetailsBinding.descriptionTxt.setText(object.getDescription());
        showDetailsBinding.numberOrderTxt.setText(String.valueOf(numberOrder));
        showDetailsBinding.plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            showDetailsBinding.numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        showDetailsBinding.minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            }
            showDetailsBinding.numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        showDetailsBinding.addToCardBtn.setOnClickListener(v -> {
            object.setNumberInCard(numberOrder);
            managementCart.insertFood(object);
                startActivity(new Intent(ShowDetailsActivity.this, MainActivity.class));

        });
    }

}