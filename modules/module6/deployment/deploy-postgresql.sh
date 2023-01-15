#!/bin/bash
suffix=$1
appName="petstore"
location="eastus"
rgName=${appName}rg${suffix}
let "randomIdentifier=$RANDOM*$RANDOM"
server="$appName-postgresql-server-${suffix}"
sku="B_Gen5_1"
login="petstoreuser"
password="Strong123"
echo "Using resource group $resourceGroup with login: $login, password: $password..."
echo "Creating $server in $location..."
az postgres server create --name $server --resource-group $rgName --location "$location" --admin-user $login --admin-password $password --sku-name $sku --ssl-enforcement Disabled

startIp="0.0.0.0"
endIp="255.255.255.255"
az postgres server firewall-rule create -g $rgName -s $server -n allowip --start-ip-address $startIp --end-ip-address $endIp


echo "Creating $server in $location..."
az postgres server show --resource-group $rgName --name $server

