const User = require("../models/UserModel");
require("dotenv").config();
const bcrypt = require("bcrypt");
// const fs = require("fs");
// const mailingService = require('../utils/MailingService')
// const roles = require('../models/enums/RoleEnum')
// const {generateVerificationToken, verifyVerificationToken} = require("../utils/TokenHandler")


exports.createUser = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Add a new user.'

  /* #swagger.parameters['user'] = {
             in: 'body',
             description: 'A full user.',
             required: true,
             schema: { $ref: "#/definitions/User" }
      } */
  try {
    // Check if the email is already taken
    const existingUser = await User.findOne({email: req.body.email});
    if (existingUser) {
      return res.status(400).json({message: 'Email already taken'});
    }

    let picPath = `${req.protocol}://${req.get("host")}/images/noProfilePic.png`;
    // check if request contains the image paths ( else it will take the no profile pic )
    if (req.file) {
      picPath = `${req.protocol}://${req.get("host")}/images/${req.file.filename}`
    }
    // create the user
    const hashedPassword = await bcrypt.hash(req.body.password, Number.parseInt(process.env.CRYPT_SALT));
    const user = new User({
      ...req.body,
      profileImagePath: picPath,
      email: req.body.email.toLowerCase(), // save the email in lowercase
      password: hashedPassword
    });
    // save the user
    await user.save();
    const objectUser = user.toObject();
    // add the token to our object
    objectUser.token = user.generateToken();
    res.status(200).json(objectUser);

  } catch (error) {
    console.log(error)
    res.status(400).json({error});
  }
};


exports.verifyUser = async (req, res, next) => {
  try {
    const userId = await verifyVerificationToken(req.params.token);
    const user = await User.findById(userId);

    user.isVerified = true;

    await user.save();
    res.status(200).json({message: 'Account verified'});
  } catch (err) {
    res.status(400).json({error: err.message});
  }
}

exports.checkIfEmailExist = async (req,res) =>{
  try {

    // Check if the email is already taken
    const {email} = req.body;
    const user = await User.findOne({email:email});

    if(user){
      return res.json(true);
    }
    return res.json(false);
  } catch (err) {
    res.status(400).json({error: err.message});
  }
}

exports.resetPassword = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Reset you password.'
  try {
    const user = await User.findOne({email: req.body.email.toLowerCase()});
    if (!user) {
      return res.status(404).json({message: 'User not found'});
    }

    const token = generateVerificationToken(user._id);
    const url = `${req.protocol}://${req.get("host")}/api/users/resetPassword/${token}`;
    const url1 = `http://localhost:5000/verifyToken/${token}`;
    // send the email to the user
    await mailingService.sendResetPasswordEmail(user, url1)

    res.status(200).json({message: 'An email has been sent to reset your password'});
  } catch (err) {
    res.status(400).json({message: err.message});
  }
}

exports.verifyResetPassword = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Change password.'
  try {
    const userId = await verifyVerificationToken(req.params.token);
    const user = await User.findById(userId);
    console.log(user);
    if (!user) {
      return res.status(404).json({message: 'Invalid or expired token'});
    }
    const hashedPassword = await bcrypt.hash(req.body.password, Number.parseInt(process.env.CRYPT_SALT));

    user.password = hashedPassword;
    const user2 = await user.save();

    res.status(200).json({message: 'Your password has been changed'});
  } catch (err) {
    res.status(400).json({message: err.message});
  }
}



exports.verifyResetPassword = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Change password.'
  try {
    const userId = await verifyVerificationToken(req.params.token);
    const user = await User.findById(userId);
    console.log(user);
    if (!user) {
      return res.status(404).json({message: 'Invalid or expired token'});
    }
    const hashedPassword = await bcrypt.hash(req.body.password, Number.parseInt(process.env.CRYPT_SALT));

    user.password = hashedPassword;
    const user2 = await user.save();
    console.log("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
    console.log(user2)
    res.status(200).json({message: 'Your password has been changed'});
  } catch (err) {
    res.status(400).json({message: err.message});
  }
}


exports.verifyPassword = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Same as login, just return true or false.'
  try {
    const {email, password} = req.body;

    if (!req.body.email) {
      return res.status(422).json({messageText: "Email can't be blank", buttonText: "Retry"});
    }
    if (!req.body.password) {
      return res.status(422).json({messageText: "Password can't be blank", buttonText: "Retry"});
    }

    const user = await User.findOne({email: email.toLowerCase()});

    const valid = await user.verifyPassword(password);

    if (!valid) {
      return res.status(401).json(false);
    }

    res.status(200).json(true);

  } catch (err) {
    res.status(500).json(false);
  }
}

exports.login = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Login and generate the token.'

  try {
    const {email, password} = req.body;

    if (!req.body.email) {
      return res.status(422).json({message: "Email can't be blank", buttonText: "Retry"});
    }
    if (!req.body.password) {
      return res.status(422).json({message: "Password can't be blank", buttonText: "Retry"});
    }

    const user = await User.findOne({email: email.toLowerCase()});

    if (!user) {
      return res.status(401).json({message: "Can't find user", buttonText: "Retry"});
    }
    const valid = await user.verifyPassword(password);

    if (!valid) {
      return res.status(401).json({message: "Wrong password", buttonText: "Retry"});
    }
    // since mongoose document can't be changed, we should convert them into an object to add the token
    const objectUser = user.toObject();
    // add the token to our object
    objectUser.token = user.generateToken();
    // return the whole object with its token
    res.status(200).json(objectUser);

  } catch (err) {
    console.log(err)
    res.status(500).json({err});
  }
};

