package cl.tofcompany.appmovilg1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.Entities.CategoryMasVendidoModel;
import cl.tofcompany.appmovilg1.R;

public class CategoryMasVendidoAdapter extends RecyclerView.Adapter<CategoryMasVendidoAdapter.ViewHolder> {
    ArrayList<CategoryMasVendidoModel> masVendidoModels;

    public CategoryMasVendidoAdapter(MainActivity mainActivity, ArrayList<CategoryMasVendidoModel> masVendidoModels) {
        this.masVendidoModels = masVendidoModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_masvendido,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.categoryname.setText(masVendidoModels.get(position).getTitle());

        switch (position){
            case 0:{
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
                Glide.with(holder.itemView.getContext())
                        .load(masVendidoModels.get(position).getPic())
                        .into(holder.categoryimage);
                break;
            }
            case 1:{
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background2));
                Glide.with(holder.itemView.getContext())
                        .load(masVendidoModels.get(position).getPic())
                        .into(holder.categoryimage);
                break;
            }
            case 2:{
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background3));
                break;
            }
            case 3:{
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background4));
                Glide.with(holder.itemView.getContext())
                        .load(masVendidoModels.get(position).getPic())
                        .into(holder.categoryimage);
                break;
            }
            case 4:{
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background5));
                Glide.with(holder.itemView.getContext())
                        .load(masVendidoModels.get(position).getPic())
                        .into(holder.categoryimage);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return masVendidoModels.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView categoryname;
         ImageView categoryimage;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname=itemView.findViewById(R.id.categoryName);
            categoryimage=itemView.findViewById(R.id.categoryPic);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
