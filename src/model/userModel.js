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
    sleepingHour: {
      type: String,
      required: true,
    },
    wakingHour: {
      type: String,
      required: true,
    },
    sexuallyActive: {
      type: Number,
      required: true,
    },
    substanceUse: {
      type: Boolean,
      required: true,
    },
    soberRoommate: {
      type: Boolean,
      required: true,
    },
    cleanliness: {
      type: Number,
      required: true,
    },
    hoursInDorm: {
      type: String,
      required: true,
    },
    hobbies: {
      type: String,
      required: true,
    },
    major: {
      type: String,
      required: true,
    },
    friendsOver: {
      type: Number,
      required: true,
    },
  },
});

// Create and export the model
const User = mongoose.model('User', userSchema);
module.exports = User;
