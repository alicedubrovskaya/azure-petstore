## How to deploy PetStore

### Deployment code structure
1. `deploy-app-insights.sh` deploys Application Insights service
2. `cleanup.sh` cleans up all resources.

### Pre-requisites
1. Azure Subscription was created
2. Azure CLI is installed
3. Bash console is available
4. App Services are deployed from previous Module 3

### Deployment steps
1. Open Bash in this folder
2. Run `./deploy-app-insights.sh`. Get `Instumentation key` from output
3. Put `Instumentation Key` to each service directly or use as env var for docker
4. Re-deploy services including integration with Application Insights
5. Open app URI. Try multiple times as App and Services has a lengthy cold start. Check Application Insights

### Cleanup
`./cleanup.sh`