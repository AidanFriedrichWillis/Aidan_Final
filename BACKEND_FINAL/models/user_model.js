const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const userSchema = new Schema(
  {
    fullname: {
      type: String,
      required: true,
      minlength: 1,
      maxlength: 75
    },
    username: {
      type: String,
      required: true,
      unique: true,
      minlength: 1,
      maxlength:20
    },
    email: {
      type: String,
      required: true,
      unique: true,
      minlength: 1,
      maxlength: 50
    },
    password: {
      type: String,
      required: true,
      minlength: 1,

    }
  },
  {
    timestamps: true,
  }
);

const User = mongoose.model("User", userSchema);

module.exports = User;
