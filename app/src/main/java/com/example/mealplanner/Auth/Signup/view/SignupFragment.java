package com.example.mealplanner.Auth.Signup.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mealplanner.Auth.Signup.presenter.SignupPresenterImp;
import com.example.mealplanner.R;
import com.example.mealplanner.home.view.HomeActivity;
import com.example.mealplanner.model.SignupRepositoryImp;
import com.google.firebase.auth.FirebaseUser;


public class SignupFragment extends Fragment implements SignupView{
    private SignupRepositoryImp signupRepositoryImp;
    private SignupPresenterImp signupPresenterImp;
    Button signupBtn;
    EditText emailEditText;
    EditText passwordEditText;
    TextView logintxt;




    public SignupFragment() {
        // Required empty public constructor
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signupPresenterImp = new SignupPresenterImp(this, new SignupRepositoryImp());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views here
        signupBtn = view.findViewById(R.id.SignUpBtn);
        emailEditText = view.findViewById(R.id.etName);
        passwordEditText = view.findViewById(R.id.etPassword);
        logintxt=view.findViewById(R.id.TxtLogin);
       // NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_auth);



        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = getEmailFromInput();
                String password = getPasswordFromInput();
                signupPresenterImp.signup(email, password);

                Intent intent = new Intent(getActivity(), HomeActivity.class);

                // Start the Activity
                startActivity(intent);
            }
        });
//        logintxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                navController.navigate(R.id.action_signupFragment_to_loginFragment);
//
//            }
//        });
        logintxt.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_signupFragment_to_loginFragment);
       });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSignUpSuccess(FirebaseUser user) {

    }

    @Override
    public void showSignUpError(String errorMessage) {

    }

    private String getEmailFromInput() {
        // Retrieve email from the input field
         emailEditText.findViewById(R.id.etName);
        return emailEditText.getText().toString();
    }

    private String getPasswordFromInput() {
        // Retrieve password from the input field
        passwordEditText .findViewById(R.id.etPassword);
        return passwordEditText.getText().toString();
    }
}