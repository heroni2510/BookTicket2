package com.example.nhom23.bookticket2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nhom23.bookticket2.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Button registerBtn1 ;
    EditText emailET ;
    EditText passwordET ;
    EditText nameET;
    EditText phoneET ;
    EditText addressET;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhxa();
        mAuth = FirebaseAuth.getInstance();
        registerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
    }

    private void Register(){
        final String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                            String Uid = current_user.getUid();
                            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(Uid);
                            User user = new User(nameET.getText().toString(),Uid,phoneET.getText().toString(),addressET.getText().toString(),emailET.getText().toString());
                            databaseReference.setValue(user);
                            Toast.makeText(RegisterActivity.this,"Dang ky thanh cong",Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    private void anhxa() {
        registerBtn1 = (Button) findViewById(R.id.registerbtn);
        emailET = (EditText) findViewById(R.id.editText);
        passwordET = (EditText) findViewById(R.id.editText2);
        nameET = (EditText) findViewById(R.id.editText4);
        phoneET = (EditText) findViewById(R.id.editText3);
        addressET = (EditText) findViewById(R.id.editText5);

    }
}
