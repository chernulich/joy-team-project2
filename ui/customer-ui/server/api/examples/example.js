let http = require('http');
let rest = require('restler');
let configuration = require('../../config/backend-config');

exports.getExampleList = function (request, response) {

  rest.get(configuration.perEnvironment.backendUrl + '/examples').on('success', (backendResponseBody, backendResponseMeta) => {
    response.json(backendResponseBody);
  });
};

exports.getExampleById = function (request, response) {
  let id = request.params.id;

  rest.get(configuration.perEnvironment.backendUrl + '/examples/' + id).on('success', (backendResponseBody, backendResponseMeta) => {
    response.json(backendResponseBody);
  });
};

exports.saveExample = function (request, response) {
  let body = request.body;

  rest.postJson(configuration.perEnvironment.backendUrl + '/examples', body)
    .on('success', (backendResponseBody, backendResponseMeta) => {
      response.json(backendResponseBody);
    })
    .on('fail', (backendResponseBody, backendResponseMeta) => {
      response.status(backendResponseMeta.statusCode).send(backendResponseBody);
    });
};
