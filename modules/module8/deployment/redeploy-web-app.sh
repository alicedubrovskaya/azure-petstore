#!/bin/sh
set -e

appSuffix=$1
orderQueue=$2

source ../../module3/deployment/parameters.sh
petserviceTag=petstorepetservice
petserviceSubdomain="${petserviceTag}${appSuffix}"
productserviceTag=petstoreproductservice
productserviceSubdomain="${productserviceTag}${appSuffix}"
orderserviceTag=petstoreorderservice
orderserviceSubdomain="${orderserviceTag}${appSuffix}"
webAppTag=petstoreapp
webAppSubdomain="${webAppTag}${appSuffix}"

echo "Building Web App"
docker build -t $webAppTag ../../../petstore/petstoreapp

../../module3/deployment/deploy-app-service.sh $webAppSubdomain $webAppTag $appSuffix "PETSTOREPRODUCTSERVICE_URL=https://${productserviceSubdomain}.azurewebsites.net PETSTOREPETSERVICE_URL=https://${petserviceSubdomain}.azurewebsites.net PETSTOREORDERSERVICE_URL=https://${orderserviceSubdomain}.azurewebsites.net ORDER_RESERVER_QUEUE_CONNECTION=${orderQueue}"