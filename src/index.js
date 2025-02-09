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

        const newUser = new User({
          studentId: 'jlx5',
          studentName: 'Jamal Xiao',
          absolutes: {
            gradYear: 2028,
            quadConnection: 'Keohane',
            gender: "male",
          },
          preferences: {
            sleepingHabits: 'early',
            cleanliness: true,
            sexuallyActive: false,
            substanceUse: false,
            school: 'Pratt',
            major: 'Electrical Enngineering',
          },
        });

        return newUser.save();
      })
      .then((user) => {
        console.log('New user added:', user);
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  });
}