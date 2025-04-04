(function() {
	'use strict';

	var tinyslider = function() {
		var el = document.querySelectorAll('.testimonial-slider');

		if (el.length > 0) {
			var slider = tns({
				container: '.testimonial-slider',
				items: 1,
				axis: "horizontal",
				controlsContainer: "#testimonial-nav",
				swipeAngle: false,
				speed: 700,
				nav: true,
				controls: true,
				autoplay: true,
				autoplayHoverPause: true,
				autoplayTimeout: 3500,
				autoplayButtonOutput: false
			});
		}
	};
	tinyslider();

	


	var sitePlusMinus = function() {

		var value,
    		quantity = document.getElementsByClassName('quantity-container');

		function createBindings(quantityContainer) {
	      var quantityAmount = quantityContainer.getElementsByClassName('quantity-amount')[0];
	      var increase = quantityContainer.getElementsByClassName('increase')[0];
	      var decrease = quantityContainer.getElementsByClassName('decrease')[0];
	      increase.addEventListener('click', function (e) { increaseValue(e, quantityAmount); });
	      decrease.addEventListener('click', function (e) { decreaseValue(e, quantityAmount); });
	    }

	    function init() {
	        for (var i = 0; i < quantity.length; i++ ) {
						createBindings(quantity[i]);
	        }
	    };

	    function increaseValue(event, quantityAmount) {
	        value = parseInt(quantityAmount.value, 10);

	        console.log(quantityAmount, quantityAmount.value);

	        value = isNaN(value) ? 0 : value;
	        value++;
	        quantityAmount.value = value;
	    }

	    function decreaseValue(event, quantityAmount) {
	        value = parseInt(quantityAmount.value, 10);

	        value = isNaN(value) ? 0 : value;
	        if (value > 0) value--;

	        quantityAmount.value = value;
	    }
	    
	    init();
		
	};
	sitePlusMinus();

	// Sticky Header
	window.addEventListener('scroll', function() {
		const header = document.querySelector('.site-header');
		if (window.scrollY > 50) {
			header.classList.add('scrolled');
		} else {
			header.classList.remove('scrolled');
		}
	});

	// Cart functionality
	document.addEventListener('DOMContentLoaded', function() {
		updateCartCount();
	});

	function addToCart(product) {
		let cart = JSON.parse(localStorage.getItem('cart')) || [];
		const existingProduct = cart.find(item => item.id === product.id);
		
		if (existingProduct) {
			existingProduct.quantity += product.quantity;
		} else {
			cart.push(product);
		}
		
		localStorage.setItem('cart', JSON.stringify(cart));
		updateCartCount();
		showNotification('Product added to cart successfully!');
	}

	function updateCartCount() {
		const cart = JSON.parse(localStorage.getItem('cart')) || [];
		const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
		
		const cartBadge = document.querySelector('.cart-badge');
		if (cartBadge) {
			cartBadge.textContent = totalItems;
			cartBadge.style.display = totalItems > 0 ? 'flex' : 'none';
		}
	}

	function showNotification(message) {
		const notification = document.createElement('div');
		notification.className = 'notification';
		notification.textContent = message;
		
		document.body.appendChild(notification);
		
		// Trigger reflow
		notification.offsetHeight;
		
		// Add show class for animation
		notification.classList.add('show');
		
		setTimeout(() => {
			notification.classList.remove('show');
			setTimeout(() => {
				notification.remove();
			}, 300);
		}, 3000);
	}

	// Shop Page Functionality
	const shopFunctions = {
		init() {
			if (document.querySelector('.product-section')) {
				this.setupEventListeners();
				this.setupFilters();
				updateCartCount();
			}
		},

		setupEventListeners() {
			// Add to Cart buttons
			document.querySelectorAll('.btn-add-to-cart').forEach(button => {
				button.addEventListener('click', (e) => {
					e.preventDefault();
					const product = {
						id: button.dataset.id,
						name: button.dataset.name,
						price: button.dataset.price,
						image: button.closest('.product-item').querySelector('.product-thumbnail img').src,
						quantity: 1
					};
					this.addToCart(product);
				});
			});

			// Quick View buttons
			document.querySelectorAll('.btn-quick-view').forEach(button => {
				button.addEventListener('click', (e) => {
					const product = {
						id: button.dataset.id,
						name: button.dataset.name,
						price: button.dataset.price,
						description: button.dataset.description,
						image: button.closest('.product-item').querySelector('.product-thumbnail img').src
					};
					this.showQuickView(product);
				});
			});

			// Quick View Modal Add to Cart
			const quickViewAddToCart = document.getElementById('quickViewAddToCart');
			if (quickViewAddToCart) {
				quickViewAddToCart.addEventListener('click', () => {
					const quantity = parseInt(document.getElementById('quickViewQuantity').value) || 1;
					const product = {
						id: quickViewAddToCart.dataset.id,
						name: document.getElementById('quickViewName').textContent,
						price: document.getElementById('quickViewPrice').textContent.replace('$', ''),
						image: document.getElementById('quickViewImage').src,
						quantity: quantity
					};
					this.addToCart(product);
					bootstrap.Modal.getInstance(document.getElementById('quickViewModal')).hide();
				});
			}
		},

		setupFilters() {
			const categoryFilter = document.getElementById('categoryFilter');
			const sortByFilter = document.getElementById('sortBy');
			const searchInput = document.getElementById('searchProducts');

			const filterProducts = () => {
				const category = categoryFilter.value.toLowerCase();
				const searchTerm = searchInput.value.toLowerCase();

				document.querySelectorAll('.product-item').forEach(product => {
					const name = product.querySelector('.product-title').textContent.toLowerCase();
					const productCategory = product.dataset.category.toLowerCase();
					const matchesCategory = !category || productCategory === category;
					const matchesSearch = !searchTerm || name.includes(searchTerm);
					
					product.closest('.col-12').style.display = 
						matchesCategory && matchesSearch ? 'block' : 'none';
				});
			};

			[categoryFilter, sortByFilter, searchInput].forEach(element => {
				if (element) {
					element.addEventListener(element.tagName === 'INPUT' ? 'input' : 'change', filterProducts);
				}
			});
		},

		showQuickView(product) {
			const elements = {
				name: document.getElementById('quickViewName'),
				price: document.getElementById('quickViewPrice'),
				description: document.getElementById('quickViewDescription'),
				image: document.getElementById('quickViewImage'),
				addToCartBtn: document.getElementById('quickViewAddToCart')
			};

			if (!elements.name || !elements.price || !elements.description || !elements.image || !elements.addToCartBtn) return;

			elements.name.textContent = product.name;
			elements.price.textContent = `$${product.price}`;
			elements.description.textContent = product.description;
			elements.image.src = product.image;
			
			// Update add to cart button data
			Object.keys(product).forEach(key => {
				if (key !== 'quantity') {
					elements.addToCartBtn.dataset[key] = product[key];
				}
			});
		},

		addToCart(product) {
			let cart = JSON.parse(localStorage.getItem('cart')) || [];
			const existingProduct = cart.find(item => item.id === product.id);
			
			if (existingProduct) {
				existingProduct.quantity += product.quantity;
			} else {
				cart.push(product);
			}
			
			localStorage.setItem('cart', JSON.stringify(cart));
			updateCartCount();
			showNotification('Product added to cart successfully!');
		}
	};

	// Cart functionality
	const cartFunctions = {
		init() {
			if (document.getElementById('cartItems')) {
				this.displayCartItems();
				this.setupEventListeners();
			}
		},

		setupEventListeners() {
			const cartItems = document.getElementById('cartItems');
			if (!cartItems) return;

			// Event delegation for quantity changes and remove buttons
			cartItems.addEventListener('click', (e) => {
				if (e.target.classList.contains('remove-item') || e.target.closest('.remove-item')) {
					const button = e.target.classList.contains('remove-item') ? e.target : e.target.closest('.remove-item');
					const productId = button.dataset.id;
					this.removeFromCart(productId);
				}
			});

			cartItems.addEventListener('change', (e) => {
				if (e.target.classList.contains('item-quantity')) {
					const productId = e.target.dataset.id;
					const newQuantity = parseInt(e.target.value);
					if (newQuantity > 0) {
						this.updateCartItemQuantity(productId, newQuantity);
					} else {
						e.target.value = 1;
						this.updateCartItemQuantity(productId, 1);
					}
				}
			});

			// Clear cart button
			const clearCartBtn = document.querySelector('.btn-outline-danger');
			if (clearCartBtn) {
				clearCartBtn.addEventListener('click', (e) => {
					e.preventDefault();
					this.clearCart();
				});
			}
		},

		displayCartItems() {
			const cart = JSON.parse(localStorage.getItem('cart')) || [];
			const elements = {
				container: document.getElementById('cartContainer'),
				items: document.getElementById('cartItems'),
				summary: document.getElementById('cartSummary'),
				empty: document.getElementById('emptyCartMessage'),
				total: document.getElementById('cartTotal')
			};

			if (!elements.container || !elements.items || !elements.summary || !elements.empty) return;

			if (cart.length === 0) {
				elements.container.style.display = 'none';
				elements.summary.style.display = 'none';
				elements.empty.style.display = 'block';
				return;
			}

			elements.container.style.display = 'block';
			elements.summary.style.display = 'block';
			elements.empty.style.display = 'none';

			let total = 0;
			elements.items.innerHTML = cart.map(item => {
				const itemTotal = item.price * item.quantity;
				total += itemTotal;
				return `
					<tr>
						<td class="product-thumbnail">
							<img src="${item.image}" alt="${item.name}" class="cart-item-image">
						</td>
						<td class="product-name">
							<h2 class="h5 text-black">${item.name}</h2>
						</td>
						<td class="product-price">$${parseFloat(item.price).toFixed(2)}</td>
						<td class="product-quantity">
							<div class="quantity-container">
								<input type="number" class="form-control text-center item-quantity" 
									   value="${item.quantity}" min="1" data-id="${item.id}">
							</div>
						</td>
						<td class="product-total">$${itemTotal.toFixed(2)}</td>
						<td class="product-remove">
							<button class="btn btn-danger btn-sm remove-item" data-id="${item.id}">
								<i class="fas fa-times"></i>
							</button>
						</td>
					</tr>
				`;
			}).join('');

			if (elements.total) {
				elements.total.textContent = `$${total.toFixed(2)}`;
			}
		},

		removeFromCart(productId) {
			let cart = JSON.parse(localStorage.getItem('cart')) || [];
			cart = cart.filter(item => item.id !== productId);
			localStorage.setItem('cart', JSON.stringify(cart));
			
			this.displayCartItems();
			updateCartCount();
			showNotification('Product removed from cart');
		},

		updateCartItemQuantity(productId, newQuantity) {
			let cart = JSON.parse(localStorage.getItem('cart')) || [];
			const item = cart.find(item => item.id === productId);
			
			if (item) {
				item.quantity = newQuantity;
				localStorage.setItem('cart', JSON.stringify(cart));
				this.displayCartItems();
				updateCartCount();
			}
		},

		clearCart() {
			if (confirm('Are you sure you want to clear your cart?')) {
				localStorage.removeItem('cart');
				this.displayCartItems();
				updateCartCount();
				showNotification('Cart has been cleared');
			}
		},

		proceedToCheckout() {
			alert('Proceeding to checkout...');
		}
	};

	// Initialize all functionality when DOM is loaded
	document.addEventListener('DOMContentLoaded', () => {
		shopFunctions.init();
		cartFunctions.init();
		updateCartCount();
	});

})();