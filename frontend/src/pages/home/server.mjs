import http from 'node:http';
import { execFileSync } from 'node:child_process';
import { readFile } from 'node:fs/promises';
import path from 'node:path';
import { fileURLToPath } from 'node:url';

const PORT = Number(process.env.HOME_API_PORT || 8080);
const MYSQL_BIN = process.env.MYSQL_BIN || 'C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysql.exe';
const MYSQL_HOST = process.env.MYSQL_HOST || 'localhost';
const MYSQL_PORT = process.env.MYSQL_PORT || '3306';
const MYSQL_USER = process.env.MYSQL_USER || 'root';
const MYSQL_PASSWORD = process.env.MYSQL_PASSWORD || '12345';
const MYSQL_DATABASE = process.env.MYSQL_DATABASE || 'QuanLyDatDoAn';
const homeDir = path.resolve(path.dirname(fileURLToPath(import.meta.url)));

const staticFiles = new Map([
    ['/', { file: 'index.html', type: 'text/html; charset=utf-8' }],
    ['/index.html', { file: 'index.html', type: 'text/html; charset=utf-8' }],
    ['/css/index.css', { file: path.join('css', 'index.css'), type: 'text/css; charset=utf-8' }],
    ['/js/home.js', { file: path.join('js', 'home.js'), type: 'text/javascript; charset=utf-8' }],
]);

function queryMySQL(sql) {
    const args = [
        '--default-character-set=utf8mb4',
        `-h${MYSQL_HOST}`,
        `-P${MYSQL_PORT}`,
        `-u${MYSQL_USER}`,
        `-p${MYSQL_PASSWORD}`,
        '-D',
        MYSQL_DATABASE,
        '--batch',
        '--raw',
        '--skip-column-names',
        '-e',
        sql,
    ];

    return execFileSync(MYSQL_BIN, args, {
        encoding: 'utf8',
        stdio: ['ignore', 'pipe', 'pipe'],
    }).trim();
}

function parseJson(raw, fallback) {
    if (!raw) return fallback;
    return JSON.parse(raw);
}

function sqlString(value) {
    return `'${String(value).replaceAll('\\', '\\\\').replaceAll("'", "\\'")}'`;
}

function getCategories() {
    const raw = queryMySQL(`
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'maDM', d.MaDM,
                'tenDM', d.TenDM,
                'moTa', d.MoTa,
                'hinhAnh', (
                    SELECT m.HinhAnh
                    FROM MonAn m
                    WHERE m.MaDM = d.MaDM
                      AND m.TrangThai = 'DangBan'
                      AND m.HinhAnh IS NOT NULL
                      AND m.HinhAnh <> ''
                    LIMIT 1
                )
            )
        )
        FROM DanhMuc d;
    `);

    return parseJson(raw, []);
}

function getFoods(category) {
    const categoryFilter = category ? `AND MaDM = ${sqlString(category)}` : '';
    const raw = queryMySQL(`
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'maMon', MaMon,
                'tenMon', TenMon,
                'maDM', MaDM,
                'giaBan', GiaBan,
                'hinhAnh', HinhAnh,
                'moTa', MoTa,
                'trangThai', TrangThai
            )
        )
        FROM MonAn
        WHERE TrangThai = 'DangBan'
        ${categoryFilter};
    `);

    return parseJson(raw, []);
}

function sendJson(res, status, data) {
    res.writeHead(status, {
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, OPTIONS',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Content-Type': 'application/json; charset=utf-8',
    });
    res.end(JSON.stringify(data));
}

async function sendStatic(reqUrl, res) {
    const staticFile = staticFiles.get(reqUrl.pathname);
    if (!staticFile) return false;

    const content = await readFile(path.join(homeDir, staticFile.file));
    res.writeHead(200, { 'Content-Type': staticFile.type });
    res.end(content);
    return true;
}

const server = http.createServer(async (req, res) => {
    if (req.method === 'OPTIONS') {
        sendJson(res, 204, {});
        return;
    }

    const reqUrl = new URL(req.url, `http://localhost:${PORT}`);
    const category = reqUrl.searchParams.get('category');

    try {
        if (req.method === 'GET' && await sendStatic(reqUrl, res)) {
            return;
        }

        if (reqUrl.pathname === '/api/categories') {
            sendJson(res, 200, getCategories());
            return;
        }

        if (reqUrl.pathname === '/api/foods') {
            sendJson(res, 200, getFoods(category));
            return;
        }

        if (reqUrl.pathname === '/api/home') {
            sendJson(res, 200, {
                categories: getCategories(),
                foods: getFoods().slice(0, 8),
            });
            return;
        }

        sendJson(res, 404, { error: 'Endpoint khong ton tai' });
    } catch (error) {
        console.error(error);
        sendJson(res, 500, { error: 'Khong the lay du lieu tu co so du lieu' });
    }
});

server.listen(PORT, () => {
    console.log(`Crave Home page: http://localhost:${PORT}`);
    console.log(`API home:       http://localhost:${PORT}/api/home`);
    console.log(`API categories: http://localhost:${PORT}/api/categories`);
    console.log(`API foods:      http://localhost:${PORT}/api/foods`);
});
