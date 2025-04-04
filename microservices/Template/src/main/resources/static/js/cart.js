document.addEventListener('DOMContentLoaded', function() {
    // Initialize cart page
    if (document.getElementById('cartItems')) {
        displayCartItems();
        setupEventListeners();
    }
});

function setupEventListeners() {
    // Clear cart button
    const clearCartBtn = document.getElementById('clearCartBtn');
    if (clearCartBtn) {
        clearCartBtn.addEventListener('click', clearCart);
    }

    // Checkout button
    const checkoutBtn = document.getElementById('checkoutBtn');
    if (checkoutBtn) {
        checkoutBtn.addEventListener('click', proceedToCheckout);
    }

    // Event delegation for quantity changes and remove buttons
    const cartItems = document.getElementById('cartItems');
    if (cartItems) {
        cartItems.addEventListener('click', function(e) {
            if (e.target.classList.contains('btn-remove-item')) {
                const productId = e.target.dataset.id;
                removeFromCart(productId);
            }
        });

        cartItems.addEventListener('change', function(e) {
            if (e.target.classList.contains('quantity-input')) {
                const productId = e.target.dataset.id;
                const newQuantity = parseInt(e.target.value);
                if (newQuantity > 0) {
                    updateCartItemQuantity(productId, newQuantity);
                } else {
                    e.target.value = 1;
                    updateCartItemQuantity(productId, 1);
                }
            }
        });
    }
}

function displayCartItems() {
    const cartItems = document.getElementById('cartItems');
    const cartContainer = document.getElementById('cartContainer');
    const cartSummary = document.getElementById('cartSummary');
    const emptyCartMessage = document.getElementById('emptyCartMessage');
    const cartTotal = document.getElementById('cartTotal');

    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    if (cart.length === 0) {
        cartContainer.style.display = 'none';
        cartSummary.style.display = 'none';
        emptyCartMessage.style.display = 'block';
        return;
    }

    cartContainer.style.display = 'block';
    cartSummary.style.display = 'block';
    emptyCartMessage.style.display = 'none';

    let total = 0;
    cartItems.innerHTML = cart.map(item => {
        const itemTotal = item.price * item.quantity;
        total += itemTotal;
        return `
            <tr>
                <td class="product-thumbnail">
                    <img src="${item.image}" alt="${item.name}" class="img-fluid">
                </td>
                <td class="product-name">
                    <h2 class="h5 text-black">${item.name}</h2>
                </td>
                <td class="product-price">$${parseFloat(item.price).toFixed(2)}</td>
                <td class="product-quantity">
                    <input type="number" class="form-control quantity-input" 
                           value="${item.quantity}" min="1" data-id="${item.id}">
                </td>
                <td class="product-total">$${itemTotal.toFixed(2)}</td>
                <td class="product-remove">
                    <button class="btn btn-remove-item" data-id="${item.id}">×</button>
                </td>
            </tr>
        `;
    }).join('');

    if (cartTotal) {
        cartTotal.textContent = `$${total.toFixed(2)}`;
    }
}

function removeFromCart(productId) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart = cart.filter(item => item.id !== productId);
    localStorage.setItem('cart', JSON.stringify(cart));
    
    displayCartItems();
    updateCartCount();
    showNotification('Product removed from cart');
}

function updateCartItemQuantity(productId, newQuantity) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    const item = cart.find(item => item.id === productId);
    
    if (item) {
        item.quantity = newQuantity;
        localStorage.setItem('cart', JSON.stringify(cart));
        displayCartItems();
        updateCartCount();
    }
}

function clearCart() {
    if (confirm('Are you sure you want to clear your cart?')) {
        localStorage.removeItem('cart');
        displayCartItems();
        updateCartCount();
        showNotification('Cart has been cleared');
    }
}

function proceedToCheckout() {
    // Implement checkout logic here
    alert('Proceeding to checkout...');
}

function updateCartCount() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    const cartBadge = document.querySelector('.cart-badge');
    if (cartBadge) {
        cartBadge.textContent = totalItems;
        cartBadge.style.display = totalItems > 0 ? 'inline' : 'none';
    }
}

function showNotification(message) {
    const notification = document.createElement('div');
    notification.className = 'alert alert-success position-fixed';
    notification.style.top = '20px';
    notification.style.right = '20px';
    notification.style.zIndex = '1000';
    notification.textContent = message;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.remove();
    }, 3000);
} 