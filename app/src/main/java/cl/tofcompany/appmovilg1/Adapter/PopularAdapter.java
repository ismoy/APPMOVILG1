package cl.tofcompany.appmovilg1.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cl.tofcompany.appmovilg1.Model.FoodModel;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.Activities.ShowDetailsActivity;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<FoodModel> foodModels;

    public PopularAdapter(ArrayList<FoodModel> FoodModels) {
        this.foodModels = FoodModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.categoryname.setText(foodModels.get(position).getTitle());
      holder.categoryfee.setText(String.valueOf(foodModels.get(position).getFee()));
      int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(foodModels.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.categoryimage);
        holder.addbtn.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
            intent.putExtra("object",foodModels.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
         TextView categoryname;
         TextView categoryfee;
         TextView addbtn;
         ImageView categoryimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname=itemView.findViewById(R.id.title);
            categoryfee=itemView.findViewById(R.id.fee);
            categoryimage=itemView.findViewById(R.id.pic);
            addbtn =itemView.findViewById(R.id.addBtn);
        }
    }
}
