package dlc.androidtask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import dlc.androidtask.R;
import dlc.androidtask.fragments.ListViewFragment;
import dlc.androidtask.models.Company;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    private ArrayList<Company> companies;
    private final RequestManager glide;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.nameLine);
            txtFooter = (TextView) v.findViewById(R.id.domainLine);
            icon = (ImageView) v.findViewById(R.id.icon);
        }
    }

    public void add(Company item) {
        companies.add(item);
        notifyDataSetChanged();
    }

    public void setList(ArrayList<Company> companies) {
        this.companies.clear();
        this.companies.addAll(companies);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        companies.remove(position);
        notifyItemRemoved(position);
    }

    public CompanyAdapter(RequestManager glide) {
        companies = new ArrayList<Company>();
        this.glide = glide;
    }

    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.company_row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Company company = companies.get(position);
        holder.txtHeader.setText(company.getName());
        holder.txtFooter.setText(company.getDomain());
        if (company.getLogoURL() != "")
            glide.load(company.getLogoURL()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

}

