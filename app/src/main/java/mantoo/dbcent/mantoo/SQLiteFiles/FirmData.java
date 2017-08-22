package mantoo.dbcent.mantoo.SQLiteFiles;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.UUID;

import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Interface.Firm;

/**
 * Created by dbcent91 on 28/7/17.
 */

public class FirmData implements Firm {

    private SQLiteDatabase sqLiteDatabaseObj;
    private SchemaDefinition schemaDefinitionObj;

    private Context context;

    public FirmData(Context context) {
        this.context = context;
        schemaDefinitionObj = new SchemaDefinition(context);
        sqLiteDatabaseObj = schemaDefinitionObj.getWritableDatabase();
    }

    // ********* Firm (id -> c566f43c-b1cc-4520-ab06-ee312f5fb054,name -> Firm -> 1) *********

    @Override
    public void addFirm() {


        try {
            UUID firmId = UUID.randomUUID();
            long millisecond = System.currentTimeMillis();

            ContentValues contentValues = new ContentValues();
            contentValues.put("id", firmId.toString());
            contentValues.put("name", "Firm -> 1");
            contentValues.put("mantooId", "NULL");
            contentValues.put("userId", "1e8b0b7b-5fb9-4f2d-a4c6-6f367846c90c");
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            Log.d("Firm", firmId.toString());

            sqLiteDatabaseObj.insert("firm", null, contentValues);

            Message.message(context, "Firm Data Inserted Successfully");
        } catch (Exception e) {
            Message.message(context, "Firm Data Inserted -> Un-Successfull");
        }

    }
}
