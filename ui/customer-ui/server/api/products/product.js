let rest = require('restler');
let path = require("path");
let configuration = require('../../config/backend-config');
const jsonFile = require('jsonfile');

exports.getImagesById = function (request, response) {

  let dir = path.join(__dirname, '../../mock/image/rose-blue-flower-rose-blooms-67636.jpeg');

  response.sendFile(dir);

};

exports.getProductDetails = function (request, response) {

  const file = '../customer-ui/server/mock/json/productDetail.json';
  jsonFile.readFile(file, function (err,  obj) {
      if(err) {
        console.error(err);
      }
      response.send(obj);
  })


}
