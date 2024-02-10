package com.example.mealplanner.model;


import com.example.mealplanner.network.NetworkCallback;
import com.google.firebase.auth.FirebaseAuth;

public class SignupRepositoryImp {


    public void signup(String email, String password, NetworkCallback networkCallback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        networkCallback.onSignUpSuccess(task.getResult().getUser());
                    } else {
                        networkCallback.onSignUpFailure(task.getException().getMessage());
                    }
                });
    }



}
