# Cassandra Java Assignment - ADRIAN TAN

This is a proposed solution for the Cassandra Java Assignment, with test cases for all specified endpoints. This implementation uses Docker Desktop to run Apache Cassandra 5.0, and all URL queries were tested via Postman.

### Notes:

- This package needs to be cloned to a local computer, and its associated Cassandra container must be set up, before the program can be executed. Assume all three of the above-mentioned software applications (Docker, Apache Cassandra, Postman) have been installed prior to running the program.
- In order to set up the Cassandra container to run this application locally, the following commands must be run on the command line:

docker run -p 9042:9042 --rm --name cassandra -d cassandra:4.0.7

docker exec -it cassandra bash -c "cqlsh -u cassandra -p cassandra"

- The program is run from CassandraJavaAssignment.java, but can only be run after the Cassandra container is created. Test cases are run from ValidationFT.java with the same condition.