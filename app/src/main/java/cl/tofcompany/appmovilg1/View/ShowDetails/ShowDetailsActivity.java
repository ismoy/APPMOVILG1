package cl.tofcompany.appmovilg1.View.ShowDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import cl.tofcompany.appmovilg1.Model.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.Presenter.ShowDetails.ShowDetailsPresenter;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.databinding.ActivityShowDetailsBinding;

public class ShowDetailsActivity extends AppCompatActivity {
    ActivityShowDetailsBinding showDetailsBinding;
     ManagementCart managementCart;
     private FoodModel object;
    private int numberOrder = 1;
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
      /*  ShowDetailsPresenter presenter = new ShowDetailsPresenter(this,managementCart);
        presenter.showdetails(showDetailsBinding.foodPic,showDetailsBinding.titleTxt,showDetailsBinding.priceTxt,
                showDetailsBinding.descriptionTxt,showDetailsBinding.numberOrderTxt,showDetailsBinding.plusBtn,
                showDetailsBinding.minusBtn,showDetailsBinding.addToCardBtn);*/
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