# Context
This is a Kotlin standalone app that is able to parse a standard Cron expression and generates a prettified output with the given expression's expanded values.

_Psst...```src/main/kotlin/org/esia/simplecron/CronParser.kt``` is a good starting point to see how the app operates internally_

# Prerequisites
- Java >= 1.8.0
- Gradle (Optional)

# Build
Under the project's root directory, run the following commands:
1. ```./gradlew clean build```
2. ```./gradlew shadowJar```

You should see an executable JAR file called **simple-cron-1.0-all.jar** under the ```build/libs``` directory.

# Execute
Under the project's root directory, run the following command:
- ```java -jar build/libs/simple-cron-1.0-all.jar```

For help with usage, run the following command:
- ```java -jar build/libs/simple-cron-1.0-all.jar -h```

Usage should look as follows:
```
» java -jar build/libs/simple-cron-1.0-all.jar -h
usage: [-h] CRON_EXPRESSION UNIX_COMMAND

optional arguments:
  -h, --help        show this help message and exit


positional arguments:
  CRON_EXPRESSION   Cron schedule expression e.g. minute hour day_of_month
                    month day_of_week

  UNIX_COMMAND      Unix command

```

E.g. of usage in action:
```
➜  java -jar build/libs/simple-cron-1.0-all.jar "*/15 0 1,15 * 1-5" "/usr/bin/find"                                                                                                                                                                                                                                                                                [Wed Mar 03, 23:17]
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

# Tests
Under the project's root directory, run the following command:
- ```./gradlew clean test```

You should see something along the lines of:-
```
--------------------------------------------------------------------------------------------
|  Results: SUCCESS (30 tests, 30 successes, 0 failures, 0 skipped) Gradle Test Run :test  |
--------------------------------------------------------------------------------------------
```

You can also find a HTML/prettified overview of the test results under the ```build/reports/tests``` directory. It should be labeled under **index.html**.
