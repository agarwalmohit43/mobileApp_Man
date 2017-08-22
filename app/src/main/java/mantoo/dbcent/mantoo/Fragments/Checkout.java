package mantoo.dbcent.mantoo.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mantoo.dbcent.mantoo.CustomAdapters.CheckoutAdapter;
import mantoo.dbcent.mantoo.CustomAdapters.InventoryAdapter;
import mantoo.dbcent.mantoo.Extra.Message;
import mantoo.dbcent.mantoo.Information.CustomerInformation;
import mantoo.dbcent.mantoo.Information.TransactionEntryInfo;
import mantoo.dbcent.mantoo.Information.TransactionsInformation;
import mantoo.dbcent.mantoo.R;
import mantoo.dbcent.mantoo.SQLiteFiles.CustomerData;
import mantoo.dbcent.mantoo.SQLiteFiles.DuePaymentsData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionEntryData;
import mantoo.dbcent.mantoo.SQLiteFiles.TransactionsData;

/**
 * Created by dbcent91 on 31/7/17.
 */

public class Checkout extends DialogFragment implements View.OnClickListener {


    AutoCompleteTextView customerTextView;
    TextView totalAmountDis;
    RecyclerView recyclerView;
    Toolbar toolbar;

    TransactionEntryData transactionEntryDataObj;
    CheckoutAdapter mCheckoutAdapter;

    Double totalAmount;

    Button submitCart, addItem_CheckOut, addMoreItem_CheckOut;

    String userId;


    CustomerData customerDataObj;
    Map<String, CustomerInformation> customerInformationMap;

    TransactionsData transactionsDataObj;


    TransactionsInformation transactionsInformationObj;
    TransactionsData transactionsDataobj;
    Map<String, TransactionsInformation> transactionsInformationMap;

    DuePaymentsData duePaymentsDataObj;

    ArrayList<TransactionEntryInfo> transactionEntryInfosList;

    FragmentManager mFragmentManager;

    LinearLayout mRecyclerViewLinearLayout, submitTotalAmount_Linearlayout, addItem_checkout_LinearLayout;
    //CoordinatorLayout cordinatorCheckout;

