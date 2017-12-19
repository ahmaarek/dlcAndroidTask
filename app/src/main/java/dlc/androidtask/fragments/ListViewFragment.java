package dlc.androidtask.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.bumptech.glide.Glide;

import org.jdeferred.DoneCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dlc.androidtask.Network.CompaniesAPI;
import dlc.androidtask.R;
import dlc.androidtask.adapters.CompanyAdapter;
import dlc.androidtask.models.Company;

public class ListViewFragment extends Fragment {
    RecyclerView companyRecyclerView;
    SearchView searchView;

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
        final CompanyAdapter companyAdapter = new CompanyAdapter(Glide.with(this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        companyRecyclerView.setLayoutManager(layoutManager);
        companyRecyclerView.setAdapter(companyAdapter);

        searchView = (SearchView) view.findViewById(R.id.normalSearch);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("hey", "up");
                CompaniesAPI.searchCompany(s, getContext()).done(new DoneCallback<ArrayList<Company>>() {
                    @Override
                    public void onDone(ArrayList<Company> result) {
                        companyAdapter.setList(result);
                        companyAdapter.notifyDataSetChanged();
                        Log.d("hey","down");
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
//        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
//            @Override
//            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
//
//            }
//
//            @Override
//            public void onSearchAction(String currentQuery) {
//                Log.d("hey", "up");
//                CompaniesAPI.searchCompany(currentQuery, getContext()).done(new DoneCallback<ArrayList<Company>>() {
//                    @Override
//                    public void onDone(ArrayList<Company> result) {
//                        companyAdapter.setList(result);
//                        companyAdapter.notifyDataSetChanged();
//                        Log.d("hey","down");
//                    }
//                });
//            }
//        });



        return view;
    }

}
