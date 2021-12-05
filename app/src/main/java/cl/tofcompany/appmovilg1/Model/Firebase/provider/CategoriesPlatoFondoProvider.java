package cl.tofcompany.appmovilg1.Model.Firebase.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesPlatoFondoProvider {

    DatabaseReference mDatabase;

    public CategoriesPlatoFondoProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CategoriaPlatoFondo");
    }

    public DatabaseReference getCategories(){
        return mDatabase;
    }
}
