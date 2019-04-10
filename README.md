# transport-schedule-aggregator
### Idea:
save data response from yandex-rasp API to use it in various aggregations (cheapest tickect in date interval from place1 to place2)
To minimize api invokations, data from response will store in database and have expiration period (some hours).

### Mainly used technologies:
h2 embedded database, spring boot and hinernate.

### What's done:
  - dictionary entity to store available stations and their codes;

  - request "stations_list";

  - saving json response to dictionary entities.

### What's planning:
  - refactor structure of implementation api;

  - request "search" (other related things like entities, mapping from json to entities);

  - some aggregation commands;

  - simple interface (maybe java.swing, maybe upgrade to webserver plus pages with data);

  - set up logger;

  - set up docker to figure out its principles.
