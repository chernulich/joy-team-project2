const express = require('express');
const app = express();

const PORT = process.env.PORT || 4200;
const DIST_FOLDER = __dirname + '/../dist/customer-ui';

const ngExpressEngine = require('@nguniversal/express-engine').ngExpressEngine;
app.engine('html', ngExpressEngine({}));


app.set('view engine', 'html');
app.set('views', DIST_FOLDER);
app.use(express.static(DIST_FOLDER));


app.get('*.*', express.static(DIST_FOLDER, {
  maxAge: '1y'
}));

let routes = {
  api: {
    example: require('./api/examples/example')
  }
};

app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.get('/api/examples', routes.api.example.getExampleList);
app.get('/api/examples/:id', routes.api.example.getExampleById);
app.post('/api/examples', routes.api.example.saveExample);


// All regular routes use the Universal engine
app.get('*', (req, res) => {
  res.render('index', {req});
});

// Start up the Node server
app.listen(PORT, () => {
  console.log(`Node Express server listening on http://localhost:${PORT}`);
});


