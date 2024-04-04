package com.example.spotifysdkimplementation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.spotifysdkimplementation.databinding.AccountCreationPageBinding;
import com.example.spotifysdkimplementation.databinding.LoginPageBinding;
import com.example.spotifysdkimplementation.databinding.LoginPageBinding;

public class AccountCreationPage extends AppCompatActivity {
    private AccountCreationPageBinding binding;
    private Button confirmCreation;
    private EditText username, password, confirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_creation_page);

        username = findViewById(R.id.create_username);
        password = findViewById(R.id.create_password);
        confirmPassword = findViewById(R.id.confirm_create_password);
        confirmCreation = findViewById(R.id.confirm_creation);

        confirmCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1 = password.getText().toString();
                String p2 = confirmPassword.getText().toString();

                boolean passwordsMatch = false;
                if ((p1.equals(p2))) {
                    passwordsMatch = true;
                } else {
                    Toast.makeText(AccountCreationPage.this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
                if (passwordsMatch && (username.getText().toString().length() > 0)) {
                    Log.d("Account", "Made");
                    createAccount(username.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    private void createAccount(String user, String pass) {
        //NEED TO IMPLEMENT FIREBASE USER CREATION HERE
    }

//    @Override
//    public View onCreateView(
//            LayoutInflater inflater, ViewGroup container,
//            Bundle savedInstanceState
//    ) {
//
//
//        binding = AccountCreationPageBinding.inflate(inflater, container, false);
//        return binding.getRoot();
//
//    }
//
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                NavHostFragment.findNavController(LoginPage.this)
////                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
////            }
////        });
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}