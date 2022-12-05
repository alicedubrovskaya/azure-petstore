#!/bin/bash

set -e

appServiceName=$1
dockerImageTag=$2
settings=$3
appServicePlanName="${appServiceName}plan"
source $(dirname "$0")/parameters.sh

dockerAcrUri="${containerRegistryName}.azurecr.io/${dockerImageTag}"

echo "Pushing image to registry: ${dockerAcrUri}"
az acr login -n $containerRegistryName
docker tag "$dockerImageTag" "$dockerAcrUri"
docker push "$dockerAcrUri"

echo "Creating App Service Plan: $appServicePlanName"
az appservice plan create --name "$appServicePlanName" --resource-group "$rgName" --is-linux

echo "Creating App Service Web App.: $appServiceName"
az webapp create -g "$rgName" -p "$appServicePlanName" -n "$appServiceName" -i "$dockerAcrUri"

if [ -n "$settings" ]
then
  echo "Configure Settings"
  az webapp config appsettings set -g "$rgName" -n "$appServiceName" --settings $settings
fi