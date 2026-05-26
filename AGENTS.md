# AGENTS

## Scope

Instructions for AI coding agents working in this repository.

## Stack

- Java 25
- Spring Boot
- Redis
- Gradle Wrapper

## Fast Start

- Windows run app: `./gradlew.bat bootRun`
- Run tests: `./gradlew.bat test`
- Coverage report: `./gradlew.bat jacocoTestReport`

## Conventions

- Keep package naming under `com.cleancoders.redistoolkit`.
- Follow standard layered package structure used in sibling Spring projects.
- Prefer Gradle wrapper and shared dependency conventions over local one-off changes.

## Notes

- This repo has minimal top-level docs; infer conventions from source and build files.

## References

- [build.gradle](build.gradle)
- [settings.gradle](settings.gradle)
- [src](src)
