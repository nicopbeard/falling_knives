package com.example;

import java.sql.*;

public class FallingKnifeDataModel {
    public String DataID;
    public String Ticker;
    public String StartDate;
    public String EndDate;
    public float Change;
    public float Open;
    public float FutureOpen;

    public FallingKnifeDataModel(String id, String ticker, String sd, String ed, float o, float fo) {
        this.DataID = id;
        this.Ticker = ticker;
        this.StartDate = sd;
        this.EndDate = ed;
        this.Open = o;
        this.FutureOpen = fo;
        this.Change = ((fo-o)/o);
    }
}