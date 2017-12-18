package dlc.androidtask.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ListView;

import java.util.List;

import dlc.androidtask.fragments.ListViewFragment;
import dlc.androidtask.fragments.QuickSearchFragment;

/**
 * Created by ahmedmaarek on 13/12/2017.
 */

public class SearchPageAdapter extends FragmentPagerAdapter {

    public SearchPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return QuickSearchFragment.newInstance();//return quick search fragment
        }
        else
            return ListViewFragment.newInstance(); //return normal search fragment
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Quick Search";
        }
        else{
            return "List View";
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
