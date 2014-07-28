Once running go to http://localhost:8080/GrailsProject/

Then try and fire JSON reques to http://localhost:8080/GrailsProject/hackapi/notifypayment
- Passing a param http://localhost:8080/GrailsProject/hackapi/notifypayment?customerName=jlong
- Currently only supports GET

To view your domain model
- localhost:8080/GrailsProject/dbconsole/
- select Generic H2 (Embedded)
- by detault this supports HSQLDB, update datasource.grooy to point to your own DB instance

Integration Test
- NotifyPaymentSpecification is the basic test to validate end-to-end testing
