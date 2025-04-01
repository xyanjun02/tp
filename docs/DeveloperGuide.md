# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

## Implementation
This section describes how some noteworthy features are implemented.
### Adding Internship
The ```add``` command allows users to add new internships and requires the following fields:
- Company
- Role
- Duration
- Allowance
- Skills

#### Implementation Flow
1. The user enters an ```add``` command.
2. The parser receives the command and creates a new AddCommand instance.
3. Once the new instance is created, ```AddCommand.execute()``` is invoked.
4. The ```execute()``` method extracts the values in the user input, validates them, and processes them if needed.
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
