package com.s23010226.trashtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Sign_Up extends AppCompatActivity {

    Database_Helper myDb;

    EditText username, email, password, phone_number;
    Button sign_up_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myDb = new Database_Helper(this);

        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
        email = findViewById(R.id.editTextEmail);
        phone_number = findViewById(R.id.editTextPhone);
        sign_up_btn = findViewById(R.id.sign_up);

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String phone = phone_number.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty() || mail.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(Sign_Up.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                boolean result = myDb.insertUser(user, mail, phone, pass);
                
                if (result) {
                    Toast.makeText(Sign_Up.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Sign_Up.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Sign_Up.this, "Regitration Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}