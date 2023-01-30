#!/bin/bash

set -e

appSuffix=$1
orderQueue=$2
petserviceTag=petstorepetservice
petserviceSubdomain="${petserviceTag}${appSuffix}"
productserviceTag=petstoreproductservice
productserviceSubdomain="${productserviceTag}${appSuffix}"
orderserviceTag=petstoreorderservice
orderserviceSubdomain="${orderserviceTag}${appSuffix}"
webAppTag=petstoreapp
webAppSubdomain="${webAppTag}${appSuffix}"

#creating RG and ACR first to let container ACR time to initialize, while dockers are being built
./deploy-rg-acr.sh $appSuffix


echo "Building Web App"
docker build -t $webAppTag ../../../petstore/petstoreapp

# back to working dir
#./deploy-app-service.sh $petserviceSubdomain $petserviceTag $appSuffix
#./deploy-app-service.sh $productserviceSubdomain $productserviceTag $appSuffix
#./deploy-app-service.sh $orderserviceSubdomain $orderserviceTag $appSuffix "PETSTOREPRODUCTSERVICE_URL=https://${productserviceSubdomain}.azurewebsites.net/"
./deploy-app-service.sh $webAppSubdomain $webAppTag $appSuffix "PETSTOREPRODUCTSERVICE_URL=https://${productserviceSubdomain}.azurewebsites.net PETSTOREPETSERVICE_URL=https://${petserviceSubdomain}.azurewebsites.net PETSTOREORDERSERVICE_URL=https://${orderserviceSubdomain}.azurewebsites.net ORDER_RESERVER_QUEUE_CONNECTION=${orderQueue}"