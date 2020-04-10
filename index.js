// index.js

var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser'); // 1
var methodOverride = require('method-override'); // 1
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

// DB schema // 4 스키마 정의
var contactSchema = mongoose.Schema({
  name:{type:String, required:true, unique:true},
  email:{type:String},
  phone:{type:String}
});

var Contact = mongoose.model('contact', contactSchema); // 5 정의된 스키마 mongo db에 저장

// Routes
// Home // 6
app.get('/', function(req, res){
  res.redirect('/contacts');
});
// Contacts - Index // 7
app.get('/contacts', function(req, res){
  Contact.find({}, function(err, contacts){
    console.log(contacts);
    if(err) return res.json(err);
    res.render('contacts/index', {contactList:contacts});
  });
});
// Contacts - New // 8
app.get('/contacts/new', function(req, res){
  res.render('contacts/new');
});
// Contacts - create // 9
app.post('/contacts', function(req, res){
  Contact.create(req.body, function(err, contact){
    if(err) return res.json(err);
    res.redirect('/contacts');
  });
});

// Contacts - show // 3
app.get('/contacts/:id', function(req, res){
  Contact.findOne({_id:req.params.id}, function(err, contact){
    if(err) return res.json(err);
    if(!contact)  res.redirect('/contacts');//값을 삭제후 뒤로가기 - 새로고침 시 해당 _id의 값이 없어 에러 발생 <- 해결하기위해 없으면 contacts 리다이렉트
    res.render('contacts/show', {contact:contact});
  });
});
// Contacts - edit // 4
app.get('/contacts/:id/edit', function(req, res){
  Contact.findOne({_id:req.params.id}, function(err, contact){
    if(err) return res.json(err);
    res.render('contacts/edit', {contact:contact});
  });
});
// Contacts - update // 5 req.body로 받아 _id의 해당하는 데이터를 update 
app.put('/contacts/:id', function(req, res){
  Contact.findOneAndUpdate({_id:req.params.id}, req.body, function(err, contact){
    if(err) return res.json(err);
    res.redirect('/contacts/'+req.params.id);
  });
});
// Contacts - destroy // 6
app.delete('/contacts/:id', function(req, res){
  Contact.deleteOne({_id:req.params.id}, function(err){
    if(err) return res.json(err);
    res.redirect('/contacts');
  });
});

// Port setting
var port = 3000;
app.listen(port, function(){
  console.log('server on! http://localhost:'+port);
});