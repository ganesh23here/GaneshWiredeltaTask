package ganesh.wiredelta;

/**
 * Created by Ganesh on 20/11/15.
 */

import android.support.v7.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class CompanyListActivity extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener {

    final String ROOT_URL = "https://api.myjson.com/";
    private ListView listViewCompanies, filterListView;
    private List<Company> companies;
    EditText inputSearch;
    CompanyAdapter companyAdapter;
    AlertDialog dialog;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewCompanies = (ListView) findViewById(R.id.listViewCompanies);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        findViewById(R.id.filterButton).setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.wd_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        getCompanies();
        listViewCompanies.setOnItemClickListener(this);

        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                CompanyListActivity.this.companyAdapter.filter(cs.toString().toLowerCase(Locale.getDefault()), false);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });


        filterListView = new ListView(this);

        filterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.department);
                dialog.dismiss();
                CompanyListActivity.this.companyAdapter.filter(txt.getText().toString().toLowerCase(Locale.getDefault()), true);
            }
        });

        arrayAdapter = new ArrayAdapter<>(this, R.layout.list_filter, R.id.department, getResources().getStringArray(R.array.department_filter));
        filterListView.setAdapter(arrayAdapter);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", null);
        builder.setView(filterListView);
        dialog = builder.create();
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
        companyAdapter = new CompanyAdapter(CompanyListActivity.this, companies);
        listViewCompanies.setAdapter(companyAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CompanyDetailsActivity.class);
        Company company = companies.get(position);
        intent.putExtra("company", company);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.filterButton) {
            dialog.show();
        }
    }
}