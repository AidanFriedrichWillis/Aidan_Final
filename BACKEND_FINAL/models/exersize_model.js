const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const exersizeSchema = new Schema(
  {
    name: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      minlength: 1,
    },
    reps: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      minlength: 1,
    },
    sets: {
      type: String,
      required: true,
      unique: true,
      trim: true,
      minlength: 1,
    },
    weight: {
      type: String,
      required: true,
      trim: true,
      minlength: 1,
    },
  },
  {
    timestamps: true,
  }
);

const User = mongoose.model("exersize", exersizeSchema);

module.exports = User;
