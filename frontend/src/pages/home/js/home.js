const API_BASE_URL = window.CRAVE_HOME_API_BASE_URL || 'http://localhost:8080/api';

const heroImg = document.getElementById('hero-img');
const categoryGrid = document.getElementById('category-grid');
const popularGrid = document.getElementById('popular-grid');

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN').format(Number(amount) || 0) + 'đ';
}

function escapeHtml(value = '') {
    return String(value)
        .replaceAll('&', '&amp;')
        .replaceAll('<', '&lt;')
        .replaceAll('>', '&gt;')
        .replaceAll('"', '&quot;')
        .replaceAll("'", '&#039;');
}

function imageOf(item) {
    return item?.hinhAnh || item?.HinhAnh || '';
}

function setMessage(container, message) {
    if (!container) return;

    container.innerHTML = `
        <p class="home-state">${escapeHtml(message)}</p>
    `;
}

async function fetchJson(path) {
    const response = await fetch(`${API_BASE_URL}${path}`);

    if (!response.ok) {
        throw new Error(`HTTP ${response.status}`);
    }

    return response.json();
}

function renderHero(foods = []) {
    if (!heroImg) return;

    const firstFoodWithImage = foods.find((food) => imageOf(food));
    if (!firstFoodWithImage) {
        heroImg.removeAttribute('src');
        return;
    }

    heroImg.src = imageOf(firstFoodWithImage);
    heroImg.alt = firstFoodWithImage.tenMon || firstFoodWithImage.TenMon || 'Món ngon Crave';
}

function renderCategories(categories = []) {
    if (!categoryGrid) return;

    if (categories.length === 0) {
        setMessage(categoryGrid, 'Chưa có danh mục nào.');
        return;
    }

    categoryGrid.innerHTML = categories.map((category) => {
        const maDM = category.maDM || category.MaDM || '';
        const tenDM = category.tenDM || category.TenDM || 'Danh mục';
        const hinhAnh = imageOf(category);

        return `
            <article class="category-card" data-category="${escapeHtml(maDM)}">
                ${hinhAnh ? `<img src="${escapeHtml(hinhAnh)}" alt="${escapeHtml(tenDM)}" loading="lazy" />` : ''}
                <h3>${escapeHtml(tenDM)}</h3>
            </article>
        `;
    }).join('');

    categoryGrid.querySelectorAll('.category-card').forEach((card) => {
        card.addEventListener('click', () => {
            const category = card.getAttribute('data-category');
            loadFoods(category);
        });
    });
}

function renderFoods(foods = []) {
    if (!popularGrid) return;

    if (foods.length === 0) {
        setMessage(popularGrid, 'Chưa có món ăn nào.');
        return;
    }

    popularGrid.innerHTML = foods.map((food) => {
        const maMon = food.maMon || food.MaMon || '';
        const tenMon = food.tenMon || food.TenMon || 'Món ăn';
        const moTa = food.moTa || food.MoTa || '';
        const giaBan = food.giaBan || food.GiaBan || 0;
        const hinhAnh = imageOf(food);

        return `
            <article class="food-card" data-id="${escapeHtml(maMon)}">
                ${hinhAnh ? `<img src="${escapeHtml(hinhAnh)}" alt="${escapeHtml(tenMon)}" loading="lazy" />` : ''}
                <div class="food-card-body">
                    <h3>${escapeHtml(tenMon)}</h3>
                    <p>${escapeHtml(moTa)}</p>
                    <div class="food-card-footer">
                        <strong>${formatCurrency(giaBan)}</strong>
                        <button type="button" aria-label="Lưu ${escapeHtml(tenMon)}">
                            <span class="save-icon" aria-hidden="true"></span>
                        </button>
                    </div>
                </div>
            </article>
        `;
    }).join('');

    popularGrid.querySelectorAll('.food-card button').forEach((button) => {
        button.addEventListener('click', (event) => {
            event.stopPropagation();
            button.classList.toggle('active');
        });
    });
}

async function loadHome() {
    setMessage(categoryGrid, 'Đang tải danh mục...');
    setMessage(popularGrid, 'Đang tải món ăn...');

    try {
        const data = await fetchJson('/home');
        const categories = data.categories || [];
        const foods = data.foods || [];

        renderHero(foods);
        renderCategories(categories);
        renderFoods(foods);
    } catch (error) {
        console.error('Không thể tải dữ liệu trang chủ:', error);
        setMessage(categoryGrid, 'Không thể tải danh mục từ cơ sở dữ liệu.');
        setMessage(popularGrid, 'Không thể tải món ăn từ cơ sở dữ liệu.');
    }
}

async function loadFoods(category) {
    setMessage(popularGrid, 'Đang tải món ăn...');

    try {
        const path = category ? `/foods?category=${encodeURIComponent(category)}` : '/foods';
        renderFoods(await fetchJson(path));
    } catch (error) {
        console.error('Không thể tải món ăn:', error);
        setMessage(popularGrid, 'Không thể tải món ăn từ cơ sở dữ liệu.');
    }
}

document.addEventListener('DOMContentLoaded', loadHome);
