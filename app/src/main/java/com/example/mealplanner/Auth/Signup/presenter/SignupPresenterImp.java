package com.example.mealplanner.Auth.Signup.presenter;

import com.example.mealplanner.Auth.Signup.view.SignupView;
import com.example.mealplanner.model.SignupRepositoryImp;
import com.example.mealplanner.network.NetworkCallback;
import com.google.firebase.auth.FirebaseUser;

public class SignupPresenterImp implements NetworkCallback {
    private SignupView signupView;
    private SignupRepositoryImp signupRepositoryImp;
    // private NetworkCallback networkCallback;

    public SignupPresenterImp(SignupView signupView, SignupRepositoryImp signupRepositoryImp) {
        this.signupView = signupView;
        this.signupRepositoryImp = signupRepositoryImp != null ? signupRepositoryImp : new SignupRepositoryImp();
    }


    @Override
    public void onSignUpSuccess(FirebaseUser user) {
        signupView.hideLoading();
        signupView.showSignUpSuccess(user);
    }

    @Override
    public void onSignUpFailure(String errorMessage) {
        signupView.hideLoading();
        signupView.showSignUpError(errorMessage);

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String msg) {

    }

    public void signup(String email, String password) {
        signupView.showLoading();
        signupRepositoryImp.signup(email, password, this); // Pass the presenter as the NetworkCallback
    }
}
