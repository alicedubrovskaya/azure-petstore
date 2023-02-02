#!/bin/sh
set -e

storageName=$1

source ../../module3/deployment/parameters.sh

az storage account create \
   --name $storageName \
   --resource-group $rgName \
   --location $location

az storage container create \
   --account-name $storageName \
   --name shopping-cart \
   --auth-mode login