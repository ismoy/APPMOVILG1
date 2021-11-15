package cl.tofcompany.appmovilg1.Adapter;

import android.content.Intent;
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

import cl.tofcompany.appmovilg1.Activities.MainActivity;
import cl.tofcompany.appmovilg1.Activities.ShowDetailsActivity;
import cl.tofcompany.appmovilg1.Model.FoodModel;
import cl.tofcompany.appmovilg1.Model.FoodModelEntradas;
import cl.tofcompany.appmovilg1.R;

public class CategoryPlatoEntradaAdapter extends RecyclerView.Adapter<CategoryPlatoEntradaAdapter.ViewHolder> {
    ArrayList<FoodModel> foodModelEntradas;

    public CategoryPlatoEntradaAdapter(MainActivity mainActivity, ArrayList<FoodModel> foodModelEntradas) {
        this.foodModelEntradas = foodModelEntradas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_platoentrada,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.categoryname.setText(foodModelEntradas.get(position).getTitle());
        holder.categoryfee.setText(String.valueOf(foodModelEntradas.get(position).getFee()));
        Glide.with(holder.itemView.getContext())
                .load(foodModelEntradas.get(position).getPic())
                .into(holder.categoryimage);
        holder.addbtn.setOnClickListener(v->{
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
            intent.putExtra("object",foodModelEntradas.get(position));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodModelEntradas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
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
