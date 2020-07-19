
# recruitment-task
- Task description can be found here: [task description](TASK.md)

## Steps to run locally
1. Run cassandra on a docker
    -  `docker pull cassandra`
    -  `docker run -p 9042:9042 --name your-cassandra --network your-network -d cassandra:latest`
    -  if you want to run cqlsh: `docker run -it  --network your-network --rm cassandra:latest cqlsh your-cassandra`
2. Run app
    - `gradle clean bootRun`
3. Hit [localhost](http://localhost:8080) for API info on Swagger 