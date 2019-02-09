package sg.edu.np.s10177744connect.madassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {
    Button click;
    public static TextView data;
    public static EditText busStop;
    private RecyclerView bRecyclerView;
    public static busAdapter bAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bRecyclerView = findViewById(R.id.bus_recycler_view);
        click = findViewById(R.id.button);
        busStop = findViewById(R.id.busStopNo);
        busStop.setOnEditorActionListener(new DoneOnEditorActionListener());
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData process = new fetchData();
                process.execute();
            }
        });
        bRecyclerView.setHasFixedSize(true);
        bRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        bRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bAdapter = new busAdapter(this, fetchData.ServiceNoList);
        bRecyclerView.setAdapter(bAdapter);
        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(MainActivity.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(MainActivity.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(MainActivity.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(MainActivity.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(MainActivity.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
}
