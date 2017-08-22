package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.Interface.Transactions;

/**
 * Created by dbcent91 on 31/7/17.
 */

public class TransactionsData implements Transactions {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public TransactionsData(Context context){
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    //Submitted Transaction Id: 785e4b18-8809-47b6-af51-82eaeb575c87

    //Defaut e53bec3f-0604-46b4-9aef-8020397a40b7

    @Override
    public void createTransactions() {

       try{
           Random random = new Random();
           int ramdom = random.nextInt((100000 - 0) + 1) + 0;

           UUID transactionId = UUID.randomUUID();
           long millisecond = System.currentTimeMillis();
           ContentValues contentValues = new ContentValues();

           contentValues.put("id",transactionId.toString());
           contentValues.put("txnNumber",ramdom);
           contentValues.put("partyId","e0088445-63f5-443f-bfbc-b6a9ad23e684");
           contentValues.put("firmId","c566f43c-b1cc-4520-ab06-ee312f5fb054");
           contentValues.put("Date",millisecond);
           contentValues.put("amount",0);
           contentValues.put("additionalDiscount",0);
           contentValues.put("status","cart");
           contentValues.put("comment","Dummy Data");
           contentValues.put("recordLocation","NULL");
           contentValues.put("createdAt",millisecond);
           contentValues.put("updatedAt",millisecond);

           Log.d("Transactions", transactionId.toString());
           sqLiteDatabaseObj.insert("transactions",null,contentValues);
       }catch (Exception e){
           Message.message(context,""+e);
       }

    }

    @Override
    public void updateTransaction(ContentValues contentValues, String transactionId) {

        try{
            long millisecond = System.currentTimeMillis();
            sqLiteDatabaseObj.update("transactions",contentValues,"id = '"+transactionId+"'",null);
            Log.d("Transactions","Updated Successfully");
        }catch (Exception e){
            Message.message(context,""+e);
        }

    }


    @Override
    public void updateTransaction() {

        try{
            long millisecond = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();

            contentValues.put("txnNumber","001");
            contentValues.put("updatedAt",millisecond);

            sqLiteDatabaseObj.update("transactions",contentValues,"id = '785e4b18-8809-47b6-af51-82eaeb575c87'",null);
            Log.d("Transactions","Updated Successfully");
        }catch (Exception e){
            Message.message(context,""+e);
        }

    }

    @Override
    public ArrayList<TransactionsInformation> getTransactionData() {

        ArrayList<TransactionsInformation> transactionsInformations = new ArrayList<>();

        String[] columns={"id","txnNumber","partyId","firmId","Date","amount","additionalDiscount","status","comment","createdAt"};

        Cursor cursor=sqLiteDatabaseObj.query("transactions",columns,"status != 'cart'",null,null,null,null);
        while (cursor.moveToNext()){
            TransactionsInformation obj = new TransactionsInformation();
            obj.setId(cursor.getString(0));
            obj.setTxnNumber(cursor.getString(1));
            obj.setPartyId(cursor.getString(2));
            obj.setFirmId(cursor.getString(3));

            obj.setAmount(cursor.getDouble(5));
            obj.setAdditionalDiscount(cursor.getDouble(6));
            obj.setStatus(cursor.getString(7));
            obj.setComment(cursor.getString(8));
            obj.setCreatedAt(cursor.getLong(9));

            transactionsInformations.add(obj);
        }

        return transactionsInformations;
    }

    @Override
    public TransactionsInformation getTransactionDataWithStatus() {


        String[] columns={"id","txnNumber","partyId","firmId","Date","amount","additionalDiscount","status","comment","createdAt"};

        TransactionsInformation obj = new TransactionsInformation();
        try{
            Cursor cursor=sqLiteDatabaseObj.query("transactions",columns,"status = 'cart'",null,null,null,null);
            while (cursor.moveToNext()){

                obj.setId(cursor.getString(0));
                obj.setTxnNumber(cursor.getString(1));
                obj.setPartyId(cursor.getString(2));
                obj.setFirmId(cursor.getString(3));

                obj.setAmount(cursor.getDouble(5));
                obj.setAdditionalDiscount(cursor.getDouble(6));
                obj.setStatus(cursor.getString(7));
                obj.setComment(cursor.getString(8));
                obj.setCreatedAt(cursor.getLong(9));
                }
        }catch (Exception e){
            Log.d("Transactions",""+e);
        }

        return obj;
    }


}
