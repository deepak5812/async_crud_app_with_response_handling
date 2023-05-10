# async_crud_app_with_response_handling
<!-- TODO: Write requirement and test documentation! -->

## Prerequisites
- JDK, version >= 8
- a command line interface like (bash/sh/cmd/pwsh/...)
- `docker`
- `curl`

Command line examples are in bash style, please adjust to your "shell"...
## Start DB
```bash
./src/script/start_db.sh
```
[script content](src/script/start_db.sh)
## Clean (Test) Install
(depends on running DB)
```bash
./mvnw clean install
```
## Run
(depends on running DB)
```bash
./mvnw spring-boot:run
```
### HATEOAS Entry
```bash
curl http://localhost:8080
```
### Create/Modify "Subscriber"
```bash
curl -X PUT -H "Content-Type:application/json" -d '{"mode": "testo"}' http://localhost:8080/subscribers/1/ # creates a "Subscriber" with id=1
curl -X PUT -H "Content-Type:application/json" -d '{"mode": "testa"}' http://localhost:8080/subscribers/1/ # modifies "Subscriber" with id=1
curl -X PUT -H "Content-Type:application/json" -d '{"mode": "testb"}' http://localhost:8080/subscribers/2/ # creates a "Subscriber" with id=2
curl -X PUT -H "Content-Type:application/json" -d '{"mode": "testc"}' http://localhost:8080/subscribers/3/ # creates a "Subscriber" with id=3
```
### View "Subscriber"
```bash
curl http://localhost:8080/subscribers/1 # single subscriber
curl http://localhost:8080/subscribers # a (`page`d, `size`d, `sort`ed) representation of all
```
### Delete "Subscriber"
```bash
curl -X DELETE http://localhost:8080/subscribers/1/ # deletes "Subscriber" with id=1
```
### Refs
- https://docs.spring.io/spring-data/rest/docs/current/reference/html/
- ...