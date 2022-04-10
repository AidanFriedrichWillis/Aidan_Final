const router = require("express").Router();
let User = require("../models/user_model");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const { url } = require("inspector");

/* USER CONTROLLER FOLLOWING MVC DESIGNS
USING CRUD REQUESTS TO THE NOSQL DATABSE 
USING PROMISES ON REQUESTS

*/

//READ ALL
module.exports.findAll = async (req, res) => {
  User.find()
    .then((users) => res.status(200).json(users))
    .catch((err) => res.status(400).json("Error: " + err));
};
//CREATE AN ACCOUNT
module.exports.signup = async (req, res) => {
    console.log(req.body)
    const oldUser = await User.findOne({ username: req.body.username });
    if (oldUser) {
        console.log("yo")
        return res.status(409).json("User Already Exists. Please Login");
    }
  var password = req.body.password;
  password = await bcrypt.hash(password, 10);
  const username = req.body.username;
  const email = req.body.email;
  const fullname = req.body.fullname;
  const newUser = new User({ fullname, username, email, password });
  try{
newUser
  .save()
  .then(() => res.status(201).json("User added!"))
  .catch((err) => {console.log("hello")
   res.status(400).json("Error: " + err)});
  }
  catch(e){
    res.json("fail");

  }
  
};
//POST SIGN IN AND RETURN TOKEN
module.exports.signin = async (req, res) => {
  const user = await User.findOne({
    username: req.body.username,
  });
  const token = jwt.sign(
    {
      username: req.body.username,
      rank: user.rank,
      id: user._id,
    },
    "yo",
    { expiresIn: 86400 }
  );

  return res.json({ status: "ok", user: token });
};
//DELETE USER WHERE ID=ID
module.exports.deleteUser = async (req, res) => {
  const id = req.params.id;
  User.findByIdAndRemove(id)
    .then((data) => {
      if (!data) {
        res.status(404).send({
          message: `Cannot delete User with id=${id}. Maybe User was not found!`,
        });
      } else {
        res.status(200).send({
          message: "User was deleted successfully!",
        });
      }
    })
    .catch((err) => {
      res.status(500).send({
        message: "Could not delete User with id=" + id,
      });
    });
};
//UPDATE USER WHERE ID=ID
module.exports.updateUser = async (req, res) => {
  if (!req.body) {
    return res.status(400).send({
      message: "Data to update can not be empty!",
    });
  }
  const id = req.params.id;
  const newname = req.body.newusername;
  let newPassword = await bcrypt.hash(req.body.newPassword, 10);

  User.findByIdAndUpdate(
    id,
    { username: newname, password: newPassword },
    function (err, result) {
      if (err) {
        res.send(err);
      } else {
        res.status(200).send(result);
      }
    }
  );
};