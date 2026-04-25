# Student Registry - Spring Boot Cache Demo

A Spring Boot REST API demonstrating caching with GitHub Actions CI/CD pipeline using self-hosted runners and Docker.

## Tech Stack

- Java 17
- Spring Boot 3.5.14
- Spring Cache (in-memory)
- Docker
- GitHub Actions (self-hosted runners)


## REST API
| Method |Endpoint | Description |
| ---| --- | --- |
| GET | ```/students``` | Returns student map (cached after first call) |
| POST | ```/students/clear-cache``` | Clears the cache

## Run Locally
```bash
mvn spring-boot:run
```
## Run with Docker
```bash
docker build -t student-registry:latest .
docker run -d -p 8080:8080 --name student-registry-container student-registry:latest
```

## GitHub Actions Workflow
Triggered manually via ```workflow_dispatch``` with a filename input.
### Job Flow
```
JOB1 (Docker Build & Run) ──┐
                             ├──► JOB4 (List Docker Images) ──► JOB5 (Cleanup)
JOB2 (File Operations) ──► JOB3 (Copy & Modify) ──┘
```
### Jobs

| Job | Depends On | Description |
|-----|-----------|-------------|
| JOB 1 | — | Builds Docker image and runs container |
| JOB 2 | — | Creates file with key-value data, copies to folder2, replaces text |
| JOB 3 | JOB 2 | Copies file to folder3, replaces text, sleeps 1 min |
| JOB 4 | JOB 1 + JOB 3 | Lists all Docker images |
| JOB 5 | All (always) | Stops container and removes all Docker images |
## Self-Hosted Runners

- 2 Windows self-hosted runners (runner1 and runner2)
- JOB 1 and JOB 2 run in parallel on separate runners
- JOB 5 runs with if: always() to ensure cleanup even on failure
