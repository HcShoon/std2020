// routes/posts.js

var express  = require('express');
var router = express.Router();
var User = require('../models/User');

// Index 
// router.get('/:userId', function(req, res){
//   Post.findOne({userId:req.params.userId}, req.body,function(err, posts){    
//     if(err) return res.json(err);
//     res.render('posts/index', {posts:posts});
//   }
//   );
// });
//login
router.get('/', function(req,res){

  res.render('users/login');
});

//create user
router.post('/', function(req, res){
  User.create(req.body, function(err, user){
    if(err) return res.json(err);
    alert('회원가입 성공');
    res.redirect('/users');
  });
});

//signup
router.get('/joinup', function(req, res){
  res.render('users/joinup');
});

//sign in
router.post('/joinin', function(req, res){
  console.log(req.body)

  User.findOne({userId:req.body.userId}, function(err, user){
    if(err) return res.json(err);
    console.log('--------------------------------------------');
    console.log(err);
    console.log(user);
    if(user.userPw != req.params.userPw){
      alert('로그인 실패')
    }else{
      alert('로그인 성공')
    }

    res.render('/users', {});
  });
});


module.exports = router;