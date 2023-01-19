#!/bin/sh
set -e

source ../../module3/deployment/parameters.sh

az group delete --name $rgName
