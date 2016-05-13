# Bands and Venues

#### Web app for a Bands and Venues to be tracked in a database using Java and Postgres | May 13, 2016

#### By Nadiya Yeroshkina

## Description
An app to track bands and the venues where they've played concerts.

## Setup/Installation Requirements

Clone this repository.
```
$ cd ~/Desktop
$ git clone https://github.com/NadinYrosh/Band-Tracker_Java_Friday-Solo.git
$ cd Band-Tracker
```

Open terminal and run Postgres:
```
$ postgres
```

Open a new tab in terminal and run:
```
$ psql
```

In psql run:
```
# CREATE DATABASE band_tracker;
```

In terminal run:
```
$ psql band_trecker < band_trecker.sql
```

Switch to psql and run:
```
# \c band_trecker
# \dt
```
This will list all of the tables in the `band_trecker` database

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```

Open your web browser of choice to localhost:4567

Make sure you have Java and Gradle installed.
  * For Java:
      * Download and install [Java SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
      * Download and install [Java JRE](http://www.java.com/en/)
  * For Gradle: if you are using Homebrew on Mac:
      * $ brew update
      * $ brew install gradle




## Support and contact details

_Questions or comments can be directed to the developer_

_HTML, CSS, Bootstrap, Java, Gradle, Velocity, JUnit Testing, Spark, FluentLenium,  Postgres and Sql2o._

### License

**This project is licensed under the [MIT license](https://opensource.org/licenses/MIT).**

Copyright (c) 2016 Yeroshkina N.
