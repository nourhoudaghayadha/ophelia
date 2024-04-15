package com.example.opheeeeeeeelia.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.opheeeeeeeelia.Model.LoginResult;
import com.example.opheeeeeeeelia.Retrofit.RetrofitInterface;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {

    private RetrofitInterface retrofitInterface;

    public UserViewModel(RetrofitInterface retrofitInterface) {
        this.retrofitInterface = retrofitInterface;
    }

    public MutableLiveData<LoginResult> login(String email, String password) {
        MutableLiveData<LoginResult> loginResultLiveData = new MutableLiveData<>();

        HashMap<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        retrofitInterface.executeLogin(map).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if (response.isSuccessful()) {
                    // Connexion réussie, récupérer le token
                    LoginResult result = response.body();
                    loginResultLiveData.setValue(result);
                } else {
                    // Erreur de connexion, gérer l'échec de la connexion
                    loginResultLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                // Erreur lors de l'envoi de la requête, gérer l'échec de la connexion
                loginResultLiveData.setValue(null);
            }
        });

        return loginResultLiveData;
    }
}