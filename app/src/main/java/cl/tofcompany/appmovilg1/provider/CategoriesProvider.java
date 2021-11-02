package cl.tofcompany.appmovilg1.provider;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoriesProvider {

    DatabaseReference mDatabase;

    public CategoriesProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Categories");
    }

    public DatabaseReference getPrecios(){
        return mDatabase;
    }
}
