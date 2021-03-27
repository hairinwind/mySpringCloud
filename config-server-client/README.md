
## have to set spring profile to run it
```
-Dspring.profiles.active=dev
```
or 
```
-Dspring.profiles.active=pr
```
Otherwise, it cannot get the @Value from config server.
