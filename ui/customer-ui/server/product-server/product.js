const express = require('express');
const app = express();
const PORT = process.env.PORT || 3000;

const jsonFile = require('jsonfile');
const file = '.././product-server/products-response.json';
jsonFile.readFile(file, function (err, obj) {
    if (err) console.error(err)
    app.post('/api/customer/products',  (req, res) => {
        res.send(obj);
    });
})

app.listen(PORT, () => {
    console.log(`Node Express server listening on http://localhost:${PORT}`);
});
