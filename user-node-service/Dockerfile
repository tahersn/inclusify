# Use an official Node.js runtime as a parent image
FROM node:18

# Set the working directory in the container
WORKDIR /app

# Copy package.json and package-lock.json to the working directory
COPY package*.json ./

# Install app dependencies
RUN npm install

# Copy the rest of your application code to the container
COPY . .

# Expose the port your Node.js app will run on
EXPOSE 3500

# Define the command to run your Node.js app
CMD ["npm", "start"]
