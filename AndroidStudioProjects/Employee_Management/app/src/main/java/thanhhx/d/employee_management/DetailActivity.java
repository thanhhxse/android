package thanhhx.d.employee_management;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); //check
        Intent intent = this.getIntent();
        String id = intent.getStringExtra("id");
        TextView txtResult = findViewById(R.id.txtResult);
        txtResult.setText(id);
    }
}
