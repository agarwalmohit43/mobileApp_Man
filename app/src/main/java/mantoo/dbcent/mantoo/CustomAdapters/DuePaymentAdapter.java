package mantoo.dbcent.mantoo.CustomAdapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.DuePaymentInfo;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;


public class DuePaymentAdapter extends RecyclerView.Adapter<DuePaymentAdapter.MyViewHolder> {


    Context context;
    ArrayList<DuePaymentInfo> duePaymentInfosList;
    LayoutInflater inflater;

    CustomerData customerDataObj;
    Map<String, CustomerInformation> customerInformationMap;

    TransactionsData transactionsDataObj;
    Map<String, TransactionsInformation> transactionsInformationMap;

    public DuePaymentAdapter(Context context, ArrayList<DuePaymentInfo> duePayments) {
        this.context = context;
        this.duePaymentInfosList = duePayments;
        this.inflater = LayoutInflater.from(context);

        customerDataObj = new CustomerData(context);
        customerInformationMap = new HashMap<>();

        for (CustomerInformation obj : customerDataObj.getPartyList()) {
            customerInformationMap.put(obj.getCustomerId(), obj);
        }

        transactionsDataObj = new TransactionsData(context);
        transactionsInformationMap = new HashMap<>();

        for (TransactionsInformation obj : transactionsDataObj.getTransactionData()) {
            transactionsInformationMap.put(obj.getId(), obj);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_row_duepayment, parent, false);

        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Date date = new Date(duePaymentInfosList.get(position).getDueDate());

        holder.partyName.setText(customerInformationMap.get(duePaymentInfosList.get(position).getPartyId()).getCustomerName());
        holder.transactionId.setText("TXN" + transactionsInformationMap.get(duePaymentInfosList.get(position).getTransactionId()).getTxnNumber());
        holder.totalAmount.setText(duePaymentInfosList.get(position).getTotalAmount() + "");
        holder.dueDate.setText(date + "");


    }

    @Override
    public int getItemCount() {
        return duePaymentInfosList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView partyName, transactionId, totalAmount, dueDate;

        public MyViewHolder(View itemView) {
            super(itemView);

            partyName = (TextView) itemView.findViewById(R.id.party_duePayments);
            transactionId = (TextView) itemView.findViewById(R.id.transactionId_duePayments);
            totalAmount = (TextView) itemView.findViewById(R.id.totalAmount_duePayments);
            dueDate = (TextView) itemView.findViewById(R.id.duedate_duePayments);
        }
    }
}
