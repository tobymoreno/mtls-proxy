curl -k --cert identity.pem --cacert truststore.pem https://localhost:8444/api/hello


curl -k --cert identity.pem --cacert truststore.pem --pass secret -H 'Content-Type: application/json' -H 'Accept: application/json' -d @data.json https://localhost:8444/api/nse  | /c/Users/tmoreno/Downloads/jqwin/jq

   
