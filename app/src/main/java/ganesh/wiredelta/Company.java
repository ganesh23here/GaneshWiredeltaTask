package ganesh.wiredelta;

import java.io.Serializable;

/**
 * Created by Ganesh on 20/11/15.
 */

public class Company implements Serializable{

    private int companyID;
    private String comapnyName;
    private String companyOwner;
    private String companyStartDate;
    private String companyDescription;
    private String companyDepartments;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getComapnyName() {
        return comapnyName;
    }

    public void setComapnyName(String comapnyName) {
        this.comapnyName = comapnyName;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyDepartments() {
        return companyDepartments;
    }

    public void setCompanyDepartments(String companyDepartments) {
        this.companyDepartments = companyDepartments;
    }
}