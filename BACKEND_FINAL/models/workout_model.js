const mongoose = require("mongoose");

const Schema = mongoose.Schema;
let exersize = require("../models/exersize_model")
const workoutSchema = new Schema(
  {
    name: {
      type: String,
      required: true,
      unique: true,
      minlength: 1,
    },
    date: {
      type: Date,
    },
    exersizes: {
      type: exersize,
    },
    userId:{
        type: String,
        required: true,
    }
  },
  {
    timestamps: true,
  }
);

const User = mongoose.model("exersize", workoutSchema);

module.exports = User;
