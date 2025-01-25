# RSM Homework Tasks

Java implementations for:
1. Duplicate Counter Algorithm
2. Amazon Book Purchase Validation (Selenium)
3. Concurrent Performance Testing

## Quick Start

### Prerequisites
- **Java 8+** (check: `java -version`)
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

## Project Structure
```text
.
rsm-homework-task/
├── src/
│   ├── main/java/com/rsm/homework/
│   │   ├── duplicates/          # Task 1: Count Duplicates
│   │   ├── selenium/            # Task 2: Amazon Books
│   │   └── threads/             # Task 3: Performance Test
│   └── test/java/com/rsm/homework/
│       ├── duplicates/          # Unit Tests
│       ├── selenium/            # E2E Tests
│       └── performance/         # Performance Tests
├── pom.xml
└── README.md