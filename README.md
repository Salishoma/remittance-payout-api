
#  Payout API
This is a spring boot application that integrates with a financial tech API. Java version for this project is Java 17, while the spring boot version is 3.0.5.

You can run the code through your IDE or using docker command. <br>
To run using Docker command, on your terminal:

1. Remove the old compiled classes by running mvn clean(or ./mnvw clean)
2. Generate a jar file by running mvn package
3. You can also run 1 and 2 on line by using mvn clean package
4. Build the image of the application with
###### docker build -t payment-app . (Where . represents the current directory)
5. Run the command with 
###### docker run  -p 80:80 -e SEERBIT_CLIENT_ID=<your_client_id> -e SEERBIT_SECRET=<your_secret_key> payment-app

### Docker Compose
To run docker compose, on the command line:
1. Remove target folder and generate jar file using mvn clean package
2. Run the command:  docker-compose up -d
3. To access the endpoints, go to 
- http://localhost:8080/seerbit/api/v1/swagger-ui/index.html#/payout-controller/payout
for swagger, and
- http://localhost:8080/seerbit/api/v1/payout/create
for instance to access the payout/create endpoint if you want to use postman.

To access the application, on your browser or postman, type
- http://localhost:8080/seerbit/api/v1/ <br>
as the base url, and then append with the path of the resource you are trying to access.
For more info on the endpoints you can access, use swagger with the url:
- http://localhost:8080/seerbit/api/v1/swagger-ui/index.html <br>

When run using your IDE, the API documentation is accessible at:
- http://localhost:8080/swagger-ui/index.html#/ <br>

The application also features integration tests for both:
- Service layer
- REST controller layer <br>
