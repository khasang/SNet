# SNet

## D.B.Andreev aka elimental

### SQL Home Work. Truncate operation

Truncate operation mapped on URL:/truncate. The user must have the `DIRECTOR` role.

Below is example of method wich provides truncate operation:

```java
public String truncate() {
        try {
            jdbcTemplate.execute("TRUNCATE TABLE COMPANY");
            return "Table Company truncated";
        } catch (Exception e) {
            return "Error: " + e;
        }
    }
```
