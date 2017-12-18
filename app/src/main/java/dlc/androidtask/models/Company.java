package dlc.androidtask.models;

/**
 * Created by ahmedmaarek on 18/12/2017.
 */

public class Company {
    private String name;
    private String domain;
    private String logoURL;

    public String getName() {
        return name;
    }

    public String getDomain() {
        return domain;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public Company(String name, String domain, String logoURL){
        this.name = name;
        this.domain = domain;
        this.logoURL =  logoURL;


    }
}
