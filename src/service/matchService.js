//edit this later to implement the matching algorithm

// Sample matching algorithm based on grad year
const matchUsersByYear = async (studentId, UserModel) => {
    try {
      const user = await UserModel.findOne({ studentId });
  
      if (!user) {
        throw new Error('User not found');
      }
  
      // Get potential matches based on grad year
      const matches = await UserModel.find({
        gradYear: user.gradYear,
        studentId: { $ne: studentId },  // Exclude self
      });
  
      return matches;
    } catch (error) {
      throw new Error('Error finding matches: ' + error.message);
    }
  };
  
  module.exports = {
    matchUsersByYear,
  };
  