'use strict';

const login_modal = document.querySelector('.login-modal');
const signup_modal = document.querySelector('.signup-modal');
const overlay = document.querySelector('.overlay');
const btnsShowLoginModal = document.querySelector('.show-login-modal');
const btnsShowSignupModal = document.querySelector('.show-signup-modal');
const linkShowLoginModal = document.querySelector('.show-login-modal-link');
const btnCloseLoginModal = document.querySelector('.close-login-modal');
const btnCloseSignupModal = document.querySelector('.close-signup-modal');
const btnCloseUserMessageProducts = document.querySelector('.close-user-message-products');
const userMessageProducts = document.querySelector('.user-message-products');

if (linkShowLoginModal) {
    linkShowLoginModal.addEventListener('click', function() {
        console.log('Button clicked');
        login_modal.classList.remove('hidden');
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

if (btnCloseUserMessageProducts) {
    btnCloseUserMessageProducts.addEventListener('click', function() {
        console.log('Button clicked');
        userMessageProducts.classList.add('hidden');
    })
}
