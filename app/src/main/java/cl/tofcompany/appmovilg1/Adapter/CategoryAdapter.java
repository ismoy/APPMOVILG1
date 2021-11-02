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

import cl.tofcompany.appmovilg1.Model.CategoryModel;
import cl.tofcompany.appmovilg1.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryModel> categoryModels;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cat,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.categoryname.setText(categoryModels.get(position).getTitle());
      String picUrl="";
      switch (position){
          case 0:{
            picUrl="cevicheplatoentrada";
            holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background1));
            break;
          }
          case 1:{
              picUrl="causaplatoentrada";
              holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background2));
              break;
          }
          case 2:{
              picUrl="canchoalpaloplatosegundo";
              holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background3));
              break;
          }
          case 3:{
              picUrl="carapulgrasegundoplato";
              holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background4));
              break;
          }
          case 4:{
              picUrl="patoenajiplatosegundo";
              holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background5));
              break;
          }
      }
      int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(picUrl,"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryimage);
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
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
