package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import mantoo.dbcent.mantoo.Extra.Message;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class SchemaDefinition extends SQLiteOpenHelper {
    private Context context;

    private static final String DATABASE_NAME = "mantoo";
    private static final int DATABASE_VERSION = 7;

    //private static final String CREATE_TABLE="CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT NOT NULL,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    //private static final String DROP_TABLE="DROP TABLE  IF EXISTS parties";

    // private static final String CREATE_TABLE_PARTIES = "CREATE TABLE parties (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT,name TEXT NOT NULL UNIQUE,address TEXT,phoneNumber TEXT,dueAmount DECIMAL(10,5) NOT NULL DEFAULT 0,createdAt INTEGER,updatedAt INTEGER);";
    // private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE inventory (id TEXT PRIMARY KEY NOT NULL,mantooId TEXT,name TEXT NOT NULL UNIQUE,firmId TEXT, mantooProductid TEXT, discount DECIMAL(10,5) DEFAULT 0,tax DECIMAL(10,5) NOT NULL, gstRate DECIMAL(10,5) NOT NULL,rate DECIMAL(10,5) NOT NULL,mrp DECIMAL(10,5) NOT NULL, purcahsePrice DECIMAL(10,5) NOT NULL,createdAt INTEGER,updatedAt INTEGER);";
    // private static final String CREATE_TABLE_USER = "CREATE TABLE user (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL UNIQUE, email TEXT NOT NULL UNIQUE,firmId TEXT, createdAt INTEGER,updatedAt INTEGER)";
    //private static final String CREATE_TABLE_FIRM = "CREATE TABLE firm (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, mantooId TEXT, userId TEXT NOT NULL, createdAt INTEGER,updatedAt INTEGER, FOREIGN KEY (userId) REFERENCES user(id))";
/*
    private static final String CREATE_TABLE_TRANSACTION_ENTRY = "CREATE TABLE txnEntry (\n" +
            "    id            TEXT    PRIMARY KEY\n" +
            "                          NOT NULL\n" +
            "                          UNIQUE,\n" +
            "    transactionsId TEXT    NOT NULL,\n" +
            "    inventoryId   TEXT    NOT NULL,\n" +
            "    quantity      INTEGER DEFAULT (0) \n" +
            "                          NOT NULL,\n" +
            "    rate          DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    mrp           DECIMAL DEFAULT (0) \n" +
            "                          NOT NULL,\n" +
            "    discount      DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    tax           DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    amount        DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    comment       TEXT    DEFAULT Nothing,\n" +
            "    createdAt     INTEGER NOT NULL,\n" +
            "    updatedAt     INTEGER\n" +
            ",FOREIGN KEY (transactionsId) REFERENCES transactions(id), FOREIGN KEY (inventoryId) REFERENCES inventory(id));";

    private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE transactions (\n" +
            "    id                 TEXT    PRIMARY KEY\n" +
            "                               NOT NULL,\n" +
            "    txnNumber          INTEGER    NOT NULL\n" +
            "                               DEFAULT 001,\n" +
            "    partyId            TEXT    NOT NULL,\n" +
            "    firmId             TEXT    NOT NULL,\n" +
            "    Date               INTEGER NOT NULL,\n" +
            "    amount             DECIMAL NOT NULL\n" +
            "                               DEFAULT (0),\n" +
            "    additionalDiscount DECIMAL DEFAULT (0),\n" +
            "    status             TEXT    DEFAULT cart,\n" +
            "    comment            TEXT    DEFAULT Nothing\n" +
            "                               NOT NULL,\n" +
            "    recordLocation     TEXT    ,\n" +
            "    createdAt          INTEGER NOT NULL,\n" +
            "    updatedAt          INTEGER, FOREIGN KEY (partyId) REFERENCES parties(id));";




    private static final String CREATE_TABLE_DUE_PAYMENTS = "CREATE TABLE duePayments (\n" +
            "    id            TEXT    NOT NULL\n" +
            "                          PRIMARY KEY\n" +
            "                          UNIQUE,\n" +
            "    mantooId      TEXT    DEFAULT NULL,\n" +
            "    partyId       TEXT    NOT NULL,\n" +
            "    totalAmount   DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    dueDate       INTEGER NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    transactionId TEXT,\n" +
            "    balance       DECIMAL NOT NULL\n" +
            "                          DEFAULT (0),\n" +
            "    createdAt     INTEGER DEFAULT (0) \n" +
            "                          NOT NULL,\n" +
            "    updatedAt     INTEGER DEFAULT (0) \n" +
            ", FOREIGN KEY (partyId) REFERENCES parties(id));\n";*/


    //  private static final String ALTER_TABLE_INVENTORY="ALTER TABLE inventory ADD COLUMN discount DECIMAL(10,5) DEFAULT 0";
    //  private static final String ALTER_TABLE_PARTIES="ALTER TABLE parties ADD COLUMN sideDays INTEGER DEFAULT 30";

    //   private static final String ALTER_TABLE_Transactions="ALTER TABLE transactions ADD FOREIGN KEY (partyId) REFERENCES parties(id)";

   /*private static final String DROP_TABLE_PARTIES = "DROP TABLE  IF EXISTS parties";
   private static final String DROP_TABLE_INVENTORY = "DROP TABLE  IF EXISTS inventory";
   private static final String DROP_TABLE_USER = "DROP TABLE  IF EXISTS user";
   private static final String DROP_TABLE_FIRM = "DROP TABLE  IF EXISTS firm";*/
//   private static final String DROP_TABLE_TRANSACTION = "DROP TABLE  IF EXISTS transactions";
    // private static final String DROP_TABLE_TRANSACTION_ENTRY = "DROP TABLE  IF EXISTS txnEntry";
    //  private static final String DROP_TABLE_DUE_PAYMENTS = "DROP TABLE  IF EXISTS duePayments";


    public SchemaDefinition(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //Toast.makeText(context,"Constructor Called of insertdata",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Message.message(context,"onCreate Called");

        try {
       /*     sqLiteDatabase.execSQL(CREATE_TABLE_PARTIES);
            Log.d("Main","Parties Created");
            sqLiteDatabase.execSQL(CREATE_TABLE_INVENTORY);
            Log.d("Main","Inventory Created");
            sqLiteDatabase.execSQL(CREATE_TABLE_USER);
            Log.d("Main","User Created");
            sqLiteDatabase.execSQL(CREATE_TABLE_FIRM);
            Log.d("Main","Firm Created");
            sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION);
            Log.d("Main","Transaction Created");
            sqLiteDatabase.execSQL(CREATE_TABLE_TRANSACTION_ENTRY);
            Log.d("Main","TxnEntry Created");


            Message.message(context,"Tables Created");*/

            //sqLiteDatabase.execSQL(CREATE_TABLE_DUE_PAYMENTS);
            Log.d("Main", "Due Payments created");
        } catch (Exception e) {
            Message.message(context, "" + e);
            Log.d("Main", "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Message.message(context, "onUpgrade Called");
        try {
           /*sqLiteDatabase.execSQL(DROP_TABLE_INVENTORY);
            Log.d("Main","Parties Dropped");


            sqLiteDatabase.execSQL(DROP_TABLE_PARTIES);
            Log.d("Main","Inventory Dropped");

            sqLiteDatabase.execSQL(DROP_TABLE_USER);
            Log.d("Main","User Dropped");

            sqLiteDatabase.execSQL(DROP_TABLE_FIRM);
            Log.d("Main","Firm Dropped");

            sqLiteDatabase.execSQL(DROP_TABLE_TRANSACTION);
            Log.d("Main","Transaction Dropped");

            sqLiteDatabase.execSQL(DROP_TABLE_TRANSACTION_ENTRY);
            Log.d("Main","TransactionEntry Dropped");

            Message.message(context," Tables Droped \n onCreate Will be called Now");
            onCreate(sqLiteDatabase);*/

            // sqLiteDatabase.execSQL(DROP_TABLE_DUE_PAYMENTS);
            //     Log.d("Main","Table Dropped : -> "+DROP_TABLE_DUE_PAYMENTS);
            onCreate(sqLiteDatabase);
        } catch (Exception e) {
            Message.message(context, "" + e);
            Log.d("Main", "" + e);
        }
    }
}
