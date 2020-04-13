//routes/home.js

var express = require('../node_modules/express');
var router = express.Router(); // 1 express.Router()를 사용해서 router함수를 초기화합니다.

// Home
router.get('/', function(req, res){ // 2 app.get에서 router.get으로 바뀐 것만 빼면 이전코드와 동일합니다.
  res.redirect('/contacts'); //2  "/"에 get 요청이 오는 경우를 router함수에 설정해 줍니다.
});

module.exports = router; // 3 module.exports에 담긴 object(여기서는 router object)가 module이 되어require시에 사용됩니다.