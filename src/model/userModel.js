// model/userModel.js
const mongoose = require('mongoose');

// fix this later 
const userSchema = new mongoose.Schema({
  studentId: {
    type: String,
    required: true,
    unique: true,
  },
  gradYear: {
    type: Number,
    required: true,
  },
  quadConnection: {
    type: String,
    required: true,
  },
  preferences: {
    sleepingHabits: {
      type: String,
      required: true,
    },
    cleanliness: {
      type: String,
      required: true,
    },
  },
});

// Create and export the model
const User = mongoose.model('User', userSchema);
module.exports = User;
