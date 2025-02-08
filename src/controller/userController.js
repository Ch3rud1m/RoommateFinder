const User = require('../model/userModel');
const { matchUsersByYear } = require('../service/matchService');

// Function to create a new user
const createUser = async (req, res) => {
  const { studentId, gradYear, preferences } = req.body;

  try {
    const newUser = new User({
      studentId,
      gradYear,
      preferences,
    });

    await newUser.save();
    res.status(201).json(newUser);
  } catch (error) {
    res.status(500).json({ message: 'Error creating user', error });
  }
};

// Function to get a user by their student ID
const getUser = async (req, res) => {
  const { studentId } = req.params;

  try {
    const user = await User.findOne({ studentId });
    if (!user) {
      return res.status(404).json({ message: 'User not found' });
    }
    res.json(user);
  } catch (error) {
    res.status(500).json({ message: 'Error fetching user', error });
  }
};

// Function to get matches for a user
const getMatches = async (req, res) => {
  const { studentId } = req.params;

  try {
    // Call the matching service to get users with the same grad year
    const matches = await matchUsersByYear(studentId, User);
    res.json(matches);  // Return the list of matches
  } catch (error) {
    res.status(500).json({ message: 'Error fetching matches', error });
  }
};

// Export controller functions
module.exports = {
  createUser,
  getUser,
  getMatches,  // Add the getMatches function
};
