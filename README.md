## Introduction
This is a sample application the process coindirect country list api and expose required documents 
and currency in following endpoints.
```
* /country/list
* /country/currency
```
This is the version 1 (v1) for these apis. 

###Prerequisites 

    * OpenJdk 10 or higher
    * Node version 17+
    * Maven 3.6.3 or higher
    * docker 20.10.8 or higher
    * docker-compose 1.29.2 or higher

### Quick start
If we do not want to install all this packages in our local machine, the easiest way to build and run 
this application is to have docker and docker-compose installed. Then do the following.

```shell
cd reqdoc
tee >> .env << EOF
API_ENDPOINT=https://api.coindirect.com/api/country
SERVICE_PORT=7070
EOF
make
```
This will build the source codes in a docker container and then start it :).

#### Endpoint /v1/country/list
The required document for countries will be accessible in the following url by default this show only 10 countries
in ascending order by name.
```shell
curl http://localhost:8080/v1/country/list | jq .
```
Now if you want to see more data you can sent to query parameters `offset` and `max` to list more countries. i.e

```shell
curl http://localhost:8080/v1/country/list?offset=0&max=100 | jq .
```
This will list all the countries with required documents, currency, maxwithdrawal limit for 100 countries.

response from this api looks like below
```json
[
  {
    "name": "Afghanistan",
    "currency": "AFN",
    "requiredDocuments": [
      {
        "id": 157,
        "required": true,
        "description": "ID or Passport",
        "code": "idPassport"
      },
      {
        "id": 158,
        "required": true,
        "description": "ID Selfie",
        "code": "idSelfie"
      },
      {
        "id": 159,
        "required": true,
        "description": "Proof of Address",
        "code": "proofOfAddress"
      }
    ],
    "maxWithdrawal": 1275000
  }
]
```
If you want to see all the countries you can use `max` value 400.

Now if we are interested in sorting the list in descending order then we can also send a `sort` parameter. 
The value of `sort` should be like `<field>:<asc|desc>`. Now it only allows to be sorted by `name` or `currency`.
We can get a descending ordered list of countries by name like below

```shell
curl http://localhost:8080/v1/country/list?sort=name:desc&offset=0&max=100 | jq .
```
#### Endpoint /v1/country/currency

Note:
This is rather simple api which actually list all the countries with its currency name and the country name.
There is no sorting/offset/max query parameter available for this endpoint.

This can be access in the following url 
```shell
curl http://localhost:8080/v1/country/currency | jq .
```
Sample response looks like following:
```shell
[
  {
    "currency": "AED",
    "countryName": "United Arab Emirates"
  },
  {
    "currency": "AFN",
    "countryName": "Afghanistan"
  },
  {
    "currency": "AMD",
    "countryName": "Armenia"
  }
]
```

#### Accessing logs 
If you want to see logs  you can just run following command
```shell
docker-compose logs -f reqdoc
```
#### Stop containers
```shell
docker-compose down
```

### Build and run from source code
If you are interested in building and running the code locally without docker containers you should full fill 
prerequisites listed above. Then you can run 
```shell
make start-service
```
This will build the reqdoc service and will start the service.
You can access the service in following urls

```shell
http://localhost:7070/v1/country/list
http://localhost:7070/v1/country/currency
```

### Running with app
Now there has been a small attempt to create a small app to view the output from the reqdoc service. To run the app
along with reqdoc service you can do the following 

```shell
make deploy
```
This should start both of the containers that you will be able to access reqdoc service as described above on port `8080`
and the app would be available on the following url 
```shell
http://localhost:3000
```

Happy Coding! :)

