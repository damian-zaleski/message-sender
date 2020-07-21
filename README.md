
# recruitment-task
- Task description can be found here: [task description](TASK.md)

## Steps to run locally
1. Run cassandra on a docker
    -  `docker pull cassandra`
    -  `docker run -p 9042:9042 --name your-cassandra-name --network your-network -d cassandra:latest`
    -  if you want to run cqlsh: `docker run -it  --network your-network --rm cassandra:latest cqlsh your-cassandra-name`
2. Run app locally
    - `gradle clean bootRun`
3. Expose app locally on a docker:
    - `gradle clean bootJar`
    - `docker build -t your-application-name . `
    - `docker run -p 8080:8080 --name your-application-container-name --network your-network -d your-application-name:latest`
4. Hit [localhost:8080](http://localhost:8080) for API info on Swagger

