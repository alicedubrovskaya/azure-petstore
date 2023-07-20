drop database if exists petstoreproductservice_db;

create database petstoreproductservice_db
    with
    owner = petstoreadmin
    encoding = 'UTF8'
    connection limit = -1;
grant all on database petstoreproductservice_db to petstoreadmin;
grant temporary, connect on database petstoreproductservice_db to public;
