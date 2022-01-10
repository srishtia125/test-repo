This assignment is designed on spring-boot framework.

Installation

Clone the source code

git clone https://github.com/srishtia125/test-repo.git

Go to the checked out source code and start the server locally (Server will start on port 8080 , make sure no other is using the same port)

```
mvn spring-boot:run
```

Usage
When the server starts , internally following data is stored:

Data 

I have mocked some random data for both ToughJet and Crazy API response.

Sample data for ToughJet

[
    {
        "carrier": "toughJet",
        "basePrice": 1200.0,
        "tax": 10.0,
        "discount": 12.0,
        "departureAirportName": "LHR",
        "arrivalAirportName": "IND",
        "outboundDateTime": "2022-02-03T10:15:30",
        "inboundDateTime": "2022-02-03T10:15:30"
    },
    {
        "carrier": "toughJet",
        "basePrice": 1000.0,
        "tax": 10.0,
        "discount": 12.0,
        "departureAirportName": "LHR",
        "arrivalAirportName": "IND",
        "outboundDateTime": "2022-02-03T10:15:30Z",
        "inboundDateTime": "2022-02-03T10:15:30Z"
    }
]

APIs

 BusyFlight sample Request

http://localhost:8080/busyFlight/flights?origin=LHR&destination=IND&departureDate=2022-02-03&returnDate=2022-02-03&passengerCount=3

This Api returns the list of flights from Crazyair and ToughJet providers aggregated and in ascending order of price.

Validations: Constaint Validation to validate if a request param is valid or not.

Tests:
Unit Tests: Testing services and other parts of application to see if
 meets its design and behaves as intended.
 
 Integration Test : Testing the external API call.

