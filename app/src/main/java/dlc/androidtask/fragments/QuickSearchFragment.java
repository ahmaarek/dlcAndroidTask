package dlc.androidtask.fragments;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import org.jdeferred.DoneCallback;

import java.util.ArrayList;

import dlc.androidtask.Network.CompaniesAPI;
import dlc.androidtask.R;
import dlc.androidtask.models.Company;
import dlc.androidtask.models.CompanySuggestion;

import static dlc.androidtask.Network.CompaniesAPI.searchCompany;

public class QuickSearchFragment extends Fragment {

    FloatingSearchView searchView;
    ImageView imageView;
    TextView nameTextView, domainTextView;
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
        searchView = (FloatingSearchView) view.findViewById(R.id.quickSearch);
        nameTextView = (TextView)view.findViewById(R.id.companyName);
        domainTextView= (TextView)view.findViewById(R.id.companyDomain);
        imageView = (ImageView)view.findViewById(R.id.quickSearchImage);
        setupSearchView();
        return view;
    }
    private static ArrayList<CompanySuggestion> createSuggestions(ArrayList<Company> companies){
        ArrayList<CompanySuggestion> suggestions = new ArrayList<>();

        for (Company company: companies) {
            suggestions.add(new CompanySuggestion(company));
        }

        return suggestions;


    }

    private void setupSearchView(){

        searchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                CompaniesAPI.searchCompany(newQuery, getContext()).done(new DoneCallback<ArrayList<Company>>() {
                    @Override
                    public void onDone(ArrayList<Company> result) {

                        searchView.swapSuggestions(createSuggestions(result));;
                    }
                });
            }
        });


        searchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                final DrawableRequestBuilder request = Glide.with(getContext()).load(((CompanySuggestion)searchSuggestion).getLogo()).dontAnimate().fitCenter();

                imageView.setRotationY(0f);
                imageView.animate().rotationY(90f).setListener(new Animator.AnimatorListener()
                {

                    @Override
                    public void onAnimationStart(Animator animation)
                    {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation)
                    {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        request.into(imageView);
                        imageView.setRotationY(270f);
                        imageView.animate().rotationY(360f).setListener(null);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation)
                    {
                    }
                });

                nameTextView.setText(((CompanySuggestion)searchSuggestion).getCompany());
                domainTextView.setText(((CompanySuggestion)searchSuggestion).getDomain());
                searchView.clearSuggestions();
                searchView.clearQuery();
                searchView.clearSearchFocus();
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });

        searchView.setSearchHint("Search Image");
    }
}
