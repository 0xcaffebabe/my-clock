package wang.ismy.clock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        tickTok();
    }

    private void tickTok() {
        TextView second = MainActivity.this.findViewById(R.id.second);
        TextView hour = MainActivity.this.findViewById(R.id.hour);
        TextView minute = MainActivity.this.findViewById(R.id.minute);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LocalDateTime now = LocalDateTime.now();
                second.post(() -> {
                    second.setText(String.format("%02d", now.getSecond()));
                    minute.setText(String.format("%02d", now.getMinute()));
                    hour.setText(String.format("%02d", now.getHour()));
                });
            }
        }).start();
    }
}