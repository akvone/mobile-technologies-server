# Kotlin app (server for "Blood pressure")
Simple server for "Blood pressure" android application.

## Used technologies
* Kotlin language;
* Maven;
* MongoDB;
* Spring, SpringBoot;

## Public REST API
1.	Post, `security/register` – register new user;
1.	Post, `measurements` – create new measurement;
1.	Get, `measurements` – get all measurements;
1.	Get, `measurements/{id}` – get measurement by id;
1.	Get, `measurements/last` – get last measurement;
1.	Post, `profile` – update profile info;
1.	Get, `profile` – get profile info;

Note that all endpoints have additional `/api/v1/` prefix.

## Notes
* Use `dev` spring profile to create default `akvone/admin` user/password;
* All REST API endpoints (except `security/register`) use Basic auth;
* Use EmbeddedMongoRunnerKt#main() to run MongoDB locally;