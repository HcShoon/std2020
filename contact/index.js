// index.js

var express = require('./node_modules/express');
var mongoose = require('mongoose');
var bodyParser = require('./node_modules/body-parser'); // 1
var methodOverride = require('./node_modules/method-override'); // 1
var app = express();

// DB setting ...
mongoose.set('useNewUrlParser', true);    // 1 mongoose의 몇몇 글로벌 설정을 해 주는 부분입니다. 
mongoose.set('useFindAndModify', false);  // 1 저 부분이 바뀔 일은 왠만하면 없기 때문에 그냥 항상 저렇게 설정하고 쓰시면 됩니다. .
mongoose.set('useCreateIndex', true);     // 1 이 부분이 빠지게 되면 서버 실행시 경고를 냅니다
mongoose.set('useUnifiedTopology', true); // 1
mongoose.connect(process.env.MONGO_DB); // 2

var db = mongoose.connection; 

//4 db가 성공적으로 연결된 경우 "DB connected"를 출력합니다.
db.once('open', function(){
    console.log('DB connected');
  });
//5 db연결중 에러가 있는 경우 "DB ERROR : " 와 에러를 출력합니다.
db.on('error', function(err){
    console.log('DB ERROR : ', err);
});

// Other settings
app.set('view engine', 'ejs');
app.use(express.static(__dirname+'/public'));
app.use(bodyParser.json()); // 2 json 형태로 받음 설정
app.use(bodyParser.urlencoded({extended:true})); // 3 urlencoded data를 extended 알고리즘을 사용해서 분석한다는 설정
app.use(methodOverride('_method')); // 2 _method의 query로 들어오는 값으로 HTTP method를 바꿉니다

//Routes
app.use('/',require('./routes/home'));
app.use('/contacts',require('./routes/contacts'));

// Port setting
var port = 3000;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});