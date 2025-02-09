// server.js
const express = require('express');
const dotenv = require('dotenv');
const cors = require('cors');
const mongoose = require('mongoose');
const connectDB = require('./config/db');
const userRoutes = require('./route/userRoutes');
const User = require('./model/userModel'); // Import the User model

// Initialize environment variables
dotenv.config();

// Create an Express app
const app = express();

// Middleware
app.use(express.json());  // For parsing JSON data

// Connect to MongoDB
connectDB();

// Setup routes
app.use('/api/users', userRoutes);

// Start the server
const PORT = process.env.PORT || 5000;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}`);
});

if (process.env.NODE_ENV !== 'production') {
  mongoose.connection.once('open', () => {
    const db = mongoose.connection.db;
    db.dropDatabase()
      .then(() => {
        console.log('Database dropped');

        const newUser1 = new User({
          studentId: 'jlx5',
          studentName: 'Jamal Xiao',
          absolutes: {
            gradYear: 2028,
            quadConnection: 'Keohane',
            gender: "male",
          },
          preferences: {
            sleepingHour: '12:00 am to 1:00 am',
            wakingHour: '9:00 am to 10:00 am',
            sexuallyActive: 5,
            substanceUse: false,
            soberRoommate: true,
            cleanliness: 5,
            hoursInDorm: '4-8 hours',
            hobbies: 'Sports and fitness',
            major: 'Electrical Engineering and Computer Science',
            friendsOver: 4,
          },
        });

        const newUser2 = new User({
          studentId: 'ajy20',
          studentName: 'Alec Yang',
          absolutes: {
            gradYear: 2028,
            quadConnection: 'Keohane',
            gender: "male",
          },
          preferences: {
            sleepingHour: '12:00 am to 1:00 am',
            wakingHour: '9:00 am to 10:00 am',
            sexuallyActive: 5,
            substanceUse: false,
            soberRoommate: true,
            cleanliness: 5,
            hoursInDorm: '2-4 hours',
            hobbies: 'Technology and gaming',
            major: 'Biomedical and Health Sciences',
            friendsOver: 2,
          },
        });

        return Promise.all([newUser1.save(), newUser2.save()]);
      })
      .then((users) => {
        console.log('New users added:', users);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  });
}