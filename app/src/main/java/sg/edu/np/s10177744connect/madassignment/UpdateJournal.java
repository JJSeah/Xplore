package sg.edu.np.s10177744connect.madassignment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class UpdateJournal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_journal);
        String num = getIntent().getStringExtra("Update");

        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        Country country = dbHandler.findJournal(num);

        TextView id = (TextView)findViewById(R.id.txtid);
        id.setText(String.valueOf(country.id));

        EditText name = (EditText)findViewById(R.id.txtName);
        name.setText(country.Attraction);

        EditText details = (EditText)findViewById(R.id.txtDetails);
        details.setText(country.Details);

        ImageView photo = findViewById(R.id.imgPhoto);
        byte[] Pic = country.image;
        Bitmap bitmap = BitmapFactory.decodeByteArray(Pic, 0, Pic.length);
        photo.setImageBitmap(bitmap);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    finish();
                    startActivity(new Intent(UpdateJournal.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(UpdateJournal.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(UpdateJournal.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(UpdateJournal.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(UpdateJournal.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        }
    };
    //UPDATE
    public void update (View view) {
        EditText name = (EditText)findViewById(R.id.txtName);
        EditText details = (EditText)findViewById(R.id.txtDetails);
        TextView id = (TextView)findViewById(R.id.txtid);
        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        dbHandler.updateJournal(Integer.parseInt(id.getText().toString()),name.getText().toString(),details.getText().toString());
        finish();
        startActivity(new Intent(UpdateJournal.this, JournalList.class));
    }
}
