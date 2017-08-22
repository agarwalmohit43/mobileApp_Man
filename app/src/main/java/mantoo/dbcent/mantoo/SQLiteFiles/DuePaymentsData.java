package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import mantoo.dbcent.mantoo.Information.DuePaymentInfo;
import mantoo.dbcent.mantoo.Interface.DuePayments;

/**
 * Created by dbcent91 on 2/8/17.
 */

public class DuePaymentsData implements DuePayments {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public DuePaymentsData(Context context) {
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    @Override
    public void createDuePayments(ContentValues contentValues) {

        sqLiteDatabaseObj.beginTransaction();

        try {

            UUID duePaymentsId = UUID.randomUUID();

            long millisecond = System.currentTimeMillis();

            contentValues.put("id", duePaymentsId.toString());
            contentValues.put("mantooId", "NULL");
            contentValues.put("balance", 0);
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            Log.d("DuePayments", duePaymentsId.toString());

            sqLiteDatabaseObj.insert("duePayments", null, contentValues);
            sqLiteDatabaseObj.setTransactionSuccessful();

            Log.d("DuePayments", "Records inserted");
            Log.d("DuePayments", contentValues.get("dueDate").toString());
            Log.d("DuePayments", contentValues.get("transactionId").toString());


        } catch (Exception e) {
            Log.d("DuePayments", "" + e);
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }
    }

    @Override
    public ArrayList<DuePaymentInfo> getDuePayments() {
        ArrayList<DuePaymentInfo> duePaymentInfosList = new ArrayList<>();

        String[] columns = {"id", "mantooId", "partyId", "totalAmount", "dueDate", "transactionId", "balance", "createdAt"};
        Cursor cursor = sqLiteDatabaseObj.query("duePayments", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            DuePaymentInfo obj = new DuePaymentInfo();

            obj.setId(cursor.getString(0));
            obj.setMantooId(cursor.getString(1));
            obj.setPartyId(cursor.getString(2));
            obj.setTotalAmount(cursor.getDouble(3));
            obj.setDueDate(cursor.getLong(4));
            obj.setTransactionId(cursor.getString(5));
            obj.setBalance(cursor.getDouble(6));
            obj.setCreatedAt(cursor.getLong(7));

            duePaymentInfosList.add(obj);
        }

        return duePaymentInfosList;
    }

    @Override
    public ArrayList<DuePaymentInfo> getDuePaymentsWithParty(String partyId) {
        ArrayList<DuePaymentInfo> duePaymentInfosList = new ArrayList<>();

        String[] columns = {"id", "mantooId", "partyId", "totalAmount", "dueDate", "transactionId", "balance", "createdAt"};
        Cursor cursor = sqLiteDatabaseObj.query("duePayments", columns, "partyId = '" + partyId + "'", null, null, null, null);

        while (cursor.moveToNext()) {
            DuePaymentInfo obj = new DuePaymentInfo();

            obj.setId(cursor.getString(0));
            obj.setMantooId(cursor.getString(1));
            obj.setPartyId(cursor.getString(2));
            obj.setTotalAmount(cursor.getDouble(3));
            obj.setDueDate(cursor.getLong(4));
            obj.setTransactionId(cursor.getString(5));
            obj.setBalance(cursor.getDouble(6));
            obj.setCreatedAt(cursor.getLong(7));

            duePaymentInfosList.add(obj);
        }

        return duePaymentInfosList;
    }

    @Override
    public void updateDuePayments(ContentValues contentValues) {


    }
}
