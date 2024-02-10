package com.example.mealplanner.Auth.login.presenter;

import com.example.mealplanner.Auth.login.view.LoginView;
import com.example.mealplanner.model.LoginRepo;
import com.example.mealplanner.model.LoginRepositoryImp;
import com.example.mealplanner.network.NetworkCallback;
import com.google.firebase.auth.FirebaseUser;

public class LoginPresenterImp implements NetworkCallback { // Implement NetworkCallback interface

    private LoginView loginView;
    private LoginRepositoryImp loginRepositoryImp;

    public LoginPresenterImp(LoginRepositoryImp loginRepositoryImp, LoginView loginView) {
        this.loginRepositoryImp = loginRepositoryImp;
        this.loginView = loginView;
    }

    public void logIn(String email, String password) {
        loginRepositoryImp.loginUser(email, password, this); // Pass the presenter as the NetworkCallback
    }


    @Override
    public void onSignUpSuccess(FirebaseUser user) {

    }

    @Override
    public void onSignUpFailure(String errorMessage) {

    }

    @Override
    public void onSuccess() {
        loginView.showLoginSuccess();


    }

    @Override
    public void onFailure(String errorMessage) {
        // Handle login failure
        loginView.showLoginFailur(errorMessage);

    }
}
