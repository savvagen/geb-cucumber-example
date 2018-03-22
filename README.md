# GEB framework example

## How to run testing locally

1. Run the testing using command:
```
./gradlew test
``` 
2. Depends on the goals, we can specify the browser name in the command, to run the testing on specific browser:
``` 
./gradlew chromeTest 
```
or
```
./gradlew firefoxTest 

```

## How to run testing remotely
1. First - run the Selenoid locally (or remotely) folowing this guide: https://github.com/savvagen/Selenoid-example
2. Run the testing using command after running of the Selenoid:
``` 
./gradlew remoteTest

```

## Run cucumber test

* Use command: ``` ./gradlew cucumber ```

## Run Spock tests

* To run the Spock tests on different browsers use commands:

For chrome
```
./gradlew chromeSpock
```

For firefox
``` 
./gradlew firefoxSpock

```

If you need more details about main futures and configurations switch to [presentation]("https://docs.google.com/presentation/d/1p2zcIOuFeszPDvde9Tm0fuJCLygbeke0VkP1CpmufZE/edit?usp=sharing")

   Links:
    
[![presentation](https://docs.google.com/presentation/d/1p2zcIOuFeszPDvde9Tm0fuJCLygbeke0VkP1CpmufZE/edit#slide=id.p.jpg)](https://docs.google.com/presentation/d/1p2zcIOuFeszPDvde9Tm0fuJCLygbeke0VkP1CpmufZE/edit#slide=id.p)
