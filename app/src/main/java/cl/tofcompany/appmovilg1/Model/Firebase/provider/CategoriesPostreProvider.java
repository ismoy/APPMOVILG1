package cl.tofcompany.appmovilg1.Model.Firebase.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesPostreProvider {

    DatabaseReference mDatabase;

    public CategoriesPostreProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CategoriaPostre");
    }

    public DatabaseReference getCategories(){
        return mDatabase;
    }
}
