let rest = require('restler');
let path = require("path");
let configuration = require('../../config/backend-config');

exports.getImagesById = function (request, response) {

  let dir = path.join(__dirname, '../../mock/image/rose-blue-flower-rose-blooms-67636.jpeg');

  response.sendFile(dir);

};

