<?php

    class Admins extends Controller {

        public function __construct(){
            if(!isLoggedIn()){
                redirect('users/login');
            }

            if(!$_SESSION['ADMIN']){
                redirect('products/index');
            }

            $this->productModel = $this->model('Product');
            $this->userModel = $this->model('User');
            $this->orderModel = $this->model('Order');
        }

        public function index(){
            $user_id = $_SESSION['user_id'];
            $data = array();
            
            $this->view('admin/index', $data);
        }
    }