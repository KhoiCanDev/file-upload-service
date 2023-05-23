# Prerequisites
- JDK 17
- IntelliJ (Community is fine) 
- Postman (to test the requests)

# How to test using Postman
- Requests are exported to `File Upload Service.postman_collection.json`
- Import to your Postman and run them to test the APIs
- Note: for upload api, make sure you select files on your system first (in Body tab)

# Note
- Uploaded files are stored in `Users\{User}\AppData\Local\Temp\file-upload-service`
- H2 database is stored in `Users\{User}\fileuploaddb.mv.db`