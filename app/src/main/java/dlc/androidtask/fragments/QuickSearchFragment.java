package dlc.androidtask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import dlc.androidtask.R;

public class QuickSearchFragment extends Fragment {

    SearchView searchView;
    public QuickSearchFragment() {
        // Required empty public constructor
    }
    public static QuickSearchFragment newInstance() {
        QuickSearchFragment fragment = new QuickSearchFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quick_search, container, false);
        searchView = (SearchView)view.findViewById(R.id.quickSearch);
        searchView.setQueryHint("Search Image");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getContext(), query, Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(getContext(), newText, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return view;
    }

}
