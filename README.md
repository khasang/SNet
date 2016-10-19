# SNet

## KPavlov
### Backup operation
#### `Mapping`
Operation is mapped on URL :  [/backup](). 

![](http://i12.pixs.ru/storage/2/5/1/backuppng_3849920_23694251.png)
>Example of sucsessful backup.

#### `Security`
Back up operation is also provided by Spring security. The user must have a specific role `BACKUP_ROLE` to make this operation.

#### `pg_dump`
Backup operation is provided by `pg_dump`.
To make it work correctly,you need to create `backup.properties` in resourses folder. It have two options:
 - postgresql.dumpAppPath
 - postgresql.dumpFolder

>Example of correct options:
>```
>postgresql.dumpAppPath=C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump
>postgresql.dumpFolder=C:\\Backup\\
>```
>
It also use connection to [PostgreSQL](https://www.postgresql.org/) database. Properties for connection are used from `auth.properties`
>

Example of method in class,which provides this operation:

```java
...
    public String makeBackUp() {
        String pgDump = environment.getProperty("postgresql.dumpAppPath");
        String dumpFile = environment.getProperty("postgresql.dumpFolder") + getBackupFileName();
        //Add commands to start pg_dump
        final List<String> baseCmds = new ArrayList<>();
        //Path to pg_dump
        baseCmds.add(pgDump);
        baseCmds.add("-h");
        baseCmds.add("localhost");
        //Port
        baseCmds.add("-p");
        baseCmds.add("5432");
        //User
        baseCmds.add("-U");
        baseCmds.add(environment.getProperty("jdbc.postgresql.username"));
        //Add BLOB object into dump file
        baseCmds.add("-b");
        baseCmds.add("-v");
        //Path to dump file
        baseCmds.add("-f");
        baseCmds.add(dumpFile);
        //Base name
        baseCmds.add("snet");
        final ProcessBuilder processBuilder = new ProcessBuilder(baseCmds);
        //Password for PostgreSQL user
        final Map<String, String> env = processBuilder.environment();
        env.put("PGPASSWORD", environment.getProperty("jdbc.postgresql.password"));
        try {
        ...
        }
    }
...
```
Some parameters for `pg_dump` :

| Parameter    | Description   |
| --------|---------|
| -a | Dump only the data, not the schema (data definitions). Table data, large objects, and sequence values are dumped.   |
| -b | Include large objects in the dump. This is the default behavior except when --schema, --table, or --schema-only is specified, so the -b switch is only useful to add large objects to selective dumps. |
| -f | Send output to the specified file. This parameter can be omitted for file based output formats, in which case the standard output is used. It must be given for the directory output format however, where it specifies the target directory instead of a file. In this case the directory is created by pg_dump and must not exist before. |
| -v| Specifies verbose mode. This will cause pg_dump to output detailed object comments and start/stop times to the dump file, and progress messages to standard error.|

>You can also read about another options [here](https://www.postgresql.org/docs/9.2/static/app-pgdump.html).

## SPetrov
### Insert and Delete operations

##### `Database`
>
This operations use connection to [PostgreSQL](https://www.postgresql.org/) database. Properties for connection provide from `auth.properties`
In our case, they use database name - `snet` , table name - `company`
>
##### Code for creation table `Company`:

```sql
CREATE TABLE company
(
  id serial NOT NULL,
  address character varying(255),
  age integer,
  name character varying(255),
  salary double precision,
  CONSTRAINT company_pkey PRIMARY KEY (id)
)
```

#####Description of methods to work with operations insert and delete:

|Method|Description|Secure|Mapping|
|--------|---------|--------|---------|
|tableCreation()|create table| no | localhost:8080/create|
|insert()|add some record in table| no | localhost:8080/insert|
|selectAll()|show all records of table| no | localhost:8080/allCompany|
|deleteRecord(int recordId)|delete from table record with id = `recordId`| yes | localhost:8080/delete/{companyId}|
|delete()|delete table| yes | localhost:8080/delete|
 
##### `Security`
 Delete operation provided by Spring security. For this, you must have role `ROLE_MASTER`.
>
 For example, if we want delete `company with id=104`:

|Authorization|Successful operation|
|--------|---------|
|![](http://s020.radikal.ru/i707/1610/46/cd2ebf8a1592.jpg)|![](http://s02.radikal.ru/i175/1610/eb/c7e4fbce2cff.jpg)|
> 

> 
To make sure the record was deleted, you can display a list of all companies:
![](http://s018.radikal.ru/i507/1610/01/5c2bdee6903e.jpg)
>

=======
#Snet
## ASirosh
### Update operation
#### Overview

This operation use for changing password for account that exists in database. For this needs login of user,
by login will found account and current password will be changed to new one. Update operation requires autorization.

#### Setting up

This feature work depends on tables **users** and **roles**, also  existance of some accounts in that table requared. Table must contains **admin** and **user** account. If you haven't yet that table, there few test for creation table,and inserting required accounts.

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

## ASirosh
### Update operation
#### Overview

This operation use for changing password for account that exists in database. For this needs login of user,
by login will found account and current password will be changed to new one. Update operation requires autorization.

#### Setting up

This feature work depends on tables **users** and **roles**, also  existance of some accounts in that table requared. Table must contains **admin** and **user** account. If you haven't yet that table, there few test for creation table,and inserting required accounts.

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
