const jwt = require("jsonwebtoken");
const User = require("../models/user_model");
const bcrypt = require("bcryptjs");

//Sorts the data Posted from the client
module.exports.sortData = async (req, res, next) => {
  try {
    req.body = JSON.parse(req.body.data);
    next();
  } catch (error) {
    res.status(500).send();
  }
};
// RETURNS NEXT() IF TOKEN IS VALID
module.exports.verifyToken = async (req, res, next) => {
  let token = req.body.token || req.headers.authorization;
  if (!token) {
    return res.status(403).send({ message: "No token provided!" });
  }
  jwt.verify(token, "yo", (err) => {
    if (err) {
      return res.status(401).send({ message: "Unauthorized!" });
    }
    req.user = jwt.verify(token, "yo");
    next();
  });
};
//RETURNS NEXT() IF PASSWORD IS VALID, OR GIVES ERROR MESSAGE
module.exports.validPass = async (req, res, next) => {
  const pass = req.body.password;
  let isPasswordValid;
  const user = await User.findOne({
    username: req.body.username,
  })
    .then(async (user) => {
      
        isPasswordValid = await bcrypt.compare(pass, user.password);
        if(isPasswordValid){
          next();
        }
        else{
          return res.status(401).send({ message: "Unauthorized!" });

        }
            
    })
    .catch((err) => res.status(404).json("error" + err));
};
