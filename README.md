# RSM Count Duplicates

A lightweight Java utility to count duplicate values in integer arrays.  
**Key Features**:
- ✅ Handles arrays of 1-1000 elements (values 1-1000)
- ✅ Comprehensive test suite (15+ scenarios)
- ✅ Input validation and error handling

---

## Quick Start (Terminal Only)

### Prerequisites
- **Java 8+** (check: `java -version`)
- **Maven 3.x** (check: `mvn -v`)

### 1. Clone & Build
```bash
git clone https://github.com/dundey/RSM-count-duplicates.git
cd RSM-count-duplicates
mvn clean package
```

### 2. Run Tests
```bash
mvn test
```

## Project Structure
```text
.
├── src/
│   ├── main/java/org/rsmtask/     # Production code
│   └── test/java/org/rsmtask/     # JUnit tests
├── pom.xml                        # Maven config
└── README.md                      # This files file