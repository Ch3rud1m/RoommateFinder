// server.js
const express = require('express');
const dotenv = require('dotenv');
const connectDB = require('./config/db');
const userRoutes = require('./routes/userRoutes');

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
