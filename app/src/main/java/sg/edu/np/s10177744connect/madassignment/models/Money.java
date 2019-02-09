package sg.edu.np.s10177744connect.madassignment.models;

public class Money {
    public static final String TABLE_NAME = "finance";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private double amount;
    private String description;
    private String timestamp;
    private double totalAmount;

    public Money(int id, String title, double amount, String timestamp) {
        this.id = id;
        this.description = title;
        this.amount = amount;
        this.timestamp = timestamp;
    }


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_AMOUNT + " REAL,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Money() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {return description; }

    public void setDescription(String description) { this.description = description; }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
