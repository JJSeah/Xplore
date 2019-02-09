package sg.edu.np.s10177744connect.madassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.s10177744connect.madassignment.models.Money;
import sg.edu.np.s10177744connect.madassignment.models.ToDo;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 67;
    public static final String DATABASE_NAME = "TripDB2.db";
    public static final String TABLE_ATTRACTION = "attraction";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "attractionName";
    public static final String COLUMN_LOCATION = "address";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_PHOTO = "photo";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_VISIT = "visit";
    public static final String TABLE_JOURNAL = "journal";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_ATTRACTION
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DETAILS + " TEXT, "
                + COLUMN_PHOTO + " TEXT, "
                + COLUMN_STATUS + " TEXT, "
                + COLUMN_COUNTRY + " TEXT, "
                + COLUMN_VISIT + " TEXT,"
                + COLUMN_LOCATION + " TEXT,"
                + COLUMN_LATITUDE + " TEXT,"
                + COLUMN_LONGITUDE + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_JOURNAL
                + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_DETAILS + " TEXT, "
                + COLUMN_PHOTO + " BLOB)");

        db.execSQL(Money.CREATE_TABLE);
        db.execSQL(ToDo.CREATE_TABLE);


        ContentValues values = new ContentValues();


        values.put(COLUMN_ID, 1);
        values.put(COLUMN_NAME, "Singapore Flyer");
        values.put(COLUMN_PHOTO, "sg");
        values.put(COLUMN_DETAILS, "The Singapore Flyer is a giant Ferris wheel in Singapore.");
        values.put(COLUMN_LOCATION, "30 Raffles Ave");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2892988");
        values.put(COLUMN_LONGITUDE, "103.8631368");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 2);
        values.put(COLUMN_NAME, "Garden By The Bay");
        values.put(COLUMN_PHOTO, "sg1");
        values.put(COLUMN_DETAILS, "Gardens by the Bay is a nature park spanning 101 hectares of reclaimed land in the Central Region of Singapore, adjacent to the Marina Reservoir.");
        values.put(COLUMN_LOCATION, "18 Marina Gardens Dr");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2815683");
        values.put(COLUMN_LONGITUDE, "103.8636132");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 3);
        values.put(COLUMN_NAME, "Singapore National Museum ");
        values.put(COLUMN_PHOTO, "sg5");
        values.put(COLUMN_DETAILS, "The National Museum of Singapore is the oldest museum in Singapore. Its history dates back to 1849, when it was started as a section of a library at Singapore Institution and called the Raffles Library and Museum.");
        values.put(COLUMN_LOCATION, "93 Stamford Road");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2966147");
        values.put(COLUMN_LONGITUDE, "103.8485095");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 4);
        values.put(COLUMN_NAME, "Chinatown");
        values.put(COLUMN_PHOTO, "sg3");
        values.put(COLUMN_DETAILS, "Chinatown is a subzone and ethnic enclave located within the Outram district in the Central Area of Singapore. Featuring distinctly Chinese cultural elements, Chinatown has had a historically concentrated ethnic Chinese population.");
        values.put(COLUMN_LOCATION, "Smith Street");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2814942");
        values.put(COLUMN_LONGITUDE, "103.8448202");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 5);
        values.put(COLUMN_NAME, "Central Fire Station");
        values.put(COLUMN_PHOTO, "sg4");
        values.put(COLUMN_DETAILS, "The Central Fire Station is the oldest existing fire station in Singapore, and is located at Hill Street in the Museum Planning Area, within the Central Area.");
        values.put(COLUMN_LOCATION, " 62 Hill St");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2922139");
        values.put(COLUMN_LONGITUDE, "103.8492126");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 6);
        values.put(COLUMN_NAME, "Buddha Tooth Temple");
        values.put(COLUMN_PHOTO, "sg6");
        values.put(COLUMN_DETAILS, "The Buddha Tooth Relic Temple and Museum is a Buddhist temple and museum complex located in the Chinatown district of Singapore.");
        values.put(COLUMN_LOCATION, "288 South Bridge Rd");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Singapore");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "1.2813361");
        values.put(COLUMN_LONGITUDE, "103.8445218");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 7);
        values.put(COLUMN_NAME, "Blue Boat House");
        values.put(COLUMN_PHOTO, "aus");
        values.put(COLUMN_DETAILS, "The boat shed is a prominent landmark on the swan river.");
        values.put(COLUMN_LOCATION, "Perth WA 6005");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Perth");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "-31.9735414");
        values.put(COLUMN_LONGITUDE, "115.8265688");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 8);
        values.put(COLUMN_NAME, "Kings Park");
        values.put(COLUMN_PHOTO, "aus1");
        values.put(COLUMN_DETAILS, "Kings Park is a 4.06-square-kilometre park overlooking Perth Water and the central business district of Perth, Western Australia. ");
        values.put(COLUMN_LOCATION, "Fraser Ave, Perth WA 6005");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Perth");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "-31.9609106");
        values.put(COLUMN_LONGITUDE, "115.8321929");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 9);
        values.put(COLUMN_NAME, "Rottnest Island");
        values.put(COLUMN_PHOTO, "aus2");
        values.put(COLUMN_DETAILS, "Rottnest Island sits just offshore from the city of Perth, in Western Australia. A protected nature reserve, it's home to the quokka, a small wallaby-like marsupial. White-sand beaches and secluded coves include the Basin, with its shallow waters, and Thomson Bay, the main hub and ferry port. Strickland Bay is known for its surf breaks, while reef breaks occur at Radar Reef, off the island's far western tip.");
        values.put(COLUMN_LOCATION, "Western Australia 6161");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Perth");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "-32.006204");
        values.put(COLUMN_LONGITUDE, "115.5123398");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 10);
        values.put(COLUMN_NAME, "The Pinnacles");
        values.put(COLUMN_PHOTO, "aus3");
        values.put(COLUMN_DETAILS, "The Pinnacles are limestone formations within Nambung National Park, near the town of Cervantes, Western Australia.");
        values.put(COLUMN_LOCATION, "Pinnacles Drive, Cervantes WA 6511");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Perth");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "-30.5911024");
        values.put(COLUMN_LONGITUDE, "115.1602365");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 11);
        values.put(COLUMN_NAME, "Sand Dunes");
        values.put(COLUMN_PHOTO, "aus4");
        values.put(COLUMN_DETAILS, "Lancelin Sand Dunes is a popular sandboarding destination with gorgeous white sand. For a taste of adventure and an injection of adrenaline, head to Lancelin sand dunes.");
        values.put(COLUMN_LOCATION, "Beacon Rd, Lancelin WA 6044");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Perth");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "-31.0020631");
        values.put(COLUMN_LONGITUDE, "115.3307129");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 12);
        values.put(COLUMN_NAME, "Mount Fuji");
        values.put(COLUMN_PHOTO, "jap4");
        values.put(COLUMN_DETAILS, "Japan’s Mt. Fuji is an active volcano about 100 kilometers southwest of Tokyo. Commonly called “Fuji-san,” it’s the country’s tallest peak, at 3,776 meters. A pilgrimage site for centuries, it’s considered one of Japan’s 3 sacred mountains, and summit hikes remain a popular activity.");
        values.put(COLUMN_LOCATION, "Shizuoka Prefecture, Kanagawa Prefecture");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Tokyo Area");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "35.3605555");
        values.put(COLUMN_LONGITUDE, "138.7277777");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 13);
        values.put(COLUMN_NAME, "Osaka Castle");
        values.put(COLUMN_PHOTO, "jap1");
        values.put(COLUMN_DETAILS, "Osaka Castle is a Japanese castle in Chūō-ku, Osaka, Japan. The castle is one of Japan's most famous landmarks and it played a major role in the unification of Japan.");
        values.put(COLUMN_LOCATION, "1-1 Osakajo, Chuo, Osaka, Osaka Prefecture 540-0002");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Tokyo Area");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "34.6873153");
        values.put(COLUMN_LONGITUDE, "135.5262013");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 14);
        values.put(COLUMN_NAME, "Arashiyama Bamboo Forest");
        values.put(COLUMN_PHOTO, "jap2");
        values.put(COLUMN_DETAILS, "Arashiyama is a district on the western outskirts of Kyoto, Japan. It also refers to the mountain across the Ōi River, which forms a backdrop to the district. Arashiyama is a nationally designated Historic Site and Place of Scenic Beauty.");
        values.put(COLUMN_LOCATION, "Ukyō-ku, Kyoto, Kyoto Prefecture");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Tokyo Area");
        values.put(COLUMN_VISIT, "N");
         values.put(COLUMN_LATITUDE, "35.0168083");
        values.put(COLUMN_LONGITUDE, "135.6714226");
        db.insert(TABLE_ATTRACTION, null, values);

        values.put(COLUMN_ID, 15);
        values.put(COLUMN_NAME, "Todaiji Temple");
        values.put(COLUMN_PHOTO, "jap3");
        values.put(COLUMN_DETAILS, "Tōdai-ji is a Buddhist temple complex that was once one of the powerful Seven Great Temples, located in the city of Nara, Japan. Its Great Buddha Hall houses the world's largest bronze statue of the Buddha Vairocana.");
        values.put(COLUMN_LOCATION, "406-1 Zoshicho, Nara, Nara Prefecture 630-8211");
        values.put(COLUMN_STATUS, "N");
        values.put(COLUMN_COUNTRY, "Tokyo Area");
        values.put(COLUMN_VISIT, "N");
        values.put(COLUMN_LATITUDE, "34.6889851");
        values.put(COLUMN_LONGITUDE, "135.8398158");
        db.insert(TABLE_ATTRACTION, null, values);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ATTRACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        db.execSQL("DROP TABLE IF EXISTS " + Money.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ToDo.TABLE_NAME);
        onCreate(db);
    }


    public void updateAttraction(Country country) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS, country.getStatus());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_ATTRACTION,values, COLUMN_NAME + " = ?", new String[] { String.valueOf(country.getAttraction()) } );
        db.close();
    }

    public void updateVisit(Country country) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_VISIT, country.getVisit());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_ATTRACTION,values, COLUMN_NAME + " = ?", new String[] { String.valueOf(country.getAttraction()) } );
        db.close();
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ATTRACTION;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_ATTRACTION +
                " WHERE " + COLUMN_NAME + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Country findAttraction(String id) {
        String query = "SELECT * FROM " + TABLE_ATTRACTION + " WHERE "
                + COLUMN_NAME
                + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Country country = new Country();

        if (cursor.moveToFirst()) {
            country.setId(Integer.parseInt(cursor.getString(0)));
            country.setAttraction(cursor.getString(1));
            country.setDetails(cursor.getString(2));
            country.setPhoto(cursor.getString(3));
            country.setStatus(cursor.getString(4));
            country.setVisit(cursor.getString(6));
            country.setAddress(cursor.getString(7));
            country.setLag(cursor.getString(8));
            country.setLong(cursor.getString(9));
            cursor.close();
        } else {
            country = null;
        }
        db.close();
        return country;
    } //findAttraction
    public Country displayattraction(String name) {
        String query = "SELECT * FROM " + TABLE_ATTRACTION + " WHERE "
                + COLUMN_COUNTRY
                + " = \"" + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Country country = new Country();

        if (cursor.moveToFirst()) {
            country.setId(Integer.parseInt(cursor.getString(0)));
            country.setAttraction(cursor.getString(1));
            country.setPhoto(cursor.getString(3));
            country.setCountryname(cursor.getString(5));
            cursor.close();
        } else {
            country = null;
        }
        db.close();
        return country;
    } //displayAttraction
    public void addJournal(String name, String details, byte[] image) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DETAILS, details);
        values.put(COLUMN_PHOTO, image);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_JOURNAL, null, values);
        db.close();
    }
    public Cursor getJournal(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_JOURNAL;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Country findJournal(String id) {
        String query = "SELECT * FROM " + TABLE_JOURNAL + " WHERE "
                + COLUMN_NAME
                + " = \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Country country = new Country();

        if (cursor.moveToFirst()) {
            country.setId(Integer.parseInt(cursor.getString(0)));
            country.setAttraction(cursor.getString(1));
            country.setDetails(cursor.getString(2));
            country.setImage(cursor.getBlob(3));
            cursor.close();
        } else {
            country = null;
        }
        db.close();
        return country;
    } //findJournal

    public boolean deleteJournal(String name) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_JOURNAL + " WHERE "
                + COLUMN_NAME + " = \""
                + name + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Country country = new Country();
        if (cursor.moveToFirst()) {
            country.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_JOURNAL, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(country.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    } //deleteJournal
    public void updateJournal(int id, String name, String details) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DETAILS, details);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_JOURNAL,values, COLUMN_ID + " = ?", new String[] { String.valueOf(id) } );
        db.close();
    }
    public long insertMoney(String description,double amount) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Money.COLUMN_AMOUNT, amount);
        values.put(Money.COLUMN_DESCRIPTION, description);

        // insert row
        long id = db.insert(Money.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Money getMoney(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Money.TABLE_NAME,
                new String[]{Money.COLUMN_ID, Money.COLUMN_DESCRIPTION,Money.COLUMN_AMOUNT, Money.COLUMN_TIMESTAMP},
                Money.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Money money = new Money(
                cursor.getInt(cursor.getColumnIndex(Money.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Money.COLUMN_DESCRIPTION)),
                cursor.getDouble(cursor.getColumnIndex(Money.COLUMN_AMOUNT)),
                cursor.getString(cursor.getColumnIndex(Money.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return money;
    }

    public List<Money> getAllAmount() {
        List<Money> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Money.TABLE_NAME + " ORDER BY " +
                Money.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Money note = new Money();
                note.setId(cursor.getInt(cursor.getColumnIndex(Money.COLUMN_ID)));
                note.setDescription(cursor.getString(cursor.getColumnIndex(Money.COLUMN_DESCRIPTION)));
                note.setAmount(cursor.getDouble(cursor.getColumnIndex(Money.COLUMN_AMOUNT)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Money.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();

        // return notes list
        return notes;
    }
    public int getMoneyCount() {
        String countQuery = "SELECT  * FROM " + Money.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateMoney(Money money) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Money.COLUMN_AMOUNT, money.getAmount());
        values.put(Money.COLUMN_DESCRIPTION, money.getDescription());

        // updating row
        return db.update(Money.TABLE_NAME, values, Money.COLUMN_ID + " = ?",
                new String[]{String.valueOf(money.getId())});
    }

    public void deleteMoney(Money money) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Money.TABLE_NAME, Money.COLUMN_ID + " = ?",
                new String[]{String.valueOf(money.getId())});
        db.close();
    }

    public double totalAmount(Money money) {
        SQLiteDatabase db = this.getWritableDatabase();
        String totalSum = "SELECT SUM(" + Money.COLUMN_AMOUNT + ") FROM " + Money.TABLE_NAME;
        Cursor cursor = db.rawQuery(totalSum, null);
        if (cursor.moveToFirst()){
            money.setTotalAmount(cursor.getDouble(0));
        }
        cursor.close();
        return money.getTotalAmount();
    }
    public long insertToDo(String desc) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(ToDo.COLUMN_DESCRIPTION, desc);

        // insert row
        long id = db.insert(ToDo.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public void insertdetailToDo(String desc, int id, String timestamp) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(ToDo.COLUMN_DESCRIPTION, desc);
        values.put(ToDo.COLUMN_ID, id);
        values.put(ToDo.COLUMN_TIMESTAMP, timestamp);

        // insert row
        db.insert(ToDo.TABLE_NAME, null, values);

        // close db connection
        db.close();

    }

    public ToDo getToDo(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(ToDo.TABLE_NAME,
                new String[]{ToDo.COLUMN_ID, ToDo.COLUMN_DESCRIPTION, ToDo.COLUMN_TIMESTAMP},
                ToDo.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        ToDo note = new ToDo(
                cursor.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<ToDo> getAllToDO() {
        List<ToDo> toDos = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ToDo.TABLE_NAME + " ORDER BY " +
                ToDo.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ToDo todo = new ToDo();
                todo.setId(cursor.getInt(cursor.getColumnIndex(ToDo.COLUMN_ID)));
                todo.setDescription(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_DESCRIPTION)));
                todo.setTimestamp(cursor.getString(cursor.getColumnIndex(ToDo.COLUMN_TIMESTAMP)));

                toDos.add(todo);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return toDos;
    }

    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + ToDo.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(todo.COLUMN_DESCRIPTION, todo.getDescription());

        // updating row
        return db.update(ToDo.TABLE_NAME, values, ToDo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
    }

    public void deleteToDo(ToDo todo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ToDo.TABLE_NAME, ToDo.COLUMN_ID + " = ?",
                new String[]{String.valueOf(todo.getId())});
        db.close();
    }
}





