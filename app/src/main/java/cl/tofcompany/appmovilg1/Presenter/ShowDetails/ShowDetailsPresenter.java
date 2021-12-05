package cl.tofcompany.appmovilg1.Presenter.ShowDetails;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.Model.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Model.ShowDetails.ShowDetailModel;

public class ShowDetailsPresenter {
    Context context;
    private FoodModel object;
    ManagementCart managementCart;
    ShowDetailModel  detailModel ;

    public ShowDetailsPresenter(Context context , ManagementCart managementCart) {
        this.context = context;
        this.managementCart = managementCart;
    }

    public void showdetails(ImageView foodpic, TextView titleTxt, TextView priceTxt, TextView descriptionTxt,
                            TextView numberOrderTxt, ImageView plusBtn, ImageView minusBtn, TextView addToCardBtn){
        try {
            detailModel.showdetails(foodpic, titleTxt, priceTxt, descriptionTxt, numberOrderTxt, plusBtn, minusBtn, addToCardBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
