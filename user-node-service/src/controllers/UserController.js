const User = require("../models/UserModel"); // Import the User model

// Controller function to create a new user
exports.createUser = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Add a new user.'

  /* #swagger.parameters['user'] = {
               in: 'body',
               description: 'A full user.',
               required: true,
               schema: { $ref: "#/definitions/User" }
        } */

  try {
    const userData = req.body; // Assuming you send user data in the request body
    const user = new User(userData);
    const savedUser = await user.save();
    res.status(201).json(savedUser);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Controller function to get a list of all users
exports.getAllUsers = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Get all users.'
  try {
    const users = await User.find();
    res.status(200).json(users);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Controller function to get a specific user by ID
exports.getUserById = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Get user by his id.'
  try {
    const userId = req.params.userId; // Assuming you pass the user ID in the route params
    const user = await User.findById(userId);
    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }
    res.status(200).json(user);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Controller function to update a specific user by ID
exports.updateUser = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Modify the user.'

  /* #swagger.parameters['user'] = {
               in: 'body',
               description: 'A full user.',
               required: true,
               schema: { $ref: "#/definitions/User" }
        } */
  try {
    const userId = req.params.userId; // Assuming you pass the user ID in the route params
    const userData = req.body; // Updated user data
    const updatedUser = await User.findByIdAndUpdate(userId, userData, {
      new: true,
    });
    if (!updatedUser) {
      return res.status(404).json({ message: "User not found" });
    }
    res.status(200).json(updatedUser);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Controller function to delete a specific user by ID
exports.deleteUser = async (req, res) => {
  // #swagger.tags = ['User']
  // #swagger.description = 'Delete one user.'
  try {
    const userId = req.params.userId; // Assuming you pass the user ID in the route params
    const deletedUser = await User.findByIdAndRemove(userId);
    if (!deletedUser) {
      return res.status(404).json({ message: "User not found" });
    }
    res.status(204).send();
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
