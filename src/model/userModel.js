// model/userModel.js
const mongoose = require('mongoose');

// fix this later 
const userSchema = new mongoose.Schema({
  studentId: {
    type: String,
    required: true,
    unique: true,
  },
  studentName: {
    type: String,
    required: true,
  },
  absolutes: {
    gradYear: {
      type: Number,
      required: true,
    },
    quadConnection: {
      type: String,
      required: true,
    },
    gender: {
      type: String,
      required: true,
    }
  },

  preferences: {
    sleepingHabits: {
      type: String,
      required: true,
    },
    cleanliness: {
      type: Boolean,
      required: true,
    },
    sexuallyActive: {
      type: Boolean,
      required: true,
    },
    substanceUse: {
      type: Boolean,
      required: true,
    },
    school: {
      type: String,
      required: true,
    },
    major: {
      type: String,
      required: true,
    },
  },
});

// Create and export the model
const User = mongoose.model('User', userSchema);
module.exports = User;
