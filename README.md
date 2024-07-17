## 소중한 SW 특강 : AWS로 구현하는 스프링부트 무중단 CI/CD 배포

### 1일차
- IntelliJ, Git 환경설정

### 2일차
#### 도커
**도커란?**
Go 언어로 작성된 리눅스 컨테이너 기반으로 하는 오픈소스 가상화 플랫폼이다. 특정 서비스를 패키징하고 배포하는데 유용한 오픈소스 프로그램이다.

**컨테이너(Container)**
컨테이너는 애플리케이션과 그 필요한 모든 것을 포함하는 격리된 환경. 가볍고 빠르게 시작되며, 다른 컨테이너나 호스트 시스템과의 충돌 없이 독립적으로 실행. 이는 개발, 배포, 실행을 일관되게 만들어 준다.

**이미지(Image)**
이미지는 컨테이너를 생성하는 데 사용되는 템플릿으로, 애플리케이션 실행에 필요한 코드, 라이브러리, 환경설정 등이 포함. 읽기 전용이며, 컨테이너는 이 이미지를 기반으로 생성.

**도커 데몬(Docker Daemon)**
도커 데몬은 컨테이너의 생성, 실행, 모니터링, 삭제 등을 관리하는 백그라운드 프로세스. 도커 클라이언트와 통신하며, 도커의 모든 중요 작업을 담당.

**도커 파일(Dockerfile)**
도커 파일은 도커 이미지를 빌드하기 위한 설정 파일로, 이미지 생성 과정에 필요한 명령어를 순서대로 담고 있다. 이 파일을 사용하면 이미지 빌드 과정을 자동화하고, 재사용 가능한 방식으로 관리 가능.

**도커 허브(Docker Hub)**
https://hub.docker.com/
도커 허브는 도커 이미지를 저장하고 공유할 수 있는 클라우드 서비스. 사용자는 자신의 이미지를 업로드하여 공유할 수 있으며, 다른 사람이 만든 이미지를 검색하고 사용 가능.

#### 도커 이미지 만들기
Dockerfile을 작성하고 이를 기반으로 이미지를 빌드하는 것

**1. 프로젝트 구조 준비**
```
my-html-app/
├── Dockerfile
├── index.html
```
**2. HTML 파일 작성**
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My HTML App</title>
</head>
<body>
    <h1>Hello, Docker!</h1>
    <p>This is a simple HTML file served by Nginx in a Docker container.</p>
</body>
</html>
```

**3. Dockerfile 작성**
```Dockerfile
# 1. Nginx 베이스 이미지 사용
FROM nginx:latest

# 2. HTML 파일 복사
COPY index.html /usr/share/nginx/html/index.html

# 3. 컨테이너 실행 명령어 설정
CMD ["nginx", "-g", "daemon off;"]

```

**4. 도커 이미지 빌드**
```
cd my-html-app
docker build -t my-html-app .
```

**5. 도커 컨테이너를 실행**
```
docker run -d --name my-container -p 8080:80 my-html-app
```


