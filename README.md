# meetup-service
System that collects, processes and stores real-time RSVPs from Meetup.com and provides methods to query them to extract useful information.

This is my first Spring Boot project, some parts have been simplified or are incomplete (in particular tests, error handling and documentation) due to lack of time.

# Overall structure
The system has three main components:
- An RSVP Stream Listener to collect the asynchronous data pices from the Meetup stream
- A Solr-based repository to store the received data and allow CRUD operations and advanced search.
- A REST Service to query and search RSVP related information

> *Remark*: The system has been implemented as an unique system but the stream listener and the service to query information could be separated, sharing the repository.
