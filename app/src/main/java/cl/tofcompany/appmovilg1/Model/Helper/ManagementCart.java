package cl.tofcompany.appmovilg1.Model.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import cl.tofcompany.appmovilg1.Entities.Interface.ChangeNumberItemsListener;
import cl.tofcompany.appmovilg1.Entities.FoodModel;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodModel item) {
        ArrayList<FoodModel> listFood = getListCard();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCard(item.getNumberInCard());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added To Your Card", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FoodModel> getListCard() {
        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<FoodModel> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listfood.get(position).setNumberInCard(listfood.get(position).getNumberInCard() + 1);
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }



    public void MinusNumerFood(ArrayList<FoodModel> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCard() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCard(listfood.get(position).getNumberInCard() - 1);
        }
        tinyDB.putListObject("CardList", listfood);
        changeNumberItemsListener.changed();
    }


    public Double getTotalFee() {
        ArrayList<FoodModel> listFood2 = getListCard();
        double fee = 0;
        for (int i = 0; i < listFood2.size(); i++) {
            fee = fee + (listFood2.get(i).getFee() * listFood2.get(i).getNumberInCard());
        }
        return fee;
    }

}
