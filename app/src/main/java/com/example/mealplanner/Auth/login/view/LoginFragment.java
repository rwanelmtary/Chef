package com.example.mealplanner.Auth.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealplanner.Auth.login.presenter.LoginPresenterImp;
import com.example.mealplanner.R;
import com.example.mealplanner.home.view.HomeActivity;
import com.example.mealplanner.model.LoginRepositoryImp;


public class LoginFragment extends Fragment implements LoginView{
    private LoginPresenterImp loginPresenterImp;
    private EditText mailText;
    private EditText passText;
    private Button loginButton;




    public LoginFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenterImp = new LoginPresenterImp(new LoginRepositoryImp(), this);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mailText=view.findViewById(R.id.logtext);
        passText=view.findViewById(R.id.textpass);
        loginButton=view.findViewById(R.id.loginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mailText.getText().toString();
                String password = passText.getText().toString();
                loginPresenterImp.logIn(email, password);

            }
        });
    }

    @Override
    public void showLoginSuccess() {
      //  Toast.makeText(this,"logged successfully",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), HomeActivity.class);

        // Start the Activity
        startActivity(intent);
        Toast.makeText(getContext(), "Login successful!", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void showLoginFailur(String msg) {
        Toast.makeText(getContext(), "Login failed: " + msg, Toast.LENGTH_SHORT).show();


    }
}