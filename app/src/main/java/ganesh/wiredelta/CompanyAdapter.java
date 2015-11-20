package ganesh.wiredelta;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ganesh on 20/11/15.
 */

public class CompanyAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private Activity activity;
    List<Company> companies;
    private ArrayList<Company> companyArrayList;

    public CompanyAdapter(Activity a, List<Company> companies) {
        activity = a;
        this.companies = companies;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.companyArrayList = new ArrayList<>();
        this.companyArrayList.addAll(companies);
    }

    public int getCount() {
        return companies.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder holder;

        if (convertView == null) {
            rowView = inflater.inflate(R.layout.list_company, null);
            holder = new ViewHolder();
            holder.companyName = (TextView) rowView.findViewById(R.id.companyName);
            holder.department = (TextView) rowView.findViewById(R.id.department);
            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.companyName.setText(companies.get(position).getComapnyName());
        holder.companyName.setTag(position);
        holder.department.setText(companies.get(position).getCompanyDepartments());
        return rowView;
    }

    public static class ViewHolder {
        TextView companyName, department;
    }

    public void filter(String charText, boolean isDepartment) {
        charText = charText.toLowerCase(Locale.getDefault());
        companies.clear();
        if (charText.length() == 0) {
            companies.addAll(companyArrayList);
        } else {
            for (Company company : companyArrayList) {
                if (isDepartment) {
                    if (company.getCompanyDepartments().toLowerCase(Locale.getDefault()).contains(charText)) {
                        companies.add(company);
                    }
                } else {
                    if (company.getComapnyName().toLowerCase(Locale.getDefault()).contains(charText)) {
                        companies.add(company);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

}