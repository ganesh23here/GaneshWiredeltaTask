package ganesh.wiredelta;

/**
 * Created by Ganesh on 20/11/15.
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CompanyDetailsActivity extends AppCompatActivity {

    private TextView companyID, companyName, companyOwner, companyStartDate, companyDescription, companyDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.wd_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        companyID = (TextView) findViewById(R.id.companyID);
        companyName = (TextView) findViewById(R.id.companyName);
        companyOwner = (TextView) findViewById(R.id.companyOwner);
        companyStartDate = (TextView) findViewById(R.id.companyStartDate);
        companyDescription = (TextView) findViewById(R.id.companyDescription);
        companyDepartments = (TextView) findViewById(R.id.companyDepartments);

        Company company = (Company) getIntent().getSerializableExtra("company");
        companyID.setText(String.valueOf(company.getCompanyID()));
        companyName.setText(String.valueOf(company.getComapnyName()));
        companyOwner.setText(String.valueOf(company.getCompanyOwner()));
        companyStartDate.setText(String.valueOf(company.getCompanyStartDate()));
        companyDescription.setText(String.valueOf(company.getCompanyDescription()));
        companyDepartments.setText(String.valueOf(company.getCompanyDepartments()));
    }
}
