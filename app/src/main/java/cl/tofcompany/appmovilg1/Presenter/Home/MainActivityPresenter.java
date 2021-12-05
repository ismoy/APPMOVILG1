package cl.tofcompany.appmovilg1.Presenter.Home;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cl.tofcompany.appmovilg1.Model.Home.MainActivityModel;

public class MainActivityPresenter {
    Context context;
    MainActivityModel activityModel;
    public MainActivityPresenter(Context context) {
        this.context = context;
        activityModel = new MainActivityModel(context);
    }
    public void ShowData(RecyclerView masvendido,
                         RecyclerView platoentrada,
                         RecyclerView platofondo,
                         RecyclerView postre,
                         RecyclerView bebidas,
                         FloatingActionButton floatingActionButton,
                         LinearLayout homeBtn,
                         LinearLayout logout,
                         TextView nombre,
                         TextView fech){
        try {
            activityModel.initUi(masvendido, platoentrada, platofondo, postre, bebidas, floatingActionButton, homeBtn, logout, nombre, fech);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
