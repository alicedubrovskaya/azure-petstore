1. Deploy DB using deployment/deploy-postgresql.sh
2. Deploy services
3. In petstorepetservice App configure: DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD
4. Verify app works properly
5. Create KeyVault
6. Create secrete dbpassword with valid db password
7. Enable Managed Identity in petstorepetservice
8. Give access to the keyvault secrets for this identity
9. Replace DATASOURCE_PASSWORD value with @Microsoft.KeyVault(SecretUri=https://*{vaultName}*.vault.azure.net/secrets/dbpassword/*{versionGUID}*)
10. Verify App works as expected