const express = require('express');
const { createUser, getUser, getMatches } = require('../controller/userController');
const router = express.Router();

// Route for creating a new user
router.post('/', createUser);

// Route for getting a user by student ID
router.get('/:studentId', getUser);

// Route for getting matches for a user by their student ID
router.get('/:studentId/matches', getMatches);  // New route to fetch matches

module.exports = router;
