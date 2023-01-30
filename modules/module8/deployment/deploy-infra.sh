#!/bin/sh
set -e

storageName=$1

source ../../module3/deployment/parameters.sh

az storage account create \
   --name $storageName \
   --resource-group $rgName \
   --location $location \
   --allow-blob-public-access false

az storage container create \
   --account-name $storageName \
   --name shopping-cart \
   --auth-mode login

az servicebus namespace create \
  --resource-group $rgName \
  --name petstore-namespace \
  --location $location \
  --sku Standard

az servicebus queue create \
  --resource-group $rgName \
  --namespace-name petstore-namespace \
  --name order-queue \
  --max-delivery-count 3