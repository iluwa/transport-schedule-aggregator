# transport-schedule-aggregator
### Idea:
save data response from yandex-rasp API to use it in various aggregations (cheapest tickect in date interval from place1 to place2).

To minimize api invocations, data from response will be stored in database and have expiration period (some hours).

### Mainly used technologies:
h2 embedded database, spring boot and hibernate.

### What's done:
  - dictionary entity to store available stations and their codes;

  - request "stations_list";

  - request "search"

  - saving json response to dictionary entities.

### What's planning:
  - some aggregation commands;

  - simple interface (maybe java.swing, maybe upgrade to webserver plus pages with data);

  - write unit tests.
