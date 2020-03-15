package thanhhx.d.employee_management;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TaskListActivity extends ListActivity {

    private String[] listSubject = {"PRJ311", "PRJ321", "PRM391", "Capstone"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listSubject);
        setListAdapter(adapter);


    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("id", listSubject[position]);
        startActivity(intent);
    }
}

