let envType = process.env.CUSTOMER_UI_ENV;
let perEnvironment;

switch (envType) {
  case 'PROD' :
    perEnvironment = require('./env/prod.js');
    break;
  default:
    perEnvironment = require('./env/local.js');
    break;
}

exports.perEnvironment = perEnvironment;
