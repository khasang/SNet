 ## ASirosh
 ### Update operation
 #### Overview
 This operation use for changing password for account
 that exists in database. For this needs login of user,
 by login will found account and current password will
 be changed to new one. Update operation requires autorization.
 #### Setting up
 This feature work depends on tables **users** and **roles**, 
 also  existance of some accounts in that table requared. 
 Table must contains **admin** and **user** account. If you 
 haven't yet that table, there few test for creation table, 
 and inserting required accounts.
 ```java
 @Test@Ignore
public void createNewTableUsers() {
    String droping = "DROP TABLE IF EXISTS users";
    String creation = "CREATE TABLE users(  id integer NOT NULL,  login character varying(255),  " +
            "password character varying(255),  role_id integer,  CONSTRAINT users_pkey PRIMARY KEY (id))";
    try {
        queryHandler.execute(droping);
        queryHandler.execute(creation);
    } catch (Exception exc) {
        Assert.fail("Failed execute statement, error was thrown: " + exc);
    }
}
```
This test creates table users
> WARNING: All data that contains in user will lost!

For prevent uncontroled droping tables all tests,
running whose could lead to loss of data, by default
will skipped.

>Also, you can run these test for setting up your database:

| Test   | Function |
|  ----- | ---------|
| createNewTableRoles()| creates new table **roles**|
| createNewTableUsers()| creates new table **users**|
| createAdminRole()| create **ADMIN** role |
| createPrimeEntry() | create **admin** account |
| createExampleUser() | create **user** account for test purpose |

>Note: All password already encrypted when creates accounts.

#### Usage

For changing password of exist user you must **REST**-request

![](http://i82.fastpic.ru/big/2016/1019/d6/9391086e55742f63118d45020ca88bd6.png)

Example request,
```
http://localhost:8080/confidential/changepwd?login=user&newPwd=password
```
* user - user's login in database
* password - user's _non-encrypted_ password in database

#### Security

Request of change password mapped in secure zone **/confidential/**, it's mean that
operation requares autorization. Moreover, password given in request automatically
encrypted before it's put in database.
