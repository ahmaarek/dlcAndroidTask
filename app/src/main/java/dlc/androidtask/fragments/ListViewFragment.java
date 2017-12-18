package dlc.androidtask.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import dlc.androidtask.R;
import dlc.androidtask.adapters.CompanyAdapter;
import dlc.androidtask.models.Company;

public class ListViewFragment extends Fragment {
    RecyclerView companyRecyclerView;

    public ListViewFragment() {
        // Required empty public constructor
    }


    public static ListViewFragment newInstance() {
        ListViewFragment fragment = new ListViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
        companyRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        CompanyAdapter companyAdapter = new CompanyAdapter(Glide.with(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        companyRecyclerView.setLayoutManager(layoutManager);
        companyRecyclerView.setAdapter(companyAdapter);
        return view;
    }

}
