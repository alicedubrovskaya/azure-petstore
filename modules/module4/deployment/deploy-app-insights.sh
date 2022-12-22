#!/bin/sh
set -e

source ../../module3/deployment/parameters.sh

az resource create \
    --resource-group $rgName \
    --resource-type "Microsoft.Insights/components" \
    --name $appInsightsName \
    --location $location \
    --properties '{"Application_Type":"web"}'