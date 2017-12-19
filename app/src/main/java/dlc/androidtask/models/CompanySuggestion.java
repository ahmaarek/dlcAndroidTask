package dlc.androidtask.models;

import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by ahmedmaarek on 19/12/2017.
 */

public class CompanySuggestion implements SearchSuggestion {
    private String company;
    private String domain;
    private String logo;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public CompanySuggestion(Company company){
        this.company=company.getName();
        this.domain=company.getDomain();
        this.logo=company.getLogoURL();
    }


    public CompanySuggestion(Parcel source) {
        this.company=source.readString();
        this.domain=source.readString();
        this.logo=source.readString();
    }

    public static final Creator<CompanySuggestion> CREATOR = new Creator<CompanySuggestion>() {
        @Override
        public CompanySuggestion createFromParcel(Parcel in) {
            return new CompanySuggestion(in);
        }

        @Override
        public CompanySuggestion[] newArray(int size) {
            return new CompanySuggestion[size];
        }
    };


    @Override
    public String getBody() {
        return this.company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(company);
        parcel.writeString(domain);
        parcel.writeString(logo);
    }
}
