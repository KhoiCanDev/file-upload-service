# Prerequisites
- JDK 17
- IntelliJ (Community is fine) 
- Postman (to test the requests)

# What it has
- API for upload files, support multiple files, with file extension checks, files are stored in `Temp` folder
- API to get uploaded files metadata, return in json
- API to get single uploaded file metadata, return in json
- API to get single uploaded file content, return in binary
- Uploaded file metadata has: file name, file size, stored file path, created time
- Uploaded files metadata are stored in H2 database, save on disk

# How to test using Postman
- Requests are exported to `File Upload Service.postman_collection.json`
- Import to your Postman and run them to test the APIs
- Note: for upload api, make sure you select files on your system first (in Body tab)

# Stored file paths
- Uploaded files are stored in `Users\{User}\AppData\Local\Temp\file-upload-service`
- H2 database is stored in `Users\{User}\fileuploaddb.mv.db`