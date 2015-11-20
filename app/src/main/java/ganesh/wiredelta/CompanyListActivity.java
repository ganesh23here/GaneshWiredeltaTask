package ganesh.wiredelta;

/**
 * Created by Ganesh on 20/11/15.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompanyListActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    final String ROOT_URL = "https://api.myjson.com/";
    private ListView listView;
    private List<Company> companies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listViewCompanies);

        getCompanies();
        listView.setOnItemClickListener(this);
    }

    private void getCompanies() {
        final ProgressDialog loading = ProgressDialog.show(this, "Downloading data", "Please wait..", false, false);
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();
        CompaniesAPI api = adapter.create(CompaniesAPI.class);
        api.getBooks(new Callback<List<Company>>() {
            @Override
            public void success(List<Company> list, Response response) {
                loading.dismiss();
                companies = list;
                showList();
            }

            @Override
            public void failure(RetrofitError error) {
            }
        });
    }

    private void showList() {
        String[] items = new String[companies.size()];
        for (int i = 0; i < companies.size(); i++) {
            items[i] = companies.get(i).getComapnyName();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.simple_list, items);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CompanyDetailsActivity.class);
        Company company = companies.get(position);
        intent.putExtra("company", company);
        startActivity(intent);
    }
}