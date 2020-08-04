package us.erlang.android_livedata;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ArchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arch);

        final TextView textView = findViewById(R.id.counter_text);
        CounterViewModel model = ViewModelProviders.of(this).get(CounterViewModel.class);

        Button button = findViewById(R.id.increase);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.startCounter();
            }
        });


        final Observer<Long> counterObserver = new Observer<Long>() {
            @Override
            public void onChanged(@Nullable final Long counter) {
                textView.setText(String.valueOf(counter));
            }
        };
        model.getCounter().observe(this, counterObserver);
    }
}