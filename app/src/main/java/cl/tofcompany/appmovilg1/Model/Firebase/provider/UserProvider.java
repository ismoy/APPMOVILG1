package cl.tofcompany.appmovilg1.Model.Firebase.provider;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cl.tofcompany.appmovilg1.Entities.User;

public class UserProvider {

    DatabaseReference mDatabase;

    public UserProvider() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("Clientes");

    }


    public Task<Void> create(User user) {
        return mDatabase.child(user.getId()).setValue(user);
    }

    public DatabaseReference getUsers (String idUser) {
        return mDatabase.child(idUser);
    }


}
