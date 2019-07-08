let http = require('http');
let rest = require('restler');
let configuration = require('../../config/backend-config');

exports.getProductList = function (request, response) {
    let body = request.body;

    rest.postJson(configuration.perEnvironment.jsonUrl + '/customer/products', body)
        .on('success', (backendResponseBody, backendResponseMeta) => {
            response.json(backendResponseBody);
        })
        .on('fail', (backendResponseBody, backendResponseMeta) => {
            response.status(backendResponseMeta.statusCode).send(backendResponseBody);
        });
};
