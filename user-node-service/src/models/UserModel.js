const mongoose = require("mongoose");
const Schema = mongoose.Schema;
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");
const uniqueValidator = require("mongoose-unique-validator");
// const {hash} = require("bcrypt");
const genders = require("./enums/GenderEnum");

require("dotenv").config();

const schema = new Schema(
    {
        firstName: {type: String, required: true},
        lastName: {type: String, required: true},
        username: {type: String, required: true},
        email: {
            type: String,
            required: true,
            unique: true,
            match: [/\S+@\S+\.\S+/, "is invalid"],
        },
        password: {type: String, required: true},
        gender: {type: String, default: genders.MALE},
        profileImagePath: String,
        phoneNumber: {type: String},
        dateOfBirth: {type: Date},
    },
    {timestamps: true}
);

// A dependency used to validate unique attribute in a more efficient way
schema.plugin(uniqueValidator);

/**
 * Verify user password
 * */
schema.methods.verifyPassword = async function (password) {
    console.log(password)
    // console.log(this.password)
    let validation = false;
    await bcrypt
        .compare(password, this.password)
        .then((valid) => {
            // console.log(valid)
            validation = valid;
        })
        .catch((error) => ({error}));
    return validation;
};

schema.methods.generateToken = function () {
    let today = new Date();
    let exp = new Date(today);
    exp.setDate(today.getDate() + 60);

    return jwt.sign(
        {
            id: this._id,
            email: this.email,
            exp: parseInt(exp.getTime() / 1000),
        },
        process.env.JWT_SECRET
    );
};

module.exports = mongoose.model("User", schema);
