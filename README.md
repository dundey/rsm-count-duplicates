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
mvn test -Dtest="*DuplicateCounterTest"              # Task 1: Duplicate Counter (Unit Tests)
mvn test -Dtest="*AmazonBookTest"                    # Task 2: Amazon Books (E2E Tests)
mvn test -Dtest="*MockServicePerformanceTest"        # Task 3: Performance Testing
```
