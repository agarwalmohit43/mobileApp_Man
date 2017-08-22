package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Interface.Customer;
import mantoo.dbcent.mantoo.R;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class CustomerData implements Customer {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public CustomerData(Context context) {
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }


    //Customer Dummy id -> e0088445-63f5-443f-bfbc-b6a9ad23e684

    public void addPartiesvalue() {
        sqLiteDatabaseObj.beginTransaction();

        try {
            String name = "Customer number -> ";
            for (int i = 1; i <= 2000; i++) {

                UUID partyId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();

                ContentValues contentValues = new ContentValues();

                contentValues.put("id", partyId.toString());
                contentValues.put("mantooId", "NULL");
                contentValues.put("name", name + i);
                contentValues.put("address", "Pune");
                contentValues.put("phoneNumber", "8981871984");
                contentValues.put("dueAmount", 20500);
                contentValues.put("sideDays", 30);
                contentValues.put("createdAt", millisecond);
                contentValues.put("updatedAt", millisecond);

                Log.d("Customer", name + i);
                sqLiteDatabaseObj.insert("parties", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Log.d("Customer", "Customer Inserted Successfully");
        } catch (Exception e) {
            // Message.message(context, "Un-Successfull");
            Log.d("Customer", "" + e);
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }

    @Override
    public void addPartiesDummyvalue() {

        sqLiteDatabaseObj.beginTransaction();

        try {
            ContentValues contentValues = new ContentValues();

            UUID partyId = UUID.randomUUID();

            long millisecond = System.currentTimeMillis();

            contentValues.put("id", partyId.toString());
            contentValues.put("mantooId", "NULL");
            contentValues.put("name", "self");
            contentValues.put("address", "Pune");
            contentValues.put("phoneNumber", "SELF089");
            contentValues.put("dueAmount", 50000);
            contentValues.put("sideDays", 30);
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            Log.d("Customer", partyId.toString());
            sqLiteDatabaseObj.insert("parties", null, contentValues);

            sqLiteDatabaseObj.setTransactionSuccessful();
            Log.d("Customer", "Dummy Created SuccessFully");
        } catch (Exception e) {
            Log.d("Customer", "" + e);
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }
    }

    @Override
    public void addCustomer(ContentValues contentValues) {
        sqLiteDatabaseObj.beginTransaction();

        try {

            UUID partyId = UUID.randomUUID();
            UUID mantooId = UUID.randomUUID();
            long millisecond = System.currentTimeMillis();


            contentValues.put("id", partyId.toString());
            contentValues.put("mantooId", mantooId.toString());
            contentValues.put("sideDays", 30);
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            sqLiteDatabaseObj.insert("parties", null, contentValues);

            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Successfull");
        } catch (Exception e) {
            Message.message(context, "Un-Successfull");
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }

    public ArrayList<CustomerInformation> getPartyList() {
        ArrayList<CustomerInformation> customerInformation = new ArrayList<>();

        String[] columns = {"id", "mantooId", "name", "address", "dueAmount", "phoneNumber", "sideDays", "createdAt"};
        Cursor cursor = sqLiteDatabaseObj.query("parties", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            CustomerInformation obj = new CustomerInformation();
            obj.setCustomerId(cursor.getString(0));
            obj.setCustomerMantooId(cursor.getString(1));
            obj.setCustomerName(cursor.getString(2));
            obj.setCustomerAddress(cursor.getString(3));
            obj.setCustomerBalance(cursor.getDouble(4));
            obj.setCustomerContact(cursor.getString(5));
            obj.setSideDays(cursor.getInt(6));
            obj.setCurrentDate(cursor.getLong(7));

            //Log.d("Customer",cursor.getString(0));
            customerInformation.add(obj);
        }
        return customerInformation;
    }

    public void updateParty(ContentValues contentValues, String partyId) {

        sqLiteDatabaseObj.beginTransaction();
        try {
            contentValues.put("sideDays", 30);

            sqLiteDatabaseObj.update("parties", contentValues, "id='" + partyId + "'", null);
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Update Successfull");

        } catch (Exception e) {
            Log.d("Customer", "" + e);
            Message.message(context, "Unabel to update");

        } finally {
            sqLiteDatabaseObj.endTransaction();
        }
    }

}
