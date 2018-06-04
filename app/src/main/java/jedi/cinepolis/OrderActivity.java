package jedi.cinepolis;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class OrderActivity extends AppCompatActivity {

    private int aantal;
    private double prijs;
    private static DecimalFormat df2 = new DecimalFormat(".##");
    Spinner bankSpinner;
    Button betaalBtn;
    EditText emailAdress;
    ArrayAdapter<CharSequence> bankAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSharedElementExitTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition_a));
        setContentView(R.layout.activity_order);

        getSupportActionBar().setTitle("Kassa");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));

        CardView bankCv = (CardView)findViewById(R.id.backCv);
        bankCv.setVisibility(View.GONE);

        emailAdress=(EditText)findViewById(R.id.emailTxT);
        //final String email = emailAdress.getText().toString();

        betaalBtn = (Button)findViewById(R.id.betaalBtn);
        betaalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Uw Tickets zijn verzonden naar: " + emailAdress.getText().toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });

        bankSpinner = (Spinner)findViewById(R.id.bankSpinner);
        bankAdapter = (ArrayAdapter.createFromResource(this,R.array.bank_namen,android.R.layout.simple_spinner_item));
        bankAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankSpinner.setAdapter(bankAdapter);
        bankSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        final TextView totaalPrijsTv = (TextView)findViewById(R.id.totaalPrijs);
        final TextView aantalTicketsTv = (TextView)findViewById(R.id.aantalTickets);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onRadioButtonClicked(View view) {

        CardView bankCv = (CardView)findViewById(R.id.backCv);
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_ideal:
                if (checked)
                bankCv.setVisibility(View.VISIBLE);
                    break;
            case R.id.radio_paypal:
                if (checked)
                    bankCv.setVisibility(View.GONE);
                    break;
            case R.id.radio_creditcard:
                if (checked)
                    bankCv.setVisibility(View.GONE);
                    break;
        }
    }
}
