노드 js로 게시판 만들기

***2020.04.22 - 회원가입
  + 회원가입
    -model.virtual 을 이용하여 pw 확인
    
***2020.04.16 - 기시판 만들기(게시글 쓰기, 읽기, 수정, 삭제) & 회워가입
  + 게시글 쓰기, 읽기, 수정, 삭제
    - cnontect와 유사하게 작성
  + 회원가입 기초 틀 작업 후 테스트 

***2020.04.13 - 기시판 만들기(프로젝트 생성)
  + 프로젝트 생성
    - npm init --yes
    - npm install --save ejs express mongoose body-parser method-override
    - views path가 잡히지않아 require('path'); 를 통해 app.set('views', path.join(__dirname, 'views')); 로 위치 지정

***2020.04.13 - 주소록 만들기(router & bootstrap 적용)
  + Router 적용
    - 이전 index.js에서 app을 사용하여 분기하던것을 모듈로 분리하여 Router로 라우팅함
    - router를 사용하는 이유 : 
      1. 기본 경로 코드 분리 ex) 'contact/new' -> contact로 라우팅 모듈 생성 후 '/new' 로 지정
      2. 콘솔에 요청을 기록하도록 미들웨어 라우팅
      3. 매개변수가 있는 경로
      4. 특정 매개 변수의 유효성을 검사하기 위해 매개 변수 용 미들웨어 라우팅
      5. 특정 경로로 전달 된 매개 변수의 유효성을 검사합니다.
      
  + bootstrap 적용
    - head.ejs에 bootstrap 관련 url 적용
 
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

  + res.render() 
    - 해당 ejs 파일로 페이지 그리기
    
  + mongo db update, select, delete, create
   - post : create에 해당. Contact.create 구문으로 생성
   - get : select에 해당. Contact.find 와 Contact.findOne으로 검색 첫번째 인자 ({}) 에 조건 값 지정 findOne은 하나의 값만 find는 전체를 검색
   - put : update에 해당. Contact.findOneAndUpdate 구문으로 해당 검색 데이터의 값을 수정. 첫번째 인자({})로 검색 후 두번째 인자(req.body) 값으로 수정
   - delete : deletedp 해당. Contact.deleteOne 구문으로  검색 첫번째 인자 ({}) 에 조건 값 지정 후 해당 데이터 삭제

***2020.04.09 - node-js 시작
  + node js 설치
    - index.js 로 Hello World! 출력

  + ejs 설치
    - url 지정후 호출
    - 파라미터 전송 및 호출

