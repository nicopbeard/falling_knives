#!/bin/bash

BEGIN {
    FS = ","
    quote = "'"
}

{
    while(getline line < "addToDB.txt")
    {
        split(line, b, ",")
        printf "insert into market (dataid, date, high, low, open, close, volume) values"
        dataid = b[1]
        ticker = b[2]
        file = ticker"Data.csv"
        while(getline line1 < file)
        {
            split(line1, a, ",")
            if(a[1] == "Date")
                continue
            printf " ("quote dataid quote", "quote a[1] quote", "quote a[2] quote", "quote a[3] quote", "quote a[4] quote", "quote a[5] quote", "quote a[6] quote"),"
        }
        close(file)
        print ";"
    }
}