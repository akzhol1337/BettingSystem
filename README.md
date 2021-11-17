<h1>Betting System</h1>

<p>
Store information about matches (playing teams, coefficient, date/live, number of bets, total money on match), store on Database(PostgreSQL) or XML/TXT file. 

Adapter Pattern to adapt from external resource interface to interface in our code

Singleton to create one global instance of database/file adapter

---

Create system of account, every account has his own ID, balance, etc.

Observer pattern to notify user accounts from admin panel

---

User can make a bet.

There is two types of bets, Ordinary and express, two algorithms to make a bet

Abstract pattern to make an object of express bet

Strategy pattern to define algorithms

---

Every user has a ranking, defined by bets won

State pattern do give a personal ranking to every user
</p>