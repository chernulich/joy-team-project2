let rest = require('restler');
let configuration = require('../../config/backend-config');

exports.getProductList = function (request, response) {

    const jsonFile = require('jsonfile');
    const file = '../customer-ui/server/mock/json/productsList.json';
    jsonFile.readFile(file, function (err, obj) {
        if (err) {
            console.error(err);
        }
        response.send(obj);
    })

}

