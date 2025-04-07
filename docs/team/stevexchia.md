# Steve Chia - Project Portfolio Page

## Project: NextStep

**NextStep** is a **command-line interface (CLI) application** designed to empower students and job-seekers in their internship search. By simplifying internship management through seamless adding, deleting, searching, and filtering, NextStep eliminates the chaos of spreadsheets and manual tracking. 🎯

---

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
[View my code contributions on RepoSense](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=stevexchia&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

---
### Enhancements Implemented

**New Feature: Dynamic table formatting for listings**
- **What it does**: Displays all available internships in an adjustable table format that automatically resizes columns based on content
- **Justification**: Provides optimal information display regardless of entry length variations
- **Highlights**:
  - Implemented a responsive table formatter that calculates optimal column widths
  - Added truncation for very long entries with restrictions
  - Includes customisable display options through command parameters
- **Challenges**: Required solving complex string manipulation and console display problems
- **Code reference**: `TablePrinter.java`

**New Feature: Added serialization and local storage capability**
- **What it does**: Enables the application to save and load data locally, persisting user data between sessions
- **Justification**: Critical for a complete user experience, ensuring users don't lose their data
- **Highlights**: Required careful design of data structures and robust file error handling
- **Code reference**: `Storage.java`

**New Feature: Implemented entry editing functionality**
- **What it does**: Allows users to edit past entries with a guided, step-by-step interface
- **Justification**: Improves usability by allowing corrections without needing to delete and recreate entries
- **Highlights**: Designed an intuitive user flow that clearly indicates what aspects are being modified
- **Code reference**: `EditCommand.java`

**New Feature: Skill-based internship search**
- **What it does**: Allows users to find internships that match specific skills in their profile
- **Justification**: This targeted search capability helps users efficiently identify the most relevant opportunities
- **Highlights**:
  - Designed to scale with additional filters in future iterations
  - Includes case-insensitive search for better usability
- **Challenges**: Required careful design to ensure search results remained relevant while being comprehensive

**New Feature: Help Command System**
- **What it does**: Provides users with a comprehensive list of available commands and their usage syntax
- **Justification**: Essential for user onboarding and quick reference
- **Highlights**:
  - Clean, well-formatted command list
  - Organised by functionality with clear examples
  - Designed for easy maintenance as new commands are added
- **Code reference**: `HelpCommand.java`

---
### Project Management
- Managed 2 releases on GitHub (`v1.0` and `v2.0`)
- Initiated multiple issues to help track team progress
- Conducted code reviews for team members

---
### Documentation
**User Guide:**
- Added documentation for all features
- Improved clarity of instructions and examples
- Added command summaries and usage examples
- Included troubleshooting section for common errors

**Developer Guide:**
- Created architecture diagram showing component relationships
- Documented general structure and application flow
- Included appendix with:
  - Product scope and user profile
  - Use cases
  - Manual testing instructions with screenshots
  - Non-functional requirements
  - User Story

---
### Testing

#### ✅ JUnit Tests Implemented

- **EditCommandTest**:
  - Handles invalid index (throws InvalidIndexException)
  - Catches empty user input (throws NoSuchElementException)

- **HelpCommandTest**:
  - Validates key command output strings
  - Ensures no exceptions are thrown during execution

- **ListCommandTest**:
  - Prints message for empty internship list
  - Displays correctly formatted table with internships
  - Verifies no mutation to the list post-execution

- **Core Package**:
  - InternshipTest:
    - Getters/setters for all fields (company, role, duration, allowance, status, skills)
    - Checks toString output formatting
  - InternshipListTest:
    - Add, retrieve, and delete internships
    - Valid/invalid index handling
    - List size verification and retrieval

- **Exception Package**:
  - Custom exceptions
  - Error message verification

- **ParserTest**:
  - Handles similar/duplicate commands (throws SimilarCommandException)
  - Handles empty and unknown inputs gracefully
  - Validates correct command instantiation for all supported commands
  - Catches number format issues for commands requiring an index

- **UiTest**:
  - Validates printed output for line breaks and welcome message formatting

---
### Community
- Provided thorough code reviews for teammates
- Assisted team members with debugging and implementation challenges 
- Helped optimise test coverage strategies  
