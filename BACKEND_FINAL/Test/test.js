const chai = require("chai");
let server = require("../server");
const chaiHttp = require("chai-http");
var jwt = require("jsonwebtoken");

chai.should();
chai.use(chaiHttp);
let adminToken;
let employeeToken;
let token;
let userclient;
let useemp;
let ree = "Hello, my"
describe("API REQUESTS USERS", () => {
  const account = {
    data: '{"password":"password","fullname":"fullnameRouteTest","email":"EmailRouteTest","username":"userNameRouteTest"}',
  };

  

  it("Testing POST CREATE user", (done) => {
    chai
      .request("10.65.199.137/api/user")
      .post("/")
      .set("Content-Type", "application/json")
      .send(account)
      .end(function (err, response) {
        response.should.have.status(201);
        done();
      });
  });

  it("Testing POST SIGNIN Client CREATE TOKEN", (done) => {
    chai
      .request("10.65.199.137/api/user")
      .post("/signin")
      .set("Content-Type", "application/json")
      .send(account)
      .end(async function (err, response) {
        response.should.have.status(200);
        token = await response.body;
        done();
      });
  });
  it("Testing DELETE request as UNAUTHED", (done) => {
    chai
      .request("10.65.199.137/api/user")
      .delete("/")
      .set({ Authorization: "InvalidToken" })
      .end(function (err, response) {
        response.should.have.status(401);
        done();
      });
  });
  it("Testing  request wiith NO TOKEN", (done) => {
    chai
      .request("10.65.199.137/api/user")
      .delete("/")
      .end(function (err, response) {
        response.should.have.status(403);
        done();
      });
  });
});


describe("API REQUESTS WORKOUT", () => {

const workout = {
  data: '{"date":"1650213820936","exerises":[{"name":"TESTEX","reps":2,"sets":3,"weight":15}],"name":"TESTWORKOUT"}',
}; ;


 it("Testing POST WORKOUT VALID TOKEN ", (done) => {
   chai
     .request("10.65.199.137/api/workout")
     .post("/")
     .set({ Authorization: token })
     .send(workout)
     .end(function (err, response) {
       response.should.have.status(201);
       done();
     });
 });
 it("Testing POST WORKOUT NO TOKEN ", (done) => {
   chai
     .request("10.65.199.137/api/workout")
     .post("/")
     .send(workout)
     .end(function (err, response) {
       response.should.have.status(403);
       done();
     });
 });
  it("Testing POST WORKOUT INVALID TOKEN ", (done) => {
    chai
      .request("10.65.199.137/api/workout")
      .post("/")
      .set({ Authorization:"INVLAID TOKEN" })
      .send(workout)
      .end(function (err, response) {
        response.should.have.status(401);
        done();
      });
  });

  it("Testing GET ALLWORKOUTS", (done) => {
    chai
      .request("10.65.199.137/api/workout")
      .get("/")
      .set({ Authorization: token })
      .end(function (err, response) {
        response.should.have.status(200);
        done();
      });
  });
    it("Testing GET ONE WHERE", (done) => {
      chai
        .request("10.65.199.137/api/workout")
        .get(`/where/search?name=TESTEX`)
        .set({ Authorization: token })
        .end(function (err, response) {
          response.should.have.status(200);
          done();
        });
    }); 



 it("Testing DELETE ", (done) => {
   chai
     .request("10.65.199.137/api/user")
     .delete("/")
     .set({ Authorization: token })
     .end(function (err, response) {
       response.should.have.status(200);
       done();
     });
 });





})