const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const connection = require("./utils/DatabaseConfig");
const userRouter = require("./routes/UserRouter");
const path = require("path");
const swaggerUi = require("swagger-ui-express");
const swaggerFile = require("../swagger_output.json");

connection.getConnections();

const app = express();

app.use(cors());
// for cors origin config
app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Headers",
    "Origin, X-Requested-With, Content, Accept, Content-Type, Authorization"
  );
  res.setHeader(
    "Access-Control-Allow-Methods",
    "GET, POST, PUT, DELETE, PATCH, OPTIONS"
  );
  next();
});
app.use(express.json());
app.use(bodyParser.json({ limit: "20mb" }));
app.use(bodyParser.urlencoded({ limit: "20mb", extended: true }));

// app.use("/images", express.static(path.join(__dirname, "utils/images")));
// app.use("/files", express.static(path.join(__dirname, "utils/files")));
// app.use("/public", express.static(path.join(__dirname, "utils/public")));

app.use("/users", userRouter);

// The middleware that allows swagger to work
app.use("/api-docs", swaggerUi.serve, swaggerUi.setup(swaggerFile));

app.get("/", (req, res) => {
  res.send("Everything works fine");
});

module.exports = app;
