#!/bin/bash

BEGIN {
    FS = "->"
    quote = "'"
    srand(9999999)
}

{
    ticker = gsub(" ","", $2)
    randNum = int(rand() * 99999999)
    print "------------------------------------------------------------------------------------------------------------------------------------------"
    print $2
    print "insert into company (ticker, name, public_data, sector) values ("quote$2quote", "quote" "quote", "quote" "quote", "quote" "quote");"
    print "insert into company_entity (entityID, ticker) values ("quote"47029976"quote", "quote$2quote");"
}