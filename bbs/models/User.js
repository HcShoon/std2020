// models/User.js

var mongoose = require('mongoose');

// schema
var postSchema = mongoose.Schema({ // 1
  userId:{type:String, required:true, unique:true},
  userPw:{type:String, required:true },
  userName:{type:String}
});

// model & export
var User = mongoose.model('user', postSchema);
module.exports = User;