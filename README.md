# Cassandra Java Assignment

Hi, and welcome to the Cassandra Java Assignment.

This assignment is designed to focus primarily on your Java skills but there is a sprinkling of Cassandra as well.   

## Our Commitment

We understand that feedback is important.  Without it, we don't grow.  We also understand that your time is valuable and real life happens.  

Our commitment to you for this assignment is:

* The assignment should not consume too much of your time.
* Your solution will be evaluated promptly.
* If the solution does not meet the level we are looking for, we will tell you exactly why.

## Your Commitment

We know that a few hours spent working on this assignment does not make for a work of art.  However, we expect:

* You take the assignment seriously. 
* You use the assignment as a way to showcase your skill.
* You are proud of the solution you are presenting.
* You are ready to discuss the solution in detail including tradeoffs you made along the way.

## Submission

Submissions can be handled in 1 of 2 ways.  It is based on entirely on your personal preference

* Fork this repo and open up a PR
* Tar or Zip your solution and send it to your contact person who will pass it along to us.

The most straight forward way is to simply open up a PR.  However, we understand that (for any number of reasons) you may
not want your github account logged as a contributor to this project.  This is perfectly fine and the reason why a Tar/Zip
is an option.  However, it will take a few extra days for your solution to be evaluated.

## Deadline

### Your deadline:

You will have 2 weeks to complete this assignment.  However, if something happens and you are not able to complete it in that 
timeframe (work, family, vacation, illness, etc), then please be up front about it and simply let us know.  These things happen, it 
should not be a cause for stress.

### Our deadline:

We will evaluate a PR submission within 5 business days and an emailed Tar/Zip submission within 7 business days.

## Questions / Suggestions

Please reach out to us if you have questions and we will do our best to clarify.

Your feedback is important.  If you have suggestions on how we can improve this assignment, we would love to hear it.  Please let us 
know within the submission by updating [Feedback.md](Feedback.md).  

## Restrictions

* Must be written in Java 11 or higher
* Must be buildable via Maven or Gradle
* Must be runnable "locally" and not require remote accounts for build, deployment, or execution.

## Assignment

### Business Summary

Hi, my name is Banjo.  I run a successful business called "Everything Bird On Earth" or OBOE.  Our company has several 
high resolution satellites orbiting the planet that uniquely identifies any single bird in flight and gives each an id.  

This information will eventually be used to plot age, population density, and migratory patterns.  However, before that can happen,
we need a way for our scientists to configure the satellites and a place for our satellites to persist the data.

#### Satellites

* The satellites use latitude and longitude to define a location to scan.
* The satellites scan each preconfigured location once a day.
* The satellites know which location applies to it. No need to worry about which or how many satellites there are.

#### Scan Result Data

Satellites generate 0-N scan results depending on how many birds it discovers in the area for the scan.  

| Data Field             | Example                              | 
|------------------------|------------------------------------- |
| location               | 25N,71W                              |
| day scan occured       | 2025-08-17                           |
| unique id of each bird | 50554d6e-29bb-11e5-b345-feff819cdc9f |
| species of bird        | Common loon                          |
| set of bird traits     | red eyes, swim and dive, webbed feet |

#### Scan Location Data

Satellites require being configured with location information that instructs them where to scan.  

| Data Field             | Example                              | 
|------------------------|------------------------------------- |
| location               | 25N,71W                              |
| name                   | Bermuda Triangle                     |

### Requirements

#### Cassandra

* Download and install [Apache Cassanddra](https://cassandra.apache.org/_/download.html).  
  * Note: 3.x version runs on windows and *nix while the 4.x version only runs on *nix.
  * There are many options when [installing cassandra](https://cassandra.apache.org/doc/latest/cassandra/getting_started/installing.html).  The most straightforward is:
    * Download the apache-cassandra-$VERSION-bin.tar.gz
    * Extract it into a any directory of your choosing
    * Run it from within the extracted installation directory: ```bin/cassandra```
* Create a keyspace in Cassandra called "oboe".
  * From installation directory: ```bin/cqlsh```
  * From the cqlsh prompt: ```CREATE KEYSPACE oboe WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;```
* Use the "oboe" keyspace and create a table to hold scan locations and another table to hold scan results.
* Include all DDL and any DML within [CQLStatements.md](CQLStatements.md).
* A single node Cassandra cluster using all the default (out of the box) settings is perfectly fine.

#### Java

* Create a RESTful endpoint for the scientists of OBOE to configure satellite scan locations.
* Create a RESTful endpoint for satellites to read their scan locations.
* Create a RESTful endpoint for satellites to persist scan results.
* Create a RESTful endpoint for the scientists to read the scan results for a specific location on a given day.