/**
 * Find users using the pagination
 * */
exports.getAllUsers = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Find users using the pagination.'

  const page = parseInt(req.query.page) || 1;
  const limit = parseInt(req.query.limit) || 10;
  const skip = (page - 1) * limit;

  try {
    const users = await User.find().skip(skip).limit(limit);
    const count = await User.countDocuments();

    res.status(200).json({
      total: count,
      page,
      limit,
      data: users,
    });
  } catch (error) {
    res.status(400).json({error});
  }
};

/**
 * get user by id
 * */
exports.getUserById = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Get user by his id.'

  const userId = req.params.id;

  try {
    const user = await User.findById(userId)
    if (!user) {
      return res.status(404).json({message: 'User not found'});
    }
    const objectUser = user.toObject();
    // add the token to our object
    objectUser.token = user.generateToken();
    res.status(200).json(objectUser);
  } catch (error) {
    res.status(400).json({error});
  }
};


exports.getUserByEmail = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Get user by his id.'

  const userEmail = req.params.email;

  try {
    /*
    const users = await User.find({
        $or: [
            {firstName: {$regex: regex}},
            {lastName: {$regex: regex}}
        ]
     */
    const user = await User.findOne({
      $or:[
        {email: userEmail}
      ]
    })
    if (!user) {
      return res.status(404).json({message: 'User not found'});
    }

    const objectUser = user.toObject();
    // add the token to our object
    objectUser.token = user.generateToken();

    res.status(200).json(objectUser);
  } catch (error) {
    res.status(400).json({error});
  }
};

exports.updateUser = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Modify the user.'

  /* #swagger.parameters['user'] = {
             in: 'body',
             description: 'A full user.',
             required: true,
             schema: { $ref: "#/definitions/User" }
      } */
  console.log("*****************************")
  try {
    const updateObject = {...req.body};

    // Hash password if provided
    if (updateObject.password) {
      const hash = await bcrypt.hash(updateObject.password, Number.parseInt(process.env.CRYPT_SALT));
      updateObject.password = hash;
    }

    // Add image path if file provided
    if (req.file) {
      updateObject.imagePath = `${req.protocol}://${req.get("host")}/utils/images/${req.file.filename}`;
    }

    // Update user document in the database
    const updatedUser = await User.findByIdAndUpdate(req.params.id, updateObject, {new: true});

    // Return updated user document
    res.status(200).json(updatedUser);
  } catch (err) {
    console.error(err);
    res.status(500).json({error: 'Internal server error'});
  }
};

exports.deleteUser = (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Delete one user.'

  // TODO uncomment the code below when applying security
  // User.findOne({_id: req.params.id}).then((user) => {
  //     if (!user) {
  //         res.status(404).json({
  //             error: new Error('No such user   !')
  //         });
  //     }
  //     if (user.userId !== req.auth.userId) {
  //         res.status(400).json({
  //             error: new Error('Unauthorized request!')
  //         })
  //     }
  User.deleteOne({_id: req.params.id})
      .then(res.status(200).json({message: "user deleted"}))
      .catch((error) => res.status(404).json({error}));
  //})
};

/**
 * Finding a list of users using their firstname / lastname
 * */
exports.getUserByName = async (req, res, next) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Get users by name.'

  const name = req.params.name;
  // Create a regular expression that matches the name parameter in a case-insensitive way
  const regex = new RegExp(name, "i");

  try {
    // Find all users whose first name or last name matches the regular expression
    const users = await User.find({
      $or: [
        {firstName: {$regex: regex}},
        {lastName: {$regex: regex}}
      ]
    })

    res.status(200).json(users);
  } catch (error) {
    console.log(error)
    res.status(400).json({error});
  }
};


    exports.blockUser = async (req, res) => {
      try {
        const user = await User.findById(req.params.userId);
        if (!user) {
          return res.status(404).send({message: "User not found"});
        }
        user.isBlocked = true;
        await user.save();
        return res.status(200).send({message: "User blocked successfully"});
      } catch (error) {
        console.log(error);
        return res.status(500).send({message: "Internal server error"});
      }
    },
    exports.approveUser = async (req, res) => {
      try {
        const user = await User.findById(req.params.userId);
        if (!user) {
          return res.status(404).send({message: "User not found"});
        }
        user.isApproved = true;
        await user.save();
        return res.status(200).send({message: "User approved successfully"});
      } catch (error) {
        console.log(error);
        return res.status(500).send({message: "Internal server error"});
      }
    };

exports.generateStreamKey = async (req, res) => {
  try {
    let streamKey= generateUniqueString()

    const userId = req.params.id;
    await User.findByIdAndUpdate(userId, {streamKey: streamKey});

    res.status(200).json(streamKey);
  } catch (err) {
    res.status(400).json({message: err.message});
  }
};

function generateUniqueString() {
  const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789^&()~[];,';
  let uniqueString = '';
  for (let i = 0; i < 30; i++) {
    uniqueString += characters.charAt(Math.floor(Math.random() * characters.length));
  }
  return uniqueString;
}

exports.setIsLiveStreaming = async (req, res) => {
  try {
    const userId = req.params.id;
    let user= await User.findByIdAndUpdate(userId, {isLiveStreaming: req.body.isLiveStreaming});

    res.status(200).json(user);
  } catch (err) {
    res.status(400).json({message: err.message});
  }
};

exports.getUsersById = async (req, res) => {
  try {
    console.log(req.body)
    const users = await User.find({ _id: { $in: req.body } }).select("firstName lastName profileImagePath")
    res.status(200).json(users);
  } catch (error) {
    res.status(400).json({error});
  }
};
