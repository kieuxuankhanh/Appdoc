package com.example.appdocsach;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignActivity extends AppCompatActivity {

    EditText edtuser,edtpass,edtpass2;
    Button btnlogin1,btnsign1;
    String emailPatten = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
            edtuser = findViewById(R.id.edtuser);
            edtpass = findViewById(R.id.edtpass);
            edtpass2 = findViewById(R.id.edtpass2);
            btnsign1 = findViewById(R.id.btnsign1);
            btnlogin1 = findViewById(R.id.btnlogin1);
            progressDialog = new ProgressDialog(this);
            mAuth = FirebaseAuth.getInstance();
            mUser = mAuth.getCurrentUser();
            btnlogin1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
            btnsign1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PerforAuth();
                }
            });
    }

    private void PerforAuth() {
        String email = edtuser.getText().toString();
        String password = edtpass.getText().toString();
        String password2 = edtpass2.getText().toString();

        if(!email.matches(emailPatten)){
            edtuser.setError("Nhap lai email");
        } else if (password.isEmpty() || password.length()<6) {
            edtpass.setError("Nhap lai mat khau");
        } else if (!password.equals(password2)) {
            edtpass2.setError("mat khau khong khop");
        }else {
            progressDialog.setMessage("doi 1 lat...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(SignActivity.this, "Sign thanh cong", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(SignActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(SignActivity.this, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}