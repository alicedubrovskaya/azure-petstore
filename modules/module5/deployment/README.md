## How to deploy PetStore

### Pre-requisites
1. Azure Subscription was created
2. Azure CLI is installed
3. Bash console is available
4. App Services are deployed from previous Module 3

### Deployment steps
1. Open Bash in this folder
2. Run `./deploy-storage.sh <unique-name-of-storage>`. Get `accountName` and `accountKey` from operation response or Azure Console
3. Run `./deploy-function.sh <accountName> <accountKey>`. Get domain host of created Azure Function.
4. Run `./redeploy-web-app.sh <app-name-of-create-web-module3> <functionHost>`. Get domain host of created Azure Function. E.g. `./redeploy-web-app.sh testappazure https://course-azure-function.azurewebsites.net`

### Cleanup
`./cleanup.sh`