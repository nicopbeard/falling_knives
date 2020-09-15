package FallingKnives;

import java.sql.*;

public class CompanyDataModel extends BaseDataModel {

    public String Ticker;

    public String Name;

    public Date PublicDate;

    public String Sector; // Turn this into separate data model of Sectors

    public CompanyDataModel(String t, String n, Date p, String s) {
        this.Ticker = t;
        this.Name = n;
        this.PublicDate = p;
        this.Sector = s;
    }

    public CompanyDataModel(String t, String n, String s) {
        this.Ticker = t;
        this.Name = n;
        this.PublicDate = null;
        this.Sector = s;
    }

}