const express = require("express");
const router = express.Router();
const UserController = require("../controllers/UserController");

// Create a new user
router.post("/", UserController.createUser);

// Get a list of all users
router.get("/", UserController.getAllUsers);

// Get a specific user by ID
router.get("/:userId", UserController.getUserById);

// Update a specific user by ID
router.put("/:userId", UserController.updateUser);

// Delete a specific user by ID
router.delete("/:userId", UserController.deleteUser);

router.post("/login", UserController.login);

router.post("/checkEmail", UserController.checkIfEmailExist);

//router.post("/editProfile/:id", multerImg.single("profileImage"), userController.modifyUser);

router.get("/verify/:token", UserController.verifyUser);



router.get("/byId/:id", UserController.getUserById);


module.exports = router;
