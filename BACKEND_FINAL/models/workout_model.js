const mongoose = require("mongoose");

const Schema = mongoose.Schema;
const workoutSchema = new Schema(
  {
    name: {
      type: String,
      required: true,
      minlength: 1,
    },
    date: {
      type: String,
    },
    exersizes: {
      type: Array,
      default: [],
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

const Workout = mongoose.model("workout", workoutSchema);

module.exports = Workout;
