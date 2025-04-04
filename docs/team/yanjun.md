# Yan Jun - Project Portfolio Page

## Overview

**NextStep** is a **command-line interface (CLI) application** designed to empower students and job-seekers in their internship search. By simplifying internship management through seamless adding, deleting, searching, and filtering, NextStep eliminates the chaos of spreadsheets and manual tracking. 🎯

Given below are my contributions to the project.

## Summary of Contributions

### Code Contributed
[View my code contributions on RepoSense](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=xyanjun&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2025-02-21&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

### Enhancements Implemented

**New Feature: Delete Command Implementation**
- What it does: Enables users to remove unwanted or obsolete entries from the project data.
- Justification: Streamlines data management by letting users easily delete entries, ensuring the workspace remains relevant and clutter-free.
- Highlights:
    - Integrated with the project’s command framework to safely execute deletions.
    - Added robust error handling and confirmation prompts to prevent accidental data loss.
    - Ensured compatibility with the project’s data persistence and serialization mechanisms.
- Challenges: Balancing user safety with efficient deletion, especially in scenarios with interdependent data entries.

**New Feature: Find by Role**
- What it does: Allows users to search for internships by specifying job roles.
- Justification: Improves user experience by quickly filtering and locating relevant opportunities based on the role.
- Highlights:
    - Developed case-insensitive and partial matching algorithms to cater to diverse role names.
    - Seamlessly integrated into the existing search framework.
    - Optimized for quick retrieval even with large datasets.
- Challenges: Ensuring accurate matches despite varied naming conventions and abbreviations in role titles.

**New Feature: Find by Company**
- What it does: Provides the ability to search for internships by company name.
- Justification: Simplifies the process of tracking opportunities associated with specific companies.
- Highlights:
    - Implemented handling for common company name variations and synonyms.
    - Enhanced search efficiency while maintaining high relevance in results.
    - Maintained consistency with the project’s overall search functionality.
- Challenges: Managing inconsistencies in company naming formats and ensuring robust matching across the board.

**New Feature: Addition of Status to Listings**
- What it does: Introduces a status field within the listings to indicate the current stage or condition of each internship entry.
- Justification: Provides users with an immediate overview of the progress or state of each entry, improving data clarity.
- Highlights:
    - Integrated the new status field into the project’s dynamic table display.
    - Updated table formatting logic to accommodate additional information without disrupting the layout.
    - Enabled real-time updates to the status as changes occur.
- Challenges: Incorporating the new field seamlessly while preserving backward compatibility and ensuring the display remains clear and user-friendly.

### Project Management
- Managed 2 releases on GitHub (v1.0 and v2.0)
- Initiated multiple issues to help track team progress
- Conducted code reviews for team members

### Documentation
**User Guide:**
- Updated documentation to include detailed instructions for using the delete command, search functionalities (by role and by company), and status updates.
- Refined examples and usage scenarios to ensure clarity for end-users.

### Community
- Provided thorough code reviews for teammates
- Assisted team members with debugging and implementation challenges