    Fragment mfragment;
    FragmentTransaction mfragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);


        mFragmentManager = getActivity().getSupportFragmentManager();
        mfragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        mRecyclerViewLinearLayout = (LinearLayout) rootView.findViewById(R.id.checkout_addmoreItems_LinearLayout);
        submitTotalAmount_Linearlayout = (LinearLayout) rootView.findViewById(R.id.submitTotalAmount_Linearlayout);
        addItem_checkout_LinearLayout = (LinearLayout) rootView.findViewById(R.id.addItem_checkout_LinearLayout);
        // cordinatorCheckout = (CoordinatorLayout) rootView.findViewById(R.id.cordinatorCheckout);

        customerTextView = (AutoCompleteTextView) rootView.findViewById(R.id.customerAutoCompleteCheckout);

        addItem_CheckOut = (Button) rootView.findViewById(R.id.addItem_checkout);
        addItem_CheckOut.setOnClickListener(this);

        addMoreItem_CheckOut = (Button) rootView.findViewById(R.id.addMoreItems_checkout);
        addMoreItem_CheckOut.setOnClickListener(this);

        transactionEntryInfosList = new ArrayList<>();

        totalAmountDis = (TextView) rootView.findViewById(R.id.totalAmount_cart);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.checkout_recycler);
        submitCart = (Button) rootView.findViewById(R.id.cart_Submit);
        submitCart.setOnClickListener(this);


        duePaymentsDataObj = new DuePaymentsData(getActivity());

        customerDataObj = new CustomerData(getActivity());
        customerInformationMap = new HashMap<>();

        for (CustomerInformation obj : customerDataObj.getPartyList()) {
            customerInformationMap.put(obj.getCustomerName(), obj);
        }


        transactionsDataobj = new TransactionsData(getActivity());
        transactionsInformationMap = new HashMap<>();

        for (TransactionsInformation obj : transactionsDataobj.getTransactionData()) {
            transactionsInformationMap.put(obj.getPartyId(), obj);
        }

        transactionsInformationObj = new TransactionsInformation();
        transactionsInformationObj = transactionsDataobj.getTransactionDataWithStatus();

        transactionEntryDataObj = new TransactionEntryData(getActivity());

        totalAmount = 0.00;

        for (TransactionEntryInfo obj : transactionEntryDataObj.getTxnEntry()) {
            totalAmount += obj.getAmount();
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Checkout");

        ArrayAdapter partyAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, customerInformationMap.keySet().toArray());
        customerTextView.setAdapter(partyAdapter);
        customerTextView.setThreshold(1);

        transactionEntryInfosList = transactionEntryDataObj.getTxnEntry();

        if (transactionEntryInfosList.size() != 0) {

            mRecyclerViewLinearLayout.setVisibility(View.VISIBLE);
            submitTotalAmount_Linearlayout.setVisibility(View.VISIBLE);
            customerTextView.setVisibility(View.VISIBLE);
            addItem_checkout_LinearLayout.setVisibility(View.GONE);

            mCheckoutAdapter = new CheckoutAdapter(getActivity(), transactionEntryInfosList);


            recyclerView.setAdapter(mCheckoutAdapter);
        } else {


            Message.message(getActivity(), "Cart empty");
        }


        totalAmountDis.setText("Total: " + String.format("%.2f", totalAmount) + "");
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.cart_Submit:
                hideKeyboard();
                cartSubmit();
                dissDialog();

                break;
            case R.id.addItem_checkout:
                addItem_CheckOut();
                break;
            case R.id.addMoreItems_checkout:
                addItem_CheckOut();
                break;
        }
    }

    private void dissDialog() {
        dismiss();
        mfragment = new Checkout();
        //mfragment.setArguments(sendinfo);

        mfragmentTransaction.replace(R.id.content_main, mfragment);
        mfragmentTransaction.addToBackStack(null);
        mfragmentTransaction.commit();
    }

    private void addItem_CheckOut() {
        Sells dFragment = new Sells();
        // Show DialogFragment
        dFragment.show(mFragmentManager, "Dialog Fragment");
    }

    private void cartSubmit() {

        String customerEditext = customerTextView.getText().toString().trim();
        if (customerEditext.length() != 0) {
            cartSubmitOnValidation();
            Message.message(getActivity(), "Submitted");
        } else {
            //  customerTextView.setError("Please Select party");
            Message.message(getActivity(), "Please Select party");
            // Snackbar.make(cordinatorCheckout,"Please Select party",Snackbar.LENGTH_LONG).show();
        }


    }


    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void cartSubmitOnValidation() {

        //    String userId=customerInformationMap.get(getArguments().get("customerName")).getCustomerId();
        //  Message.message(getActivity(),userId);
        for (TransactionEntryInfo obj : transactionEntryDataObj.getTxnEntry()) {

            long millisecond = System.currentTimeMillis();
            ContentValues contentValues = new ContentValues();

            contentValues.put("id", obj.getTransactionsId());
            contentValues.put("partyId", customerInformationMap.get(customerTextView.getText().toString()).getCustomerId());
            contentValues.put("firmId", "291e0f38-0056-4624-8962-5cbbc59e15fd");
            contentValues.put("Date", millisecond);
            contentValues.put("amount", totalAmount);
            contentValues.put("additionalDiscount", 0);
            contentValues.put("status", "submit");
            contentValues.put("comment", "Data Submitted");
            contentValues.put("recordLocation", "NULL");
            contentValues.put("createdAt", millisecond);
            contentValues.put("updatedAt", millisecond);

            Log.d("DuePayments", obj.getTransactionsId());

            transactionsDataobj.updateTransaction(contentValues, obj.getTransactionsId());
            //Message.message(getActivity(),obj.getTransactionsId());

        }


        duePayments();

        transactionsDataobj.createTransactions();
    }

    public void duePayments() {
        //DueDate Calculation
        int sideDays = customerInformationMap.get(customerTextView.getText().toString()).getSideDays();
        // long transactionOccuredDate = transactionsInformationMap.get(customerInformationMap.get(customerTextView.getText().toString()).getCustomerId()).getCreatedAt();
        long millisecond = System.currentTimeMillis();

        ContentValues contentValuesDuePayments = new ContentValues();

        contentValuesDuePayments.put("partyId", customerInformationMap.get(customerTextView.getText().toString()).getCustomerId());
        contentValuesDuePayments.put("totalAmount", totalAmount);
        contentValuesDuePayments.put("dueDate", (millisecond + (sideDays * 86400000l)));
        contentValuesDuePayments.put("transactionId", transactionsInformationObj.getId().toString());

        duePaymentsDataObj.createDuePayments(contentValuesDuePayments);


    }


}
