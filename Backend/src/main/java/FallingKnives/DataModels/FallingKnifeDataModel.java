package FallingKnives;

import java.sql.*;

public class FallingKnifeDataModel {
    public String Ticker;
    public Date StartDate;
    public Date EndDate;
    public float Change;

    FallingKnifeDataModel(String ticker, Date sd, Date ed, float change) {
        this.Ticker = ticker;
        this.StartDate = sd;
        this.EndDate = ed;
        this.Change = change;
    }
}