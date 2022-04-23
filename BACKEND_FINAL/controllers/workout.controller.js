const router = require("express").Router();
let Workout = require("../models/workout_model");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");

/* USER CONTROLLER FOLLOWING MVC DESIGNS
USING CRUD REQUESTS TO THE NOSQL DATABSE 
USING PROMISES ON REQUESTS

*/

//READ ALL
module.exports.findAll = async (req, res) => {
  const id = req.user.id;
  const workout = await Workout.find({
    userId: id,
  });
  if (workout) {
    return res.status(200).json(workout);
  } else {
    res.status(404).json("Not Found");
  }
};
//READ ONE
module.exports.findOne = async (req, res) => {
  const id = req.params.id;
  const workout = await Workout.find({
    _id: id,
  });
  if (workout) {

    return res.status(200).json(workout);
  } else {
    res.status(404).json("Not Found");
  }
};

//get pb
module.exports.findOneWhere = async (req, res) => {
  const name = req.query.name;
  const id = req.user.id;
  let list = [];
  let biggest = 0;
  var today = new Date();
   await Workout.find({userId:id}).then((workouts) => {
    for (let i = 0; i < 12; i++) {
      
      workouts.forEach(async (workout) => {
        var date =  new Date(parseInt(workout.date));
        workout.exersizes.forEach((exersize) => {
          if (
            exersize.weight > biggest &&
            i == date.getMonth() &&
            exersize.name == name  
          ) {
            biggest = exersize.weight;
            
          }
        });
      });
      list.push(biggest);
      biggest = 0;
    }
    res.status(200).json(list);
  });

};

//CREATE
module.exports.create = async (req, res) => {

  const name = req.body.name;
  const exersizes = req.body.exerises;
  const date = req.body.date;
  const userId = req.user.id;
  const workout = new Workout({ name, date, exersizes, userId });
  workout
    .save()
    .then(() => res.status(201).json("Workout Saved!"))
    .catch((err) => {
      res.status(400).json("Error: ");
    });
};

//DELETE USER WHERE ID=ID
module.exports.deleteWorkout = async (req, res) => {

  const id = req.params.id;
  Workout.findByIdAndRemove(id)
    .then((data) => {
      if (!data) {
        res.status(404).send({
          message: `Cannot delete WORKOUT with id=${id}. Maybe User was not found!`,
        });
      } else {
        res.status(200).send({
          message: "WORKOUT was deleted successfully!",
        });
      }
    })
    .catch((err) => {
      res.status(500).send({
        message: "Could not delete WORKOUT with id=" + id,
      });
    });
};
