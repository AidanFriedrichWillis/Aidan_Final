//SERVER,INDEX FOR SET UP CONNECTIONS
const express = require("express");
const cors = require("cors");
const mongoose = require("mongoose");
const http = require("http");
const app = express();
const server = http.createServer(app);
const bodyParser = require("body-parser");
//USING CORS AS A SAFTY MIDDLEWARE
app.use(cors());
const port = 5000;
//STARTING THE DATABASE COMMUNICATION
const uri = "mongodb://localhost:27017/MY_FITDB";
mongoose.connect(uri);
const connection = mongoose.connection;
connection.once("open", () => {
  console.log("MongoDB database connection established successfully");
});
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));
//STARTING MY ROUTER COMMUINICATIONS
const usersRouter = require("./routes/user.routes");
app.use("/api/user", usersRouter);
const workoutRouter = require("./routes/workout.routes");
app.use("/api/workout", workoutRouter);
//SERVER LISTENING ON THE PORT
server.listen(80, "100.81.96.73", () => console.log(`Server has started.`));
module.exports = server;


