package cl.tofcompany.appmovilg1.Model.ShowDetails;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.Model.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.View.ShowDetails.ShowDetailsActivity;

public class ShowDetailModel extends ShowDetailsActivity {
    Context context;
    private FoodModel object;
    private int numberOrder = 1;
    ManagementCart managementCart;

    public ShowDetailModel(Context context, ManagementCart managementCart) {
        this.context = context;
        this.managementCart = managementCart;
        managementCart = new ManagementCart(context);

    }

    public void showdetails(ImageView foodpic, TextView titleTxt, TextView priceTxt, TextView descriptionTxt,
                            TextView numberOrderTxt, ImageView plusBtn, ImageView minusBtn, TextView addToCardBtn){
        getBundle(foodpic, titleTxt, priceTxt, descriptionTxt, numberOrderTxt, plusBtn, minusBtn, addToCardBtn);

    }

    private void getBundle(ImageView foodpic, TextView titleTxt, TextView priceTxt, TextView descriptionTxt,
                           TextView numberOrderTxt, ImageView plusBtn, ImageView minusBtn, TextView addToCardBtn) {
        object = (FoodModel) getIntent().getSerializableExtra("object");
        Glide.with(context)
                .load(object.getPic())
                .into(foodpic);
        titleTxt.setText(object.getTitle());
        priceTxt.setText("$" + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));
        plusBtn.setOnClickListener(v -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(v -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        addToCardBtn.setOnClickListener(v -> {
            object.setNumberInCard(numberOrder);
            managementCart.insertFood(object);
            context.startActivity(new Intent(context, MainActivity.class));

        });
    }

}
