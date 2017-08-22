package mantoo.dbcent.mantoo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import mantoo.dbcent.mantoo.R;

/**
 * Created by dbcent91 on 21/7/17.
 */

public class Payments extends Fragment implements View.OnClickListener {

    Button openDialog;
    FragmentManager fm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_payments, container, false);

        fm =  getActivity().getSupportFragmentManager();
        openDialog = (Button) rootView.findViewById(R.id.openDialog_payments);
        openDialog.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Payments");
    }

    @Override
    public void onClick(View view) {

       /* AlertDialog.Builder mbuilder = new AlertDialog.Builder(getActivity());
        View layoutView = getActivity().getLayoutInflater().inflate(R.layout.fragment_sells,null);
        mbuilder.setView(layoutView);
        AlertDialog dialog = mbuilder.create();
        dialog.show();*/

        Sells dFragment = new Sells();
        // Show DialogFragment
        dFragment.show(fm, "Dialog Fragment");
    }
}
