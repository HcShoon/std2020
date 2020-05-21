// routes/posts.js

var express  = require('express');
var router = express.Router();
//var Post = require('../models/Post');

var mysql      = require('mysql');
var dbconfig   = require('../config/database.js');
var conn       = mysql.createConnection(dbconfig);

// // Index 
// router.get('/', function(req, res){
//   Post.find({})                  // 1
//   .sort('-createdAt')            // 1 정렬하는 법 ('-'를 붙이면 desc) 여려개는 {}으로 묻어서 값을 '-1' 또는 '1'로 지정하여  
//   .exec(function(err, posts){    // 1 정렬방향 지정
//     if(err) return res.json(err);
//     res.render('posts/index', {posts:posts});
//   });
// });
// Index 
router.get('/', function(req, res){
  conn.query('select * from board',function(err, posts){    // 1 정렬방향 지정
    if(err) return res.json(err);
    res.render('posts/index', {posts:posts});
  });
});

// New
router.get('/new', function(req, res){
  res.render('posts/new');
});

// create
router.post('/', function(req, res){
  Post.create(req.body, function(err, post){
    if(err) return res.json(err);
    res.redirect('/posts');
  });
});

// show
router.get('/:id', function(req, res){
  Post.findOne({_id:req.params.id}, function(err, post){
    if(err) return res.json(err);
    res.render('posts/show', {post:post});
  });
});

// edit
router.get('/:id/edit', function(req, res){
  Post.findOne({_id:req.params.id}, function(err, post){
    if(err) return res.json(err);
    res.render('posts/edit', {post:post});
  });
});

// update
router.put('/:id', function(req, res){
  req.body.updatedAt = Date.now(); //2
  Post.findOneAndUpdate({_id:req.params.id}, req.body, function(err, post){
    if(err) return res.json(err);
    res.redirect("/posts/"+req.params.id);
  });
});

// destroy
router.delete('/:id', function(req, res){
  Post.deleteOne({_id:req.params.id}, function(err){
    if(err) return res.json(err);
    res.redirect('/posts');
  });
});

module.exports = router;