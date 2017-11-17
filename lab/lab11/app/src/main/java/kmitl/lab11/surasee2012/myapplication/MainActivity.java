package kmitl.lab11.surasee2012.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView result;

    private CounterViewModel counterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        result = findViewById(R.id.count);

        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        result.setText(String.valueOf(counterViewModel.getCounter()));
    }

    public void onClickMe(View view) {
        counterViewModel.setCounter(counterViewModel.getCounter() + 1);
        result.setText(String.valueOf(counterViewModel.getCounter()));
    }
}
