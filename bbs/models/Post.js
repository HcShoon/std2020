// models/Post.js

var mongoose = require('mongoose');

// schema
var postSchema = mongoose.Schema({ // 1
  title:{type:String, required:true},
  body:{type:String, required:true},
  createdAt:{type:Date, default:Date.now}, // 2 디폴트 값 지정
  updatedAt:{type:Date},
});

// model & export
var Post = mongoose.model('post', postSchema);
module.exports = Post;