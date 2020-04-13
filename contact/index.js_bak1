var express = require('express'); // 설치한 express module을 불러와서 변수(express)에 담습니다.
var app = express(); //express를 실행하여 app object를 초기화 합니다.

// app.get('/', function(req, res) { // '/' 위치에 'get'요청을 받는 경우,
//   res.send('Hello World!'); // "Hello World!"를 보냅니다.
// });

//app.use(express.static(__dirname + '/public')); // 폴더 지정

app.set('view engine','ejs'); // 1  ejs를 사용하기 위해서 express의 view engine에 ejs를 set하는 코드입니다.
app.use(express.static(__dirname + '/public'));

app.get('/hello', function(req,res){ // 2 query를 통해서 이름을 받는 코드입니다. 모든 query들은 req.query에 저장됩니다.
  res.render('hello', {name:req.query.nameQuery});
});

app.get('/hello/:nameParam', function(req,res){ // 3 route parameter를 통해 이름을 받는 코드입니다. 
                                                // 콜론(:)으로 시작되는 route은 해당 부분에 입력되는 
                                                // route의 텍스트가 req.params에 저장됩니다.
                                                // 예를들어 /hello/Kim을 입력하면 "/hello/:nameParam"에 의해 
                                                // 세미콜론이 있는 route의 2번째 부분 즉, Kim이 req.params.nameParam으로 저장됩니다.
  res.render('hello', {name:req.params.nameParam});
});

var port = 3000; // 사용할 포트 번호를 port 변수에 넣습니다. 
app.listen(port, function(){ // port변수를 이용하여 3000번 포트에 node.js 서버를 연결합니다.
  console.log('server on! http://localhost:'+port); //서버가 실행되면 콘솔창에 표시될 메세지입니다.
});