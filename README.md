How to run the application:

You don't need any extra configurations, as this application uses H2 in-memory DB.
Clone the project and you are ready to test it with Postman. Sample data for csv uploading is inside the 'resources' folder.

Here's some ways to test it with postman:

The first thing we need to do is to download the CSV from resources folder and send a POST request through Postman.

1) POST     |   url: http://localhost:8080/api/csv/upload . <!-- TOC -->
<!-- TOC -->In the Body tab, select form-data.
   
| key       | type | value                      |
|-----------|------|----------------------------|
| file      | File | *mock-data.csv*            |
| hasHeader | Text | True *(for provided file)* | 
| delimiter | Text | ,                          |
    Add a key named file and set the type to File. Upload CSV file. (you can use mock-data.csv)
    Add a key named hasHeader and set the value to true or false.
    Add a key named delimiter and set the value to your delimiter (e.g., ,).
Postman should automatically set Content-Type as a Multipart/form-data in Headers tab, but in case it didn't work, you can check this manually.

2) Next thing is a pretty straightforward GET request <!-- TOC -->
<!-- TOC -->
GET     |  url: http://localhost:8080/api/csv/records

    In case of successfull previous POST request, we will get a JSON with parsed info from our CSV file.
    Otherwise, we will get an empty JSON.

3) Search functionality <!-- TOC -->
<!-- TOC -->
GET     |  url: http://localhost:8080/api/csv/search?keyword=Gerrard

    Add a query parameter named keyword with the value you want to search for.
    For example, if you want to search for records containing the word "Gerrard", set keyword=Gerrard.
    In case of successfull previous POST request, we will get a JSON with parsed info from our CSV file.

4) Miscellaneous

| Endpoint | URL                                       |
|----------|-------------------------------------------|
|Health Check Endpoint| http://localhost:8080/actuator/health or http://localhost:8080/health |
|API Documentation (Swagger UI)|http://localhost:8080/swagger-ui/index.html|



