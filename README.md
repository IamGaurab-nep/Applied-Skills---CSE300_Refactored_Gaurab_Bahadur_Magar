# Student Management System — Refactored

A refactor of the original single-class `StudentManagement.doEverything()`
implementation into four small, single-purpose Java classes.

Original source: https://gist.github.com/actimsina/27c0f0c7b9290b1aa22f8d7b28a673c2

## Structure

```
src/
├── Student.java                 # Validated student record (name, id, scores)
├── StudentRepository.java       # Storage contract (interface)
├── FileStudentRepository.java   # File-based implementation of the contract
└── StudentManagement.java       # Interactive console app (main entry point)
```

No package declarations are used, so all four classes sit in the default
package — this keeps the project runnable with nothing more than `javac`
and `java`, no build tool required.

## Running it

### From the command line
```
cd src
javac *.java -d ../out
cd ../out
java StudentManagement
```

### IntelliJ IDEA
1. Open the project folder (`File → Open`, select this directory).
2. Right-click the `src` folder → **Mark Directory as → Sources Root**.
3. Right-click `StudentManagement.java` → **Run 'StudentManagement.main()'**.

### VS Code
1. Install the **Extension Pack for Java** (Microsoft) if you don't have it.
2. Open this folder in VS Code — it will auto-detect `src` as the source
   root via `.vscode/settings.json`.
3. Open `StudentManagement.java` and click **Run** above `main()`, or use
   the included launch configuration (Run and Debug → *Launch
   StudentManagement*).

## What it does

A simple console menu:

```
1. Add     - add a new student (name, id, three subject scores)
2. Result  - show one student's average and grade
3. List    - list every student saved so far
4. Exit
```

Students are stored one per line in `students.txt` (created automatically
in the working directory on first save), using `|` as the field separator.

## Notes on the design

- `Student` validates itself in its constructor (no blank name/id, no `|`
  in name/id, scores must be 0–100) so an invalid `Student` object can
  never exist.
- `StudentRepository` is an interface, so `StudentManagement` never talks
  to file I/O directly — swapping in a database-backed repository later
  would not require touching `Student` or `StudentManagement`.
- `FileStudentRepository` uses try-with-resources for safe file handling,
  returns an empty list if the storage file doesn't exist yet, and skips
  (with a warning) any malformed or invalid line instead of crashing.

See the accompanying report for the full list of issues identified in the
original code and how each one is addressed here.

Author: Aaditya Maharjan
