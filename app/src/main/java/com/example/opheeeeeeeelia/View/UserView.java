package com.example.opheeeeeeeelia.View;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.opheeeeeeeelia.Model.LoginResult;
import com.example.opheeeeeeeelia.R;
import com.example.opheeeeeeeelia.Retrofit.RetrofitClient;
import com.example.opheeeeeeeelia.Retrofit.RetrofitInterface;
import com.example.opheeeeeeeelia.ViewModel.UserViewModel;

public class UserView extends AppCompatActivity {

    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser RetrofitInterface en utilisant RetrofitClient
        RetrofitInterface retrofitInterface = RetrofitClient.getClient().create(RetrofitInterface.class);

        // Initialiser UserViewModel avec RetrofitInterface
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(UserViewModel.class);

        // Appel de la méthode de connexion dans le ViewModel
        viewModel.login("email@example.com", "password").observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(LoginResult loginResult) {
                if (loginResult != null) {
                    // Connexion réussie, afficher le token de l'utilisateur
                    String token = loginResult.getToken();
                    Toast.makeText(UserView.this, "Welcome, Token: " + token, Toast.LENGTH_SHORT).show();
                } else {
                    // Erreur de connexion
                    Toast.makeText(UserView.this, "Failed to login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}