노드 js로 게시판 만들기

***2020.04.09 - node-js 시작
  + node js 설치
    - index.js 로 Hello World! 출력

  + ejs 설치
    - url 지정후 호출
    - 파라미터 전송 및 호출

***2020.04.10 - 주소록 만들기 (nodeJs - mongdb 연결)
  + ejs express mongdb package 설치
    - npm install --save ejs express mongoose
    - mongo db 회원가입 - 무료 회원 500mb 사용가능
    - mongo db 환경변수에 MONGO_DB 지정 - mongo db 사이트에서 connenction url 할당
    - mongi db connect 확인

  + body-parser package 설치
    - npm install --save body-parser 
    - bodyParser를 통해 리턴데이터형 json으로 지정
    - bodyParser.urlencoded 통해 알고리즘 분석 설정

  + method-override package 설치(이 package는 query로 method 값을 받아서 request의 HTTP method를 바꿔주는 역할을 합니다.)
    - npm install --save method-override
    - update과 destroy는 HTTP Methods 중 put과 delete을 사용하는데, 대부분의 브라우저의 form은 get과 post 만을 허용하고 나머지는 허용하지 않습니다. 
    - 브라우저에서 허용하진 않지만 나중에 API로 연결할 때는 문제가 없기 때문에 HTTP를 올바르게 사용하는 법을 익히는 것이 더 중요합니다. 
    - 지금은 일단 method override라는  package를 설치하여 이를 우회하도록하겠습니다.