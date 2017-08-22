package mantoo.dbcent.mantoo.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mantoo.dbcent.mantoo.CustomAdapters.DuePaymentAdapter;
import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.DuePaymentInfo;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.DuePaymentsData;

/**
 * Created by dbcent91 on 2/8/17.
 */

public class DuePayment extends Fragment implements View.OnClickListener{


    RecyclerView recyclerView;

    Toolbar toolbar;


    DuePaymentAdapter mduePaymentAdapter;
    DuePaymentsData duePaymentsDataObj;

    AutoCompleteTextView customerTextView;
    Button searchCustomer;

    CustomerData customerDataObj;
    Map<String,CustomerInformation> customerInformationMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rooView= inflater.inflate(R.layout.fragment_duepayment, container, false);

        recyclerView = (RecyclerView) rooView.findViewById(R.id.duepayments_recycler);

        duePaymentsDataObj = new DuePaymentsData(getActivity());

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        customerTextView = (AutoCompleteTextView) rooView.findViewById(R.id.customer_Editext_duepayments);

        searchCustomer = (Button) rooView.findViewById(R.id.search_duepayments);
        searchCustomer.setOnClickListener(this);

        customerDataObj = new CustomerData(getActivity());
        customerInformationMap = new HashMap<>();

        for(CustomerInformation obj:customerDataObj.getPartyList()){
            customerInformationMap.put(obj.getCustomerName(),obj);
        }

        return rooView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("DuePayments");

        mduePaymentAdapter = new DuePaymentAdapter(getActivity(),duePaymentsDataObj.getDuePayments());

        recyclerView.setAdapter(mduePaymentAdapter);
        customerTextView.setHint("ex: Mohit");

        ArrayAdapter partyAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, customerInformationMap.keySet().toArray());
        customerTextView.setAdapter(partyAdapter);
        customerTextView.setThreshold(1);



    }

    @Override
    public void onClick(View view) {

        hideKeyboard();
        String customerEditext = customerTextView.getText().toString().trim();
        if(customerEditext.isEmpty() || customerEditext.length()==0 || customerEditext.equals("") || customerEditext == null){
           // Message.message(getActivity(),"Please enter the Party Name");
            customerTextView.setError("Please Enter the Party Name");

        }else {
            ArrayList<DuePaymentInfo> duePaymentInfos =new ArrayList<>();
            duePaymentInfos = duePaymentsDataObj.getDuePaymentsWithParty(customerInformationMap.get(customerEditext).getCustomerId());



            if(duePaymentInfos.size() != 0){
                mduePaymentAdapter = new DuePaymentAdapter(getActivity(),duePaymentInfos);
                recyclerView.setAdapter(mduePaymentAdapter);
            }else{
                customerTextView.setError("No Data Found for -> "+customerEditext);
            }

        }
    }

    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
