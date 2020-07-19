
# recruitment-task
Task description can be found here: [task description](TASK.md)
 
##### Run cassandra on a docker:
1. `docker pull cassandra`
2. `docker run -p 9042:9042 --name your-cassandra --network your-network -d cassandra:latest`
3. if you want to run cqlsh: `docker run -it  --network your-network --rm cassandra:latest cqlsh your-cassandra`

