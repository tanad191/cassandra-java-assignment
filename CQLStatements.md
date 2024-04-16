# Cassandra CQL Statements

Use this file to include your DDL.  Also include any DML that you may have created.


## DDL

-- Create a keyspace

CREATE KEYSPACE IF NOT EXISTS oboe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

-- Create tables

CREATE TABLE IF NOT EXISTS oboe.scan_location (
    locationId int PRIMARY KEY,
    location text,
    name text
);

CREATE TABLE IF NOT EXISTS oboe.scan_result (
    resultId int PRIMARY KEY,
    location text,
    scanDay date,
    birdId text,
    birdSpecies text,
    birdTraits list<text>
);

-- Insert sample data

INSERT INTO oboe.scan_location
(locationid, location, name)
VALUES (1, '25N,71W', 'Bermuda Triangle');

INSERT INTO oboe.scan_result
(resultId, location, scanDay, birdId, birdSpecies, birdTraits)
VALUES (1, '25N,71W', '2025-08-17', '50554d6e-29bb-11e5-b345-feff819cdc9f', 'Common loon', [ 'red eyes', 'swim and dive', 'webbed feet' ]);

## DML (if any)
