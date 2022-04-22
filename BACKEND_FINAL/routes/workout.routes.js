var express = require("express");
var router = express.Router();

var workoutController = require("../controllers/workout.controller");
var authController = require("../middleware/auth.controller");

/*

WORKOUT ROUTES, EACH ROUTE FOLLOWING RESTFUL API URLS,
USING MIDDLEWARE IN THE FORM OF AUTHENTICATION FOR EACH ROUTE

USING: POST, GET, PUT, DELETE
WITH CRUD OPERATIONS: CREATE, READ, UPDATE AND DELETE

*/

//POST,CREATE
router.post("/", authController.sortData,authController.verifyToken,workoutController.create);
//GET,READ
router.get("/", authController.verifyToken, workoutController.findAll);
//GET ONE, READ
router.get("/:id", authController.verifyToken, workoutController.findOne);
router.get("/where/search", authController.verifyToken,workoutController.findOneWhere);

//GET ONE, READ
//DELETE,DELETE
router.delete("/:id",authController.verifyToken, workoutController.deleteUser);
//PUT,UPDATE
router.put("/update/:id", workoutController.updateUser);
module.exports = router;
