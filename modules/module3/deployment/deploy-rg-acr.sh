#!/bin/sh
set -e

source $(dirname "$0")/parameters.sh

az group create --name $rgName --location $location
az acr create --name $containerRegistryName --resource-group $rgName --sku Standard --admin-enabled true
