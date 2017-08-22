package mantoo.dbcent.mantoo.Interface;

import android.content.ContentValues;

import java.util.ArrayList;

import mantoo.dbcent.mantoo.Information.DuePaymentInfo;

/**
 * Created by dbcent91 on 2/8/17.
 */

public interface DuePayments {

    //create
    public void createDuePayments(ContentValues contentValues);
   // public void createDuePaymentsDefault();

    //read
    public ArrayList<DuePaymentInfo> getDuePayments();
    public ArrayList<DuePaymentInfo> getDuePaymentsWithParty(String partyId);

    //update
    public void updateDuePayments(ContentValues contentValues);


    //delete
}
