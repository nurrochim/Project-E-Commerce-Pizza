<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
use App\MenuPizza;
use Illuminate\Database\Seeder;

class MenuDatabaseSeeder extends Seeder{
    public function run(){
        
        DB::table('TBL_MENU_PIZZA')->truncate();
        MenuPizza::create(array
                    (
                        'NAME' => 'BEEF RENDANG',
                        'DETAIL' => 'Rendang Sauce, Shredded Mozzarella Cheese, Onion, Green Pepper',
                        'HARGA' => '105000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/beef_rendang.png',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/beef_rendang1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'AYAM BAKAR',
                        'DETAIL' => 'Rendang Sauce, Shredded Mozzarella Cheese, Onion, AyamBakar, Dry Parsley Flake',
                        'HARGA' => '105000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/ayam_bakar.png', 
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/ayam_bakar1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'EXTRAVAGANZZA',
                        'DETAIL' => 'Domino\'s Pizza Sauce, Green Peppers, Beef Crumble, Beef Pepperoni, Onions, Mushrooms, Black Olives, Mozzarella Cheese',
                        'HARGA' => '85000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/extravaganza.jpg',                        
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/extravaganza1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'SPICY GARLIC PRAWN',
                        'DETAIL' => 'Rustic Sauce, Shredded Mozzarella Cheese, Prawn, Garlic Bread Seasoning, Onion, Local Tomato, Dry Parsley Flakes',
                        'HARGA' => '105000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/spicy_garlic_prawn.png',                        
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/spicy_garlic_prawn1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'TANDOORI CHICKEN',
                        'DETAIL' => 'Curry Sauce, Onion, Green Pepper, Tandoori Chicken, Rahitta Sauce',
                        'HARGA' => '105000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/tandori_chicken.png',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/tandori_chicken1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'FIRE BREATHER',
                        'DETAIL' => 'Domino\'s Pizza Sauce, Sambal Sauce, Jalapeno, Tomato, Beef Pepperoni, Dry Chili Flakes, Mozzarella Cheese',
                        'HARGA' => '72000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/fire_breather.png',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/fire_breather1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'VEGGIE MANIA',
                        'DETAIL' => 'Domino\'s Pizza Sauce, Onions, Green Peppers, Corns, Mushrooms, Black Olives, Tomato Dice, Dried Parsley, Mozarella Cheese',
                        'HARGA' => '85000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/veggie_mania.jpg',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/veggie_mania1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'VEGGIE SUPREME',
                        'DETAIL' => 'Domino\'s Pizza Sauce, Green Pepper, Red Pepper, Onion, Feta Cheese, Parsley, Mozzarella Cheese',
                        'HARGA' => '72000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/veggie_supreme.png',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/veggie_supreme1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'SAMBAL BEEF',
                        'DETAIL' => 'Pizza Dough, Dom Pizza Sauce, Mozarella Cheese, Beef Topping / Cramble, Sambal Sauce',
                        'HARGA' => '45000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/sambal_beef.jpg',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/sambal_beef1.PNG'
                    )
                );
        MenuPizza::create(array
                    (
                        'NAME' => 'BEEF PEPPERONI FEAST',
                        'DETAIL' => 'Domino\'s Pizza Sauce, Beef Pepperoni, Mozarella Cheese',
                        'HARGA' => '72000',
                        'SIZE' => '10 Inch',
                        'IMAGE_PATH_H' => 'https://evening-scrubland-10355.herokuapp.com/storage/beef_papperoni_feast.jpg',
                        'IMAGE_PATH_D' => 'https://evening-scrubland-10355.herokuapp.com/storage/beef_papperoni_feast1.PNG'
                    )
                );
        
    }    
                
}

