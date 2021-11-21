# Betting Service
Betting Service is a place where users can risk a sum of money on the basis of the outcome of an unpredictable event. This project aims to manage data about bets using design patterns.


## Design Pattern Usage

* *Adapter* - to adapt from external resource interface to interface in our code
* *Singleton* - to create one global instance of database/file adapter
* *Observer* - used in admin class that stores all objects
* *State* - to change the state of ranking
* *Strategy* - used in making ordinary and express bets
* *MVC Pattern* - is used to separate application's concerns.

## Structure of an application
First, you meet welcome page with options: ```Login```, ```Register```, ```Quit```

Then ```Login``` has 3 options:

* ```My Profile```, ```Show all```, ```Show by category```

```Show all``` illustrates all data about current events, while ```Show by category``` classifies these data by category.

After getting all information about events the user can make a bet: ordinary and express. Also to obtain more data users even can specify the data about players by league.


```Register``` registers new user into the database.

```Quit``` turns off application



## TO RUN THIS PROJECT: 
```Compile ->  javac -cp ".:./com/company/postgresql-42.3.1.jar" com/company/Main.java```

```Run -> java -cp ".:./com/company/postgresql-42.3.1.jar" com/company/Main```

User: 

email: ```nursultan@nu.edu.kz```

password: ```qwerty```

P.S Your directory should be ```src```. In other words, change your directory to ```src``` in the terminal.

## Rules
Users can make a bet.

There are two types of bets, Ordinary and express, two algorithms to make a bet.

Every user has a ranking, defined by bets won.
## Summary
The main purpose of my project is to make it easy to manage patients and get information from database without any problem using design patterns.

Hope this project will help to enhance the quality of the betting system.
