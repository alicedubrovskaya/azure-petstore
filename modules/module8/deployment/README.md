## How to deploy PetStore

### Pre-requisites
1. Azure Subscription was created
2. Azure CLI is installed
3. Bash console is available
4. App Services are deployed from previous Module 3

### Deployment steps
1. Open Bash in this folder
2. Run `./deploy-infra.sh <unique-name-of-storage>`. 
Get `accountName` and `accountKey` from operation response or Azure Console
Get `connectionString` from created Service Bus name.
3. Put `connectionString` to `local.settings.json` as a value for `AzureServiceBusConnection`
Run `./deploy-function.sh <accountName> <accountKey>`. Get domain host of created Azure Function.
5. Run `./redeploy-web-app.sh <app-name-of-create-web-module3> <queueConnectionString>`. Get domain host of created Azure Function.

### Cleanup
`./cleanup.sh`