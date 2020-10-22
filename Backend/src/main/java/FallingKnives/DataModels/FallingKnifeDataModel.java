package FallingKnives;

import java.sql.*;

public class FallingKnifeDataModel {
    public String DataID;
    public String Ticker;
    public Date StartDate;
    public Date EndDate;
    public float Change;
    public float Open;
    public float FutureOpen;

    FallingKnifeDataModel(String id, String ticker, Date sd, Date ed, float o, float fo) {
        this.DataID = id;
        this.Ticker = ticker;
        this.StartDate = sd;
        this.EndDate = ed;
        this.Open = o;
        this.FutureOpen = fo;
        this.Change = ((fo-o)/o);
    }
}