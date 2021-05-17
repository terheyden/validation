# Jakarta (Java) Validation Utils

## Spring Boot example

```java
@RestController
@Validated
public class MyController {
    @GetMapping("/greet")
    public String greet(@RequestParam @Size(min = 3) String name) {
        return format("Hello there, %s!", name);
    }
}
```

## Validate bean example

```java
public class User {

    @NotNull
    private final UUID id;
    @NotNullOrEmpty
    private final String name;
    @Positive
    private final int age;

    public User(UUID id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
```
Here is the validation:
```java
User user = new User(id, name, age);
Validations.validateObj(user);
```

## Validating method arguments

```java
/**
 * Example of how to validate method args.
 * Note that this can't be done for static methods at the moment.
 */
public User createUser(@NotNullOrEmpty String name, @Positive int age) {

    Validators.validateArgs(this, name, age);
    return new User(UUID.randomUUID(), name, age);
}
```
This time the validation is built in:
```java
// This will throw:
createUser("", -1);
```
