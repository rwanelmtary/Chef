package com.example.mealplanner.network;

import com.example.mealplanner.model.CatigoryRespone;
import com.google.firebase.auth.FirebaseUser;

public interface NetworkCallback {
   public void onSignUpSuccess(FirebaseUser user);
  public   void onSignUpFailure(String errorMessage);

    public void onSuccess();

    public void onFailure(String msg);
}
