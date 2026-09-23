# greet-service

A minimal Spring Boot web service used as the running example for a **Jenkins CI/CD pipeline**
(DevOps PBL). Every stage of the pipeline has real code to build, test, analyse, package and deploy.

## Endpoint
```
GET /greet  ->  Hello from the Jenkins CI/CD pipeline - greet-service
```

## Build & run locally
```powershell
mvn clean package
java -jar target/greet-service.jar
# then browse http://localhost:8080/greet
```

## CI/CD pipeline (Jenkinsfile)
The declarative pipeline runs on Jenkins (Windows) and chains:

```
GitHub push -> Checkout -> Build -> Test -> SonarQube analysis -> Quality Gate
            -> Package jar -> Docker build -> Push to Nexus -> Deploy container
```

| Stage | What it does |
|-------|--------------|
| Checkout | Clones this repo |
| Build | `mvn clean compile` |
| Test | `mvn test` + JUnit report |
| SonarQube Analysis | Static analysis via the SonarQube Maven plugin |
| Quality Gate | Fails the build if the gate is not met |
| Package | `mvn package`, archives the jar |
| Docker Build | Builds an image from `Dockerfile` |
| Push to Nexus | Pushes the image to a private Nexus Docker registry |
| Deploy | Runs the image as a container |

## Toolchain
- Jenkins LTS (Windows service, port 8080)
- Temurin JDK 21, Maven 3.9.x
- SonarQube Community (port 9000)
- Docker Desktop (image build) + Nexus Repository 3 (private registry, port 5001)

## Jenkins configuration used
- Maven tool: `Maven3`
- SonarQube server: `SonarQube` @ http://localhost:9000, token credential `sonar-secret`
- Nexus credential: `nexus-login`
- Docker engine reached over `tcp://localhost:2375`

---
Author: **Raghav Kamboj** &middot; Roll No. 500102126 &middot; Subject: DevOps
