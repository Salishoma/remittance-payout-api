
#  Payout API.
-
This is a spring boot application that integrates with a financial tech API. 

You can run the code through your IDE or using docker command. <br>
To run using Docker command, on your terminal:

1. Remove the old compiled classes by running mvn clean(or ./mnvw clean)
2. Generate a jar file by running mvn package
3. You can also run 1 and 2 on line by using mvn clean package
4. Build the image of the application with
###### docker build -t payment-app . (Where . represents the current directory)
5. Run the command with 
###### docker run  -p 80:80 -e SEERBIT_CLIENT_ID=lpk_xxhyx6ta5JwJNgAw2MYzvhd7D -e SEERBIT_SECRET=spk_BdE5ovCPtVFoLQKL5Bnsotr2P payment-app

To access the application, on your browser or postman, type
- http://localhost:8080/seerbit/api/v1/ <br>
as the base url, and then append with the path of the resource you are trying to access.
For more info on the endpoints you can access, use swagger with the url:
- http://localhost:8080/seerbit/api/v1/swagger-ui/index.html <br>

The API documentation is accessible at:
- http://localhost:8080/swagger-ui/index.html#/ <br>

The application also features integration tests for both:
- Service layer
- REST controller layer <br>
