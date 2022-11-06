#!/bin/bash

curl --location --request POST 'http://localhost:2700/topics/rsupply-stockdata-json-request' \
--header 'Content-Type: application/json' \
--data-raw '{
    "records": [
        {
            "key": "alice",
            "value": {
                "RequestMaterialRequest": {
                    "RequestMaterialRequestDetail": [
                        {
                            "Status": "Available from Stock",
                            "UnitPrice": null,
                            "RSupplyUnitOfIssue": "",
                            "PartNo": "50186",
                            "Niin": "000017479",
                            "NmroUnitOfIssue": "",
                            "QuantityOrdered": 1,
                            "ProcessMessage": "",
                            "CosalTypeCode": "F",
                            "ErrorMessage": "",
                            "MessageType": "Request",
                            "MessageId": "E17F711CDB284983E053DC40A98CBBFE"
                        }
                    ]
                }
            }
        }
    ]
}'


curl --cert pem --cacert  ../../../config/dod-truststore.jks --location --request GET 'https://kafkarestproxy-confluent.apps.cluster-1-ship.noble.niwc.navy.mil/topics'

curl --location --request GET 'http://localhost:2700/topics' 


'https://kafkarestproxy-confluent.apps.cluster-1-ship.noble.niwc.navy.mil/topics