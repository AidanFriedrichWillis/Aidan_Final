var express = require("express");
var router = express.Router();

var userController = require("../controllers/user.controller");
var authController = require("../middleware/auth.controller");

/*

BOOK ROUTES, EACH ROUTE FOLLOWING RESTFUL API URLS,
USING MIDDLEWARE IN THE FORM OF AUTHENTICATION FOR EACH ROUTE

USING: POST, GET, PUT, DELETE
WITH CRUD OPERATIONS: CREATE, READ, UPDATE AND DELETE

*/

//POST,CREATE
router.post("/", userController.signup);
//POST,CREATE
router.post("/signin", userController.signin);
//GET,READ
router.get(
  "/",
  userController.findAll
);
//DELETE,DELETE
router.delete(
  "/:id",
  userController.deleteUser
);
//PUT,UPDATE
router.put(
  "/update/:id",
  userController.updateUser
);
module.exports = router;
