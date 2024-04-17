# Cassandra CQL Statements

Use this file to include your DDL.  Also include any DML that you may have created.


## DDL

-- Create a keyspace

CREATE KEYSPACE IF NOT EXISTS oboe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

-- Create tables

CREATE TABLE IF NOT EXISTS oboe.scan_location (
    locationId text PRIMARY KEY,
    coordinates text,
    name text
);

CREATE TABLE IF NOT EXISTS oboe.scan_result (
    resultId text PRIMARY KEY,
    coordinates text,
    scanDay date,
    birdId text,
    birdSpecies text,
    birdTraits list<text>
);

-- Insert sample data

INSERT INTO oboe.scan_location
(locationId, location, name)
VALUES ('0bc9b5d5-c1d4-4e56-bad9-bf89a15c8f58', '25N,71W', 'Bermuda Triangle');

INSERT INTO oboe.scan_result
(resultId, location, scanDay, birdId, birdSpecies, birdTraits)
VALUES ('0bc9b5d5-c1d4-4e56-bad9-bf89a15c8f58', '25N,71W', '2025-08-17', '50554d6e-29bb-11e5-b345-feff819cdc9f', 'Common loon', [ 'red eyes', 'swim and dive', 'webbed feet' ]);

## DML (if any)
