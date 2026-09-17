# Emergency Resource Management System

## 1. Project Overview

This is a simple Java command-line project for handling emergency incidents and resources.

The system can:
- report a new incident
- give priority to incidents
- add emergency resources
- check resource availability
- assign a free resource to an incident
- update incident status
- show a simple system report

The project is made without any external Java library.

## 2. Main Modules

1. Incident Management
2. Resource Management
3. Resource Assignment
4. Status Management
5. Reports and Summary

## 3. Technologies

- Java
- OOP
- ArrayList
- Comparator
- File handling
- Exception handling
- Input validation
- Git/GitHub

## 4. Requirements

Install JDK 17 or a newer version.

Check Java:

```bash
java -version
javac -version
```

## 5. Run the Project

Open the project root in terminal.

Compile:

```bash
javac -d out src/emergency/*.java
```

Run:

```bash
java -cp out emergency.Main
```

## 6. Run Validation Test

Compile the project first, then run:

```bash
java -cp out emergency.InputTest
```

Expected result:

```text
Validation tests passed.
```

## 7. Data Storage

The project stores data in:

```text
data/incidents.txt
data/resources.txt
```

These files are created when data is added.

## 8. Basic Working

First add resources.

Then report an incident.

Use the Assign Resource option to connect an available resource with the incident.

The resource changes from AVAILABLE to BUSY.

The incident changes from OPEN to ASSIGNED.

You can later change the incident status to RESOLVED.

## 9. GitHub Submission

Keep the repository Public.

Submit only the repository root URL:

```text
https://github.com/{github-username}/{repo-name}
```

Do not submit a `/tree/main/` or `/blob/` URL.
