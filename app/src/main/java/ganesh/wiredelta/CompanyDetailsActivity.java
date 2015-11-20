package ganesh.wiredelta;

/**
 * Created by Ganesh on 20/11/15.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CompanyDetailsActivity extends AppCompatActivity {

    private TextView textViewBookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book_details);
        textViewBookId = (TextView) findViewById(R.id.textViewBookId);

        Company company = (Company) getIntent().getSerializableExtra("company");
        textViewBookId.setText(String.valueOf(company.getCompanyID()));
    }
}
