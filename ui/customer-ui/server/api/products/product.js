let rest = require('restler');
let path = require("path");
let configuration = require('../../config/backend-config');
const jsonFile = require('jsonfile');

exports.getProductList = function (request, response) {

  rest.postJson(configuration.perEnvironment.backendUrl + '/customer/products',request.body)
    .on('success', (backendResponseBody, backendResponseMeta) => {
      response.json(backendResponseBody);
    })
    .on('fail', (backendResponseBody, backendResponseMeta) => {

      response.status(backendResponseMeta.statusCode).send(backendResponseBody);
    });
};

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
};

exports.submitOrder = function (request, response) {
  const file = '../customer-ui/server/mock/json/submitOrderResponse.json';
  jsonFile.readFile(file, function (err, obj) {
    if (err) {
      console.error(err);
    }
    response.send(obj);
  })
};
