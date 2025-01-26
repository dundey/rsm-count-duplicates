# RSM Homework Tasks

## Tasks
1. **Duplicate Counter**: Count duplicates in arrays.
    - Location: `src/main/java/com/rsm/homework/duplicates/`
2. **Amazon Books**: Automate book purchase validation.
    - Location: `src/main/java/com/rsm/homework/selenium/`
3. **Performance Testing**: Simulate high-concurrency workloads.
    - Location: `src/main/java/com/rsm/homework/threads/`

### Prerequisites
- **Java 17+** (check: `java -version`)
- **Maven 3.x** (check: `mvn -v`)

### 1. Clone & Build
```bash
git clone https://github.com/dundey/rsm-homework-task.git
cd rsm-homework-task
mvn clean package
```

### 2. Run Tests
```bash
mvn test
```

# Run specific task tests
```bash
mvn test -Dtest="*Duplicate*Test"       # Task 1 (unit tests)
mvn test -Dtest="*Selenium*Test"        # Task 2 (E2E tests)
mvn test -Dtest="*Threads*Test"     # Task 3 (performance tests)
```

## Project Structure
```text
.
rsm-homework-task/
├── src/
│   ├── main/java/com/rsm/homework/
│   │   ├── duplicates/                         # Task 1: Count Duplicates
│   │   │   └── DuplicateCounter.java           # TODO
│   │   ├── selenium/                           # Task 2: Amazon Books
│   │   └── threads/                            # Task 3: Performance Test
│   │       └── MockService.java                # Mock service for performance testing
│   └── test/java/com/rsm/homework/
│       ├── duplicates/                         # Unit Tests
│       │   └── DuplicateCounterTest .java      # TODO
│       ├── selenium/                           # E2E Tests
│       └── threads/                            # Performance Tests
│           └── MockServicePerformanceTest.java # Performance test
├── pom.xml
└── README.md