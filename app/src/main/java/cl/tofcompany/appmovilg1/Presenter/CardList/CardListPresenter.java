package cl.tofcompany.appmovilg1.Presenter.CardList;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cl.tofcompany.appmovilg1.Model.CardList.CardListModel;

public class CardListPresenter {
    Context context;
    CardListModel cardListModel;

    public CardListPresenter(Context context) {
        this.context = context;
        cardListModel = new CardListModel(context);
    }
    public void ShowPres(RecyclerView recyclerViewList,
                         ScrollView scrollView,
                         TextView totalFeeTxt,
                         TextView taxTxt,
                         TextView deliveryTxt,
                         TextView totalTxt,
                         TextView emptyTxt,
                         TextView pagar,
                         FloatingActionButton floatingActionButton,
                         LinearLayout homeBtn){
        try {
            cardListModel.initUi(recyclerViewList,scrollView, totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, pagar, floatingActionButton, homeBtn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
