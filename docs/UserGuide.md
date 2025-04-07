# NextStep - Internship Tracker User Guide 🚀

**NextStep** is a **command-line interface (CLI) application** designed to empower students and job-seekers in their internship search. By simplifying internship management through seamless adding, deleting, searching, and filtering, NextStep eliminates the chaos of spreadsheets and manual tracking. 🎯

---

## 📌 Table of Contents
- [Quick Start](#-quick-start)
- [Features](#-features)
    - [Viewing Help](#viewing-help-help)
    - [Adding Internships](#adding-an-internship-add)
    - [Listing Internships](#listing-internships-list)
    - [Deleting Internships](#deleting-an-internship-delete)
    - [Finding Internships](#finding-internships)
        - [By Skill](#by-skill-finds)
        - [By Role](#by-role-findr)
        - [By Company](#by-company-findc)
    - [Filtering Internships](#filtering-internships)
        - [By Allowance](#by-allowance-filtera)
        - [By Duration](#by-duration-filterd)
    - [Editing Internships](#editing-an-internship-edit)
    - [Exiting the Application](#exiting-nextstep-bye)
- [Data Management](#-data-management)
- [FAQ](#-faq)
- [Known Issues](#-known-issues)
- [Command Summary](#-command-summary)

---

## ⚡ Quick Start

1. **Ensure you have Java 17 or above** installed on your computer.
2. **Download** the latest `nextstep.jar` from our [releases page](https://github.com/AY2425S2-CS2113-F12-2/tp/releases).
3. **Copy** the file to your preferred folder.
4. **Run** the application using:
   ```
   java -jar nextstep.jar
   ```
5. **Type commands** to manage your internships (see examples below):
   ```
   add c/Google r/Software Engineer d/6 a/3000 s/Java,Python
   list
   find/s Python
   bye
   ```
6. Refer to the **[Features](#-features)** below for detailed command usage.

---

## 🚀 Features

> **💡 Notes about the command format:**
> - Words in **UPPER_CASE** represent parameters to be filled in by the user.
    >   - Example: `add c/COMPANY` → Replace `COMPANY` with actual data (`add c/Google`).
> - Commands that **do not take parameters** will ignore extra inputs.
    >   - Example: `help 123` is interpreted as `help`.

### Viewing Help: `help`
Displays a list of available commands and their descriptions.

**Format**:
```
help
```

### Adding an Internship: `add`
Adds a new internship with 6 required fields. Details of the fields are shown in a table below.

**Format**:
```
add c/COMPANY r/ROLE d/DURATION a/ALLOWANCE s/SKILLS st/STATUS
```

**Examples**:
```
add c/Google r/Software Engineer d/6 a/5000 s/Java, Python st/P
add c/Microsoft r/Data Analyst d/12 a/1800 s/Python, SQL st/-
```

| Symbol | Parameter | Description                                                            |
|--------|-----------|------------------------------------------------------------------------|
| `c/`   | COMPANY   | Internship company (Must not exceed 70 characters)                     |
| `r/`   | ROLE      | Internship role (Must not exceed 50 characters)                        |
| `d/`   | DURATION  | Duration in months (Positive integer, maximum of 24 months)            |
| `a/`   | ALLOWANCE | Monthly allowance (Non-negative integer, must not exceed $99999)       |
| `s/`   | SKILLS    | Required skills (comma-separated, maximum of 6 skills)                 |
| `st/`  | STATUS    | Internship status ('A': accepted, 'P': pending, 'R': rejected, '-': NA |


### Listing Internships: `list`
Displays all saved internships with their current application status.

**Format**:
```
list
```

###  Deleting an Internship: `delete`
Removes an internship from the list at the specified index.

**Format**:
```
delete INDEX
```

**Examples**:
```
delete 2
```

| Symbol | Parameter | Description                |
|--------|-----------|----------------------------|
| `-`    | INDEX     | Internship index in `list` |

### Finding Internships
#### By Skill: `find/s`
Searches for internships that require a specific skill.

**Format**:
```
find/s SKILL
```

**Examples**:
```
find/s Java
find/s Python
```

#### By Role: `find/r`
Finds internships for a given role.

**Format**:
```
find/r ROLE
```

**Examples**:
```
find/r Data Analyst
```

#### By Company: `find/c`
Finds internships offered by a specific company.

**Format**:
```
find/c COMPANY
```

**Example**:
```
find/c Google
```

###  Filtering Internships
#### By Allowance: `filter/a`
Finds internships between a given salary range.

**Format**:
```
filter/a MIN MAX
```
If no ```MAX``` is provided, finds internships above ```MIN```.

**Examples**:
```
filter/a 2000 5000  // Finds internships paying $2000 - $5000
filter/a 3000       // Finds internships paying $3000 and above
```

#### By Duration: `filter/d`
Finds internships between a given duration range.

**Format**:
```
filter/d MIN MAX
```

If no ```MAX``` is provided, finds internships above ```MIN```.

**Examples**:
```
filter/d 3 6  // Finds internships lasting 3-6 months
filter/d 12   // Finds internships lasting 12+ months
```

### Editing an Internship: `edit`
Modifies an existing internship at the specified index.

**Format**:
```
edit INDEX
```

**Examples**
```
edit 2
```
After inputting the command, the user is able to modify any field of the selected internship.

**Example**
```
> edit 2
[System] What would you like to edit? (separated by commas):
> Company
[System] Updated Company: 
> Microsoft
```


### Exiting NextStep: `bye`
Closes the application.

**Format**:
```
bye
```

---

## 💾 Data Management
- **Auto-save:** All changes are saved after each command.
- **File Location:** Saved in `[JAR folder]/data/nextstep.txt`.
- **⚠️ Warning:** Manual editing of `nextstep.txt` is **not allowed**. Format of the text file is also non-readable.

---

## ❓ FAQ
**Q: How do I transfer my data?**  
➡ Copy `nextstep.txt` to the new installation directory.

**Q: Can I search for multiple skills?**  
➡ Yes! Use commas: `find/s Python,SQL`

**Q: Can I edit multiple entries at once?**  
➡ No, edit each entry one at a time using its index.

---

## 🚧 Known Issues
🔹 **No critical issues reported!** Found a bug? Report it on GitHub.

---

## 📝 Command Summary

| Action     | Command Format             | Example                  |
|------------|----------------------------|--------------------------|
| **Add**    | `add c/COMPANY...`         | `add c/Facebook r/SE...` |
| **List**   | `list`                     | `list`                   |
| **Find**   | `find/s or /r or /c VALUE` | `find/s Java`            |
| **Filter** | `filter/a or /d MIN MAX`   | `filter/a 3000`          |
| **Edit**   | `edit INDEX`               | `edit 2`                 |
| **Delete** | `delete INDEX`             | `delete 1`               |
| **Help**   | `help`                     | `help`                   |
| **Exit**   | `bye`                      | `bye`                    |

💡 **Tip:** Use arrow keys ⬆⬇ to navigate command history!