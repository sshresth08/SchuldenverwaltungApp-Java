
package de.thkoeln.cvogt.android.schuldenverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton1, radioButton2;
    EditText editText;
    Button saveButton, zueruckZahlungsButton;
    TextView name1SchuldenTextView, name2SchuldenTextView, gesamtSchuldenTextView;

    String name1 = "Sudin";
    String name2 = "Tunc";
    double name1Schulden = 0.0;
    double name2Schulden = 0.0;
    double gesamtSchulden = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.radiogroup);
        radioButton1 = findViewById(R.id.Name1);
        radioButton2 = findViewById(R.id.Name2);

        editText = findViewById(R.id.verleihungsbetrag);

        saveButton = findViewById(R.id.abspeichern);
        zueruckZahlungsButton = findViewById(R.id.rueckzahlung);

        name1SchuldenTextView = findViewById(R.id.Name1Schulden);
        name2SchuldenTextView = findViewById(R.id.Name2Schulden);
        gesamtSchuldenTextView = findViewById(R.id.gesamtSchulden);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String betragString = editText.getText().toString();
                if(!betragString.isEmpty()){
                    double betrag = Double.parseDouble(betragString);
                    if(betrag > 0){
                        RadioButton slectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                        if(slectedRadioButton == radioButton1){
                            name1Schulden += betrag;
                            name1SchuldenTextView.setText(name1 + ": " + name1Schulden + " Euro");
                            gesamtSchulden += betrag;
                        }
                         else if( slectedRadioButton == radioButton2) {
                             name2Schulden += betrag;
                             name2SchuldenTextView.setText(name2 + ": " + name2Schulden + " Euro");
                             gesamtSchulden += betrag;
                        }
                         gesamtSchuldenTextView.setText("Gesamtschulden: " + gesamtSchulden + " Euro");
                         if ( name1Schulden > 200) {
                             Toast.makeText(MainActivity.this, "Achtung- " + name1 + " hat hohe Schulden!", Toast.LENGTH_LONG).show();
                         } else if (name2Schulden > 200) {
                             Toast.makeText(MainActivity.this, "Achtung- " + name2 + " hat hohe Schulden!", Toast.LENGTH_LONG).show();
                         }
                         editText.setText("");
                    }
                }
            }
        });

        zueruckZahlungsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton selectedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if(selectedRadioButton == radioButton1){
                    gesamtSchulden -= name1Schulden;
                    name1Schulden = 0.0;
                    name1SchuldenTextView.setText(name1 + ": " + name1Schulden + " Euro");
                } else if (selectedRadioButton == radioButton2) {
                    gesamtSchulden -= name2Schulden;
                    name2Schulden = 0.0;
                    name2SchuldenTextView.setText(name2 + ": " + name2Schulden + " Euro");
                }
                gesamtSchuldenTextView.setText("Gesamtschulden: " + gesamtSchulden + " Euro");
                editText.setText("");
            }
        });
    }

}