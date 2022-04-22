var express = require("express");
var router = express.Router();

var userController = require("../controllers/user.controller");
var authController = require("../middleware/auth.controller");

/*

  USER ROUTES, EACH ROUTE FOLLOWING RESTFUL API URLS,
  USING MIDDLEWARE IN THE FORM OF AUTHENTICATION FOR EACH ROUTE
  USING: POST, GET, PUT, DELETE
  WITH CRUD OPERATIONS: CREATE, READ, UPDATE AND DELETE

*/

//POST,CREATE
router.post("/",authController.sortData ,userController.signup);
//POST,CREATE
router.post("/signin", authController.sortData,authController.validPass, userController.signin);
//GET,LOGGED
router.get("/logged", authController.verifyToken, userController.accept);
//DELETE,DELETE
router.delete("/", authController.verifyToken, userController.deleteUser);
//PUT,UPDATE
router.put(
  "/update/:id",
  userController.updateUser
);
module.exports = router;
