const http = require('http');
const https = require('https');
const express = require('express');
const fs = require('fs');
const bodyParser = require('body-parser');
const path = require('path');

var options = {
    key: fs.readFileSync('certs/key.pem'),
    cert: fs.readFileSync('certs/cert.pem')
};

const httpPort = 80;
const httpsPort = 443;

const app = express();
app.use(express.urlencoded());
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.json());


http.createServer(app).listen(httpPort, () => console.log(`Server started (http) on port ${httpPort}`));
https.createServer(options, app).listen(httpsPort, () => console.log(`Server started (https) on port ${httpsPort}`));
