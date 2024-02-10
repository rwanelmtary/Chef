package com.example.mealplanner.Auth.Signup.view;

import com.google.firebase.auth.FirebaseUser;

public interface SignupView {
    public void showLoading() ;


    public void hideLoading() ;


    public void showSignUpSuccess(FirebaseUser user) ;


    public void showSignUpError(String errorMessage) ;
}
