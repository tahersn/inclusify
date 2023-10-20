const mongoose = require("mongoose");
const Schema = mongoose.Schema;
// const bcrypt = require("bcrypt");
// const jwt = require("jsonwebtoken");
const uniqueValidator = require("mongoose-unique-validator");
// const {hash} = require("bcrypt");
const genders = require("./enums/GenderEnum");

require("dotenv").config();

const schema = new Schema(
  {
    firstName: { type: String, required: true },
    lastName: { type: String, required: true },
    email: {
      type: String,
      required: true,
      unique: true,
      match: [/\S+@\S+\.\S+/, "is invalid"],
    },
    password: { type: String, required: true },
    gender: { type: String, default: genders.MALE },
    phoneNumber: { type: String },
    dateOfBirth: { type: Date },
  },
  { timestamps: true }
);

// A dependency used to validate unique attribute in a more efficient way
schema.plugin(uniqueValidator);

module.exports = mongoose.model("User", schema);
