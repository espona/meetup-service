# meetup-service
System that collects, processes and stores real-time RSVPs from Meetup.com and provides methods to query them to extract useful information.

This is my first Spring Boot project, some parts have been simplified or are not exhaustive (in particular tests, error handling and documentation) due to lack of time.
 
# Overall structure
The system has three main components:
- An RSVP Stream Listener to collect the asynchronous data pieces from the Meetup stream
- A [Solr-based](https://lucene.apache.org/solr/guide/7_5/index.html) repository to store the received data and allow CRUD operations and advanced search.
- A REST Service to query and search RSVP related information

> *Remark*: The system has been implemented as an unique system but the stream listener and the service to query information could be separated, sharing the common repository.


## Stream Listener
The listener connects to the stream and waits for new RSVPs. 

An event is published every time a new piece of data is received and it will be converted and stored in the Solr repository.

Two endpoints have been implemented to start and stop this listener (as GET so one can easily do it from the browser). Unfortunately it is not stateless, so it may have problems with concurrency.

## Solr Repository

The repository is Solr based (v.7.5.0) and its's configuration is included in [src/main/resources/solr](https://github.com/espona/meetup-service/tree/master/src/main/resources/solr). the main file is the "schema.xml" that defines the fields of the RSVP document.

Solr may not be the first choice repository for this use cases but I have experience with it and I found it more challenging and interesting to solve the exercise this way. A usual relational database would have been more than enough, for the two endpoints requested Solr's search capabilities aren't taken advantage of. 

### Querying for top cities

This can be performed in Solr with a simple group query by city, filtered by date, but one cannot sort the results by aggregate functions ( "COUNT(*)"-like). Therefore before making a group query to Solr I needed to make a facet one to get the first "num" cities sorted by number of events.

There is another limitation related to this Solr inability to perform aggregate functions: one cannot easily count the *guests* on the RSVP to sort the top cities. This could be solved by creating extra RSVP documents in Solr (one per guests) which will complicate the CRUD repository logic a lot. Alternatively, setting up Solr as *SolrCloud* the repository could be queried as an RDBM with a usual SQL aggregate query through the parallel SQL interface. This last alternative is a bit of an overkill for this exercise and it showed also very bad performance on my local computer. Also, I must say than in my experience on Meetup and due to the high percentage of "no-show", the number of RSVP's will provide a better estimation for the real attendance to an event ;)

### Querying for groups nearby
 
Although this seemed quite straight forward due to Sorl spatial search capabilities, it turned out being difficult to implement. 

There were some changes in Solr in latest versions and the function to get the distance can only be used for sorting if the parameter "sfield" is specified. After quite some research, I couldn't find a way to specify this parameter using [Spring Data for Solr](https://spring.io/projects/spring-data-solr) so I had to perform the query directly using the Java Solr client. As a result the code for the top cities query and the groups nearby are completely different.

## Tests

Only some examples have been implemented due to lack of time.
There are a couple of basic integration tests and some component tests for the Solr repository.

Test for testing the multiple error cases and exhaustive unit test should be defined. Tests related to the listener component are missing too.

A test Solr server should be in place with a url specified at application-dev.properties, this will allow to do better testing on the repo, cleaning it and filling it up with known data on every test run.

## Exception and error handling

A basic mechanism of returning a ErrorDTO on any failed response has been set up for the most common exception cases (not found, bad parameters, method not allowed, etc) but I am ware is not exhaustive.

## Documentation

Again, this only got so far due to my time limitations.

Basic Javadoc documentation on the project classes can be found under the [/doc](https://github.com/espona/meetup-service/tree/master/doc) directory. 

Regarding the API documentation, [Swagger2](https://swagger.io/) has been setup, providing information in an interactive way for all the endpoints, including parameters and responses in case of error or success.









