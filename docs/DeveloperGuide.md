 # Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

***
## Design

NextStep's architecture consists of the following few components:
- ```UI```: Responsible for user interactions, handles output to the user.
- ```Parser```: Interprets and executes user command.
- ```Commands```: Perform specific operations. *(eg. AddCommand, DeleteCommand)*
- ```Core```: Maintains current data state of NextStep.
- ```Storage```: Reads data from, and writes data to, the hard disk.


***
## **Implementation**
This section describes how some noteworthy features are implemented.
### Adding Internship
The ```add``` command allows users to add new internships and requires the following fields:
- Company
- Role
- Duration
- Allowance
- Skills

Example input: ```add c/Google r/SWE d/6 a/5000 s/Java, Python ```

#### Implementation Flow
1. The user enters an ```add``` command along with the required fields.
2. The parser receives the command and creates a new AddCommand instance.
3. Once the new instance is created, ```AddCommand.execute()``` is invoked.
4. The ```execute()``` method extracts the values from the user input, validates them, and processes them if needed.
5. A new internship object is then created and added to the Internship List.
6. The updated list is saved into ```storage```.
7. A confirmation message is then printed to the user via the ```Ui``` class.

The sequence diagram below showcases the flow of execution:

![addCommandSequence.png](images/addCommandSequence.png)

#### Error Handling
Additionally, ```AddCommand``` implements various exception handling to deal with errors.
+ ```EmptyInputException```: Thrown when no details are provided after "add"
+ ```InvalidInputFormatException```: Thrown when required fields are missing
+ ```NumberFormatException```: Caught by the Parser when allowance/duration are not integers

### Deleting Internship
The `delete` command allows users to remove an existing internship from the Internship List by specifying its index.

Example input: ```delete 1```
#### Implementation Flow
1. The user enters a `delete` command with an index.
2. The parser receives the command and creates a new `DeleteCommand` instance.
3. Once the new instance is created, `DeleteCommand.execute()` is invoked.
4. The `execute()` method extracts the index from the user input, validates it (ensuring that:
    - An index is provided.
    - The index is a valid integer.
    - The index is within the bounds of the Internship List), and processes it if valid.
5. The internship corresponding to the specified index is then removed from the Internship List.
6. The updated list is saved into `storage` (if persistence is implemented).
7. A confirmation message is then printed to the user via the `Ui` class.

The sequence diagram below showcases the flow of execution:

![deleteCommandSequence.png](images/deleteCommandSequence.png)

#### Error Handling
Additionally, `DeleteCommand` implements various exception handling to deal with errors.
- `EmptyInputException`: Thrown when no index is provided after "delete".
- `InvalidIndexException`: Thrown when the index given is out of bounds.
- `NumberFormatException`: Thrown when the provided index is not a valid integer.

## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
