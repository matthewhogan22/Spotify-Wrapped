package com.example.spotifysdkimplementation;

import static android.content.ContentValues.TAG;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class AccountCreationPage extends AppCompatActivity {
    private AccountCreationPageBinding binding;
    private Button confirmCreation;
    private EditText username, password, confirmPassword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser toBeAdded;
    private FirebaseAuth mAuth;

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
                String user = username.getText().toString();

                boolean passwordsMatch = false;
                if ((p1.equals(p2))) {
                    passwordsMatch = true;
                } else {
                    Toast.makeText(AccountCreationPage.this, "Passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }
                if (passwordsMatch && (user.length() > 0 && user.contains("@") && user.contains(".com"))) {
                    Log.d("Account", "Made");
                    createAccount(user, p1);
                } else {
                    Toast.makeText(AccountCreationPage.this, "Issue with email/password",
                            Toast.LENGTH_SHORT).show();
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
        mAuth.createUserWithEmailAndPassword(user, pass)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign up success, update UI with the signed-in user's information
                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        // Proceed with further actions (e.g., saving user data to the database)
                        getCode();
                    } else {
                        // If sign up fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        Toast.makeText(AccountCreationPage.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getCode() {
        final AuthorizationRequest request = getAuthenticationRequest(AuthorizationResponse.Type.CODE);
        AuthorizationClient.openLoginActivity(AccountCreationPage.this, 1, request);
    }

    private AuthorizationRequest getAuthenticationRequest(AuthorizationResponse.Type type) {
        return new AuthorizationRequest.Builder("d857953ebaca4345b0be666d38a4037c", type, "spotifysdkimplementation://auth")
                .setShowDialog(false)
                .setScopes(new String[] { "user-read-email", "user-top-read" }) // <--- Change the scope of your requested token here
                .setCampaign("your-campaign-token")
                .build();
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