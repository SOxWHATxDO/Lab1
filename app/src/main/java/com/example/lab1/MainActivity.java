package com.example.lab1;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;
import com.example.lab1.databinding.ActivityMainBinding;

/** Launch smoke-example, not the solution of any complete practice variant. */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        InsetsHelper.apply(binding.getRoot());

        binding.calculateButton.setOnClickListener(v -> calculate());

        binding.clearButton.setOnClickListener(v -> {
            binding.firstInput.setText("");
            binding.secondInput.setText("");
            binding.firstInput.setError(null);
            binding.secondInput.setError(null);
            binding.resultText.setText(R.string.result_initial);
        });
    }

    private void calculate() {
        binding.firstInput.setError(null);
        binding.secondInput.setError(null);

        int inventory;
        int dayUse;

        try {
            inventory = InputRules.positive(binding.firstInput.getText().toString(), 1000);
        } catch (IllegalArgumentException error) {
            binding.firstInput.setError(error.getMessage());
            return;
        }

        try {
            dayUse = InputRules.positive(binding.secondInput.getText().toString(), 1000);
        } catch (IllegalArgumentException error) {
            binding.secondInput.setError(error.getMessage());
            return;
        }

        int days = InputRules.fullDays(inventory, dayUse);
        int remainder = InputRules.remainder(inventory, dayUse);

        binding.resultText.setText(
                getString(R.string.result_days_remainder, days, remainder)
        );
    }
}
