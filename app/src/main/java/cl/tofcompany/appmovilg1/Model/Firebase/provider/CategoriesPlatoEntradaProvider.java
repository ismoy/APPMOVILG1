package cl.tofcompany.appmovilg1.Model.Firebase.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesPlatoEntradaProvider {

    DatabaseReference mDatabase;

    public CategoriesPlatoEntradaProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CategoriaPlatoEntrada");
    }

    public DatabaseReference getCategories(){
        return mDatabase;
    }
}
