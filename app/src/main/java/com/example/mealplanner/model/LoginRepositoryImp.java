package com.example.mealplanner.model;

import android.widget.Toast;

import com.example.mealplanner.network.NetworkCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRepositoryImp implements LoginRepo {

    public void loginUser(String email, String password, NetworkCallback callback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        callback.onSuccess();
                    } else {
                        callback.onFailure("Authentication failed.");
                    }
                });
    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailur(String msg) {
        // Handle login failure in Presenter
    }
}