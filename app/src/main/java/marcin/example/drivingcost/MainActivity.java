package marcin.example.drivingcost;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {
    private EditText fuelPrice, fuelConsumption, routeLength;
    private TextView textResult;
    private Button btnCalculate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews() {
        fuelPrice = findViewById(R.id.editTextFuelPrice);
        fuelConsumption = findViewById(R.id.editTextFuelConsumption);
        routeLength = findViewById(R.id.routeLength);
        textResult = findViewById(R.id.textResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);

        fuelPrice.addTextChangedListener(inputTextTextWatcher);
        fuelConsumption.addTextChangedListener(inputTextTextWatcher);
        routeLength.addTextChangedListener(inputTextTextWatcher);

        btnCalculate.setOnClickListener(view -> {
            double fPrice = Double.parseDouble(fuelPrice.getText().toString());
            double fConsumption = Double.parseDouble(fuelConsumption.getText().toString());
            double rLength = Double.parseDouble(routeLength.getText().toString());
            double cost = ((fPrice * fConsumption) / 100) * rLength;
            String costFormatted = (String.format("%.2f", cost));
            textResult.setText(costFormatted + " PLN");
            textResult.setTextColor(Color.parseColor("red"));

            Toast.makeText(this, "Driving cost calculated", Toast.LENGTH_SHORT).show();
        });

        btnClear.setOnClickListener(view -> {
            fuelPrice.setText("");
            fuelConsumption.setText("");
            routeLength.setText("");
            textResult.setText("0 PLN");
            textResult.setTextColor(Color.parseColor("grey"));

            Toast.makeText(this, "Data cleared", Toast.LENGTH_SHORT).show();
        });
    }

    private final TextWatcher inputTextTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String fuelPriceInput = fuelPrice.getText().toString().trim();
            String fuelConsumptionInput = fuelConsumption.getText().toString().trim();
            String routeLengthInput = routeLength.getText().toString().trim();

            btnCalculate.setEnabled(!fuelPriceInput.isEmpty()
                    && !fuelConsumptionInput.isEmpty()
                    && !routeLengthInput.isEmpty());

            btnClear.setEnabled(!fuelPriceInput.isEmpty()
                    || !fuelConsumptionInput.isEmpty()
                    || !routeLengthInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}