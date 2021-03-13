'use strict';

const login_modal = document.querySelector('.login-modal');
const signup_modal = document.querySelector('.signup-modal');
const overlay = document.querySelector('.overlay');
const btnsShowLoginModal = document.querySelector('.show-login-modal');
const btnsShowSignupModal = document.querySelector('.show-signup-modal');
const linkShowLoginModal = document.querySelector('.show-login-modal-link');
const linkShowSignupModal = document.querySelector('.show-signup-modal-link');
const btnCloseLoginModal = document.querySelector('.close-login-modal');
const btnCloseSignupModal = document.querySelector('.close-signup-modal');
const btnCloseUserMessage = document.querySelector('.close-user-message');
const btnCloseUserMessageCart = document.querySelector('.close-user-message-cart');
const btnCloseUserMessageOrders = document.querySelector('.close-user-message-order');
const btnCloseUserMessageCheckout = document.querySelector('.close-user-message-checkout');
const btnCloseUserMessageStatement = document.querySelector('.close-user-message-statement');
const userMessage = document.querySelector('.user-message');
const userMessageCart = document.querySelector('.user-message-cart');
const userMessageOrders = document.querySelector('.user-message-orders');
const userMessageCheckout = document.querySelector('.user-message-checkout');
const userMessageStatement = document.querySelector('.user-message-statement');


if (linkShowLoginModal) {
    linkShowLoginModal.addEventListener('click', function() {
        console.log('Button clicked');
        login_modal.classList.remove('hidden');
        overlay.classList.remove('hidden');
    })
}

if (linkShowSignupModal) {
    linkShowSignupModal.addEventListener('click', function() {
        console.log('Button clicked');
        signup_modal.classList.remove('hidden');
        overlay.classList.remove('hidden');
    })
}

if (btnsShowSignupModal) {
    btnsShowSignupModal.addEventListener('click', function() {
        console.log('Button clicked');
        signup_modal.classList.remove('hidden');
        overlay.classList.remove('hidden');
    })
}

if (btnsShowLoginModal) {
    btnsShowLoginModal.addEventListener('click', function() {
        console.log('Button clicked');
        login_modal.classList.remove('hidden');
        overlay.classList.remove('hidden');
    })
}

if (btnCloseLoginModal) {
    btnCloseLoginModal.addEventListener('click', function() {
        login_modal.classList.add('hidden');
        overlay.classList.add('hidden');
    })
}

if (btnCloseSignupModal) {
    btnCloseSignupModal.addEventListener('click', function() {
        signup_modal.classList.add('hidden');
        overlay.classList.add('hidden');
    })
}

if (btnCloseUserMessage) {
    btnCloseUserMessage.addEventListener('click', function() {
        console.log('Button clicked');
        userMessage.classList.add('hidden');
    })
}

