package dlc.androidtask.Network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dlc.androidtask.models.Company;

/**
 * Created by ahmedmaarek on 18/12/2017.
 */

public class CompaniesAPI {
    private static final String API_BASE_URL = "https://autocomplete.clearbit.com/v1/companies/suggest?query=:";

    public static Promise<ArrayList<Company>, Exception, Object> searchCompany(final String query, final Context context) {
        String URL = API_BASE_URL + query;
        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        final Deferred<ArrayList<Company>, Exception, Object> deferred = new DeferredObject<>();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        deferred.resolve(JSONArrayToCompanies(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                deferred.reject(error);
            }
        });
        queue.add(request);
        return deferred.promise();
    }

    private static ArrayList<Company> JSONArrayToCompanies(JSONArray jsonArray){
        int length = jsonArray.length();
//        Company companies[] = new Company[length];
        ArrayList<Company> companies = new ArrayList<Company>();
        for (int i = 0; i < length; i++) {
            String name="",domain="",url="";
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                name = (String)object.get("name");
                domain = (String)object.get("domain");
                url = (String)object.get("logo");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            companies.add(new Company(name,domain,url));
        }

        return companies;
    }

}
