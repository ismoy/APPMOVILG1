package cl.tofcompany.appmovilg1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cl.tofcompany.appmovilg1.Model.Helper.ManagementCart;
import cl.tofcompany.appmovilg1.Entities.Interface.ChangeNumberItemsListener;
import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.R;


public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodModel> foodModels;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodModel> foodModels, Context context, ChangeNumberItemsListener changeNumberItemsListener) {

        this.foodModels = foodModels;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(foodModels.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodModels.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodModels.get(position).getNumberInCard() * foodModels.get(position).getFee()) * 100.0) / 100.0));
        holder.num.setText(String.valueOf(foodModels.get(position).getNumberInCard()));
        Glide.with(holder.itemView.getContext())
                .load(foodModels.get(position).getPic())
                .into(holder.pic);

        //clic en el icono mas para agregar productos en carrito
        holder.plusItem.setOnClickListener(v -> managementCart.plusNumberFood(foodModels, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        }));

        //clic en el icono menos para disminuir productos en carrito
        holder.minusItem.setOnClickListener(v -> managementCart.MinusNumerFood(foodModels, position, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                notifyDataSetChanged();
                changeNumberItemsListener.changed();
            }
        }));

    }


    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //inicializar los variables
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //asignar los id a los variable
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            pic = itemView.findViewById(R.id.picCard);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
