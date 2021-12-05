package cl.tofcompany.appmovilg1.Model.Firebase.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesPlatoMasVendidoProvider {

    DatabaseReference mDatabase;

    public CategoriesPlatoMasVendidoProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CategoriaMasVendidos");
    }

    public DatabaseReference getCategories(){
        return mDatabase;
    }
}
