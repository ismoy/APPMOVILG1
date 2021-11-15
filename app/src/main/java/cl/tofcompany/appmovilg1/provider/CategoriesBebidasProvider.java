package cl.tofcompany.appmovilg1.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesBebidasProvider {

    DatabaseReference mDatabase;

    public CategoriesBebidasProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("CategoriaBebidas");
    }

    public DatabaseReference getCategories(){
        return mDatabase;
    }
}
