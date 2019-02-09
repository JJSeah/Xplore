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
import android.widget.ImageView;
import android.widget.TextView;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;


public class JournalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);

        String num = getIntent().getStringExtra("Journal");

        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        Country country = dbHandler.findJournal(num);
        TextView name = (TextView)findViewById(R.id.txtName);
        name.setText(country.Attraction);

        TextView details = (TextView)findViewById(R.id.txtDetails);
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
                    startActivity(new Intent(JournalDetails.this, CountryList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(JournalDetails.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(JournalDetails.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(JournalDetails.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(JournalDetails.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }
    };

    public void removeJournal (View view) {
        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        TextView name = (TextView)findViewById(R.id.txtName);
        boolean result = dbHandler.deleteJournal(
                name.getText().toString());
        if (result)
        {
            finish();
            startActivity(new Intent(JournalDetails.this, JournalList.class));
        }
    }
    public void UpdateJournal(View v) {
        Intent intent = new Intent(this, UpdateJournal.class);
        String num = getIntent().getStringExtra("Journal");
        intent.putExtra("Update", num);
        startActivity(intent);
    }

}
