<?php

    class Product {

        private $db;

        public function __construct(){
            $this->db = new Database;
        }

        public function getAllProducts(){
            $this->db->query('SELECT * FROM new_inventory');
            $results = $this->db->resultSet();
            return $results;
        }

        public function getProductById($id){
            $this->db->query('SELECT * FROM new_inventory WHERE product_id = :id');
            $this->db->bind(':id', $id);
            $row = $this->db->single();
            return $row;
        }

        public function reduceQuantity($id, $quant){
            $this->db->query('UPDATE new_inventory SET quantity = quantity - :quant WHERE product_id = :id;');
            $this->db->bind(':id', $id);
            $this->db->bind(':quant', $quant);
            if ($this->db->execute()){
                return true;
            } else {
                return false;
            };
        }

        public function restoreQuantity($id, $quant){
            $this->db->query('UPDATE new_inventory SET quantity = quantity + :quant WHERE product_id = :id;');
            $this->db->bind(':id', $id);
            $this->db->bind(':quant', $quant);
            if ($this->db->execute()){
                return true;
            } else {
                return false;
            };
        }


    }