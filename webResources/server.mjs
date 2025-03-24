import http from 'http';
import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

const getContentType = (ext) => {
    switch (ext) {
        case '.html': return 'text/html';
        case '.js': return 'text/javascript';
        case '.css': return 'text/css';
        case '.json': return 'application/json';
        default: return 'text/plain';
    }
};

const server = http.createServer((req, res) => {
    const urlPath = req.url === '/' ? '/adminDashboard.html' : req.url;
    const filePath = path.join(__dirname, urlPath);
    const ext = path.extname(filePath);

    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(404, { 'Content-Type': 'text/plain' });
            res.end('404 - File Not Found');
        } else {
            res.writeHead(200, { 'Content-Type': getContentType(ext) });
            res.end(data);
        }
    });
});

server.listen(3000, '0.0.0.0', () => {
    console.log('🌐 Server running and accessible on your local network');
});
