#!/bin/sh
set -e

accountName=$1
accountKey=$2

export ACCOUNT_NAME=$accountName
export ACCOUNT_KEY=$accountKey

cd ../function;
mvn clean package azure-functions:deploy