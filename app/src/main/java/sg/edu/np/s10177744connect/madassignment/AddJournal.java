package sg.edu.np.s10177744connect.madassignment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class AddJournal extends AppCompatActivity {


    final int REQUEST_CODE_GALLERY = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        final EditText Name = findViewById(R.id.edtName);
        final EditText Details = findViewById(R.id.edtDetails);
        final ImageView img = findViewById(R.id.imageView);
        Button add = findViewById(R.id.btnAdd);
        Button choose = findViewById(R.id.btnChoose);
        final DBHelper dbHandler = new DBHelper(this, null, null, 1);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                final ImageView img = findViewById(R.id.imageView);
                img.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    //ADD
    public void newJournal (View view) {
        DBHelper dbHandler = new DBHelper(this, null, null, 1);
        EditText Name = findViewById(R.id.edtName);
        String attraction = Name.getText().toString();
        EditText Details = findViewById(R.id.edtDetails);
        String description = Details.getText().toString();
        ImageView img = findViewById(R.id.imageView);
        byte[] image = imageViewToByte(img);
        dbHandler.addJournal(attraction,description,image);
        Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
        Name.setText("");
        Details.setText("");
        img.setImageResource(R.mipmap.ic_launcher);
    }
    //Visit Button
    public void view (View view) {

        startActivity(new Intent(AddJournal.this, JournalList.class));
    }
    //Select img
    public void select(View view) {
        ActivityCompat.requestPermissions(
                AddJournal.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_list:
                    finish();
                    startActivity(new Intent(AddJournal.this, Favourites.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_journal:
                    finish();
                    startActivity(new Intent(AddJournal.this, JournalList.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_reminder:
                    finish();
                    startActivity(new Intent(AddJournal.this, ToDoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.navigation_expenses:
                    finish();
                    startActivity(new Intent(AddJournal.this, Finance.class));
                    overridePendingTransition(0,0);
                    return true;

            }
            return false;
        }
    };

}
