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

import cl.tofcompany.appmovilg1.View.Home.MainActivity;
import cl.tofcompany.appmovilg1.Entities.FoodModel;
import cl.tofcompany.appmovilg1.R;
import cl.tofcompany.appmovilg1.View.ShowDetails.ShowDetailsActivity;

public class PlatoFondoAdapter extends RecyclerView.Adapter<PlatoFondoAdapter.ViewHolder> {
    ArrayList<FoodModel> foodModels;

    public PlatoFondoAdapter(MainActivity mainActivity, ArrayList<FoodModel> FoodModels) {
        this.foodModels = FoodModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_platofondo,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.categoryname.setText(foodModels.get(position).getTitle());
      holder.categoryfee.setText(String.valueOf(foodModels.get(position).getFee()));
        Glide.with(holder.itemView.getContext())
                .load(foodModels.get(position).getPic())
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
    public static class ViewHolder extends RecyclerView.ViewHolder{
         TextView categoryname;
         TextView categoryfee;
         TextView addbtn;
         ImageView categoryimage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname=itemView.findViewById(R.id.titlefondo);
            categoryfee=itemView.findViewById(R.id.feefondo);
            categoryimage=itemView.findViewById(R.id.picfondo);
            addbtn =itemView.findViewById(R.id.addBtnfondo);
        }
    }
}
