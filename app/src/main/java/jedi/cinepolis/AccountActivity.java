package jedi.cinepolis;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AccountActivity extends AppCompatActivity {

    Button btnSignIn,btnSignUp;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getSupportActionBar().setTitle("Account");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        // create a instance of SQLite Database
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        // Get The Refference Of Buttons
        btnSignIn = (Button) findViewById(R.id.buttonSignIN);
        btnSignUp = (Button) findViewById(R.id.buttonSignUP);

        // Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intentSignUP);
            }
        });


        // Methos to handleClick Event of Sign In Button
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View V) {
                final Dialog dialog = new Dialog(AccountActivity.this);
                dialog.setContentView(R.layout.activity_sign_in);
                dialog.setTitle("Login");

                // get the Refferences of views
                final EditText editTextUserName = (EditText) dialog.findViewById(R.id.editTextUserNameToLogin);
                final EditText editTextPassword = (EditText) dialog.findViewById(R.id.editTextPasswordToLogin);

                Button btnSignIn = (Button) dialog.findViewById(R.id.buttonSignIn);

                // Set On ClickListener
                btnSignIn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        // get The User name and Password
                        String userName = editTextUserName.getText().toString();
                        String password = editTextPassword.getText().toString();

                        // fetch the Password form database for respective user name
                        String storedPassword = loginDataBaseAdapter.getSinlgeEntry(userName);

                        // check if the Stored password matches with  Password entered by user
                        if (password.equals(storedPassword)) {
                            Toast.makeText(AccountActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                            dialog.dismiss();

                            Intent homeIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(homeIntent);
                        } else {
                            Toast.makeText(AccountActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                dialog.show();
            }

        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        loginDataBaseAdapter.close();
    }
}

