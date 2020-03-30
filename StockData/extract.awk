#!/bin/bash

BEGIN {
    FS = ","
    quote = "'"
    printf "insert into market (dataID, date, high, low, open, close, volume) values "
}

{
    if(NR > 1)
    {
        printf "("quote"6248448"quote", "quote""$1""quote", "quote""$2""quote", "quote""$3""quote", "quote""$4""quote", "quote""$5""quote", "quote""$6""quote"), "
    }
}