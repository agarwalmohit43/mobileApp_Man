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
import mantoo.dbcent.mantoo.Information.InventoryInformation;
import mantoo.dbcent.mantoo.Interface.Inventory;

/**
 * Created by dbcent91 on 26/7/17.
 */

public class InventoryData implements Inventory {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public InventoryData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }



    /*
        08-02 11:26:16.800 26272-26272/mantoo.dbcent.mantoo D/Inventory: d156826c-61d2-46ce-9330-bc0eb713932d	     1
        08-02 11:26:16.800 26272-26272/mantoo.dbcent.mantoo D/Inventory: 84387a28-4778-438a-bf24-45c9113024a7	     2
        08-02 11:26:16.801 26272-26272/mantoo.dbcent.mantoo D/Inventory: f1af41cd-1d07-49d6-91dc-1dd5a99acc07	     3
        08-02 11:26:16.801 26272-26272/mantoo.dbcent.mantoo D/Inventory: 377834f0-f388-44bf-a72c-bd07381edf53	     4
        08-02 11:26:16.801 26272-26272/mantoo.dbcent.mantoo D/Inventory: bdbd0f88-1273-4865-8a85-ee71216c779b	     5
        08-02 11:26:16.802 26272-26272/mantoo.dbcent.mantoo D/Inventory: 621a8d6b-8320-4563-8504-ea88961b7dcd	     6
        08-02 11:26:16.802 26272-26272/mantoo.dbcent.mantoo D/Inventory: 6e63aae6-398b-4868-b0b3-742a81f0ed92	     7
        08-02 11:26:16.802 26272-26272/mantoo.dbcent.mantoo D/Inventory: ca930f33-20c8-4086-9c97-9b1e26651616	     8
        08-02 11:26:16.802 26272-26272/mantoo.dbcent.mantoo D/Inventory: 153b355b-bea2-4331-b21d-aa76e2aa6858	     9
        08-02 11:26:16.802 26272-26272/mantoo.dbcent.mantoo D/Inventory: 50ae5130-096a-422e-a89d-c382fe77152b	     10
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: 0576584f-4d9b-4f57-ab5d-4720575aa98f	     11
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: d6edb07d-6116-43cf-a45e-304b2b5ce103	     12
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: b851d2a6-6645-4715-a166-efb3d10d9348	     13
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: 5f52b8bc-1f59-432e-a99a-fc48cef81466	     14
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: 90eb2e92-9bb7-4008-995d-514a2723bb23	     15
        08-02 11:26:16.803 26272-26272/mantoo.dbcent.mantoo D/Inventory: f6b19b71-47e4-4305-b4f0-19b59da8e3d9	     16
        08-02 11:26:16.804 26272-26272/mantoo.dbcent.mantoo D/Inventory: fa0e3d22-56ed-4b15-a734-7221dda1dd40	     17
        08-02 11:26:16.804 26272-26272/mantoo.dbcent.mantoo D/Inventory: e3770ae8-e522-4c2f-8dd4-edaec4183331	     18
        08-02 11:26:16.804 26272-26272/mantoo.dbcent.mantoo D/Inventory: 6730f5a0-9695-4c38-90c6-bd5689f63f7b	     19
        08-02 11:26:16.804 26272-26272/mantoo.dbcent.mantoo D/Inventory: 1b20b965-55af-4363-a5dc-d293ad3b83bd	     20
    * */

    @Override
    public void addInventoryValues() {

        sqLiteDatabaseObj.beginTransaction();

        try {
            String name = "Inventory number -> ";
            for (int i = 1; i <= 2000; i++) {

                ContentValues contentValues = new ContentValues();

                UUID inventoryId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();

                contentValues.put("id", inventoryId.toString());
                contentValues.put("mantooId", "NULL");
                contentValues.put("name", name + i);
                contentValues.put("firmId", "NULL");
                contentValues.put("mantooProductid", "NULL");
                contentValues.put("discount", 4.3);
                contentValues.put("tax", 14.5);
                contentValues.put("gstRate", 28.5);
                contentValues.put("rate", 100);
                contentValues.put("mrp", 150);
                contentValues.put("purcahsePrice", 75);
                contentValues.put("createdAt", millisecond);
                contentValues.put("updatedAt", millisecond);

                Log.d("Inventory", inventoryId.toString() + "\t     "+i);
                sqLiteDatabaseObj.insert("inventory", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Log.d("Inventory", "Inventory Data Inserted Successfuly");

        } catch (Exception e)
        {
            Log.d("Inventory", ""+e);
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }

   /* public void addDiscount() {

        sqLiteDatabaseObj.beginTransaction();

        try {

                ContentValues contentValues = new ContentValues();

                UUID inventoryId = UUID.randomUUID();
                long millisecond = System.currentTimeMillis();


                contentValues.put("updatedAt", millisecond);

                sqLiteDatabaseObj.insert("inventory", null, contentValues);
            }
            sqLiteDatabaseObj.setTransactionSuccessful();
            Message.message(context, "Successfull");
        } catch (Exception e) {
            Message.message(context, "Un-Successfull");
        } finally {
            sqLiteDatabaseObj.endTransaction();
        }

    }*/

    @Override
    public void addInventory(ContentValues contentValues) {




    }

    @Override
    public ArrayList<InventoryInformation> getInventoryList() {

        ArrayList<InventoryInformation> inventoryInformationsList = new ArrayList<>();

        String[] columns = {"id", "name", "tax", "gstRate", "rate", "mrp", "purcahsePrice", "discount"};
        Cursor cursor = sqLiteDatabaseObj.query("inventory", columns, null, null, null, null, null);

        while (cursor.moveToNext()) {

            InventoryInformation obj=new InventoryInformation();
            obj.setId(cursor.getString(0));
            obj.setInventoryName(cursor.getString(1));
            obj.setTax(cursor.getDouble(2));
            obj.setGstRate(cursor.getDouble(3));
            obj.setRate(cursor.getDouble(4));
            obj.setMrp(cursor.getDouble(5));
            obj.setPurcahsePrice(cursor.getDouble(6));
            obj.setDiscount(cursor.getDouble(7));

            Log.d("Inventory",cursor.getString(0));
            inventoryInformationsList.add(obj);

        }
        return inventoryInformationsList;
    }



    @Override
    public void updateInventory(ContentValues contentValues, String partyId) {

    }
}
