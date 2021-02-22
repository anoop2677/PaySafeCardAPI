# PaySafeCardAPI
PaySafeCardAPI is an end point integration of paysafe payment through card.
For UI: [PaySafe UI](https://github.com/anoop2677/paysafeUI)

# Clone the Repository
Import as an gradle project and run following command:
```bash
gradlew build
```
# Start the Application
Run the main application inside 'src/main/java'
application will run on:
[localhost:9090](http://localhost:9090/token)

# Endpoints
```bash
/token
```
To create customer and generate single use customer token

```
/payment
```
Payment processing
