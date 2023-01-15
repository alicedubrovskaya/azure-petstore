1. Run ./deploy-postgresql.sh
2. Connect to server and create a DB: petstore
3. Deploy the petservice with connection to DB:
./deploy-app-service.sh petstorepetservice${suffix} petstorepetservice ${suffix} "DATASOURCE_URL=jdbc:postgresql://petstore-postgresql-server-${suffix}.postgres.database.azure.com:5432/petstore DATASOURCE_USER=petstoreuser@petstore-postgresql-server-${suffix} DATASOURCE_PASSWORD=Strong123"

