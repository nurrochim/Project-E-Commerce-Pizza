<?php
namespace App\Http\Controllers;

use League\Fractal\TransformerAbstract;
use App\MenuPizza;

class MenuPizzaTransformer extends TransformerAbstract{
    public function transform(MenuPizza $menuPizza){
        return [
            'id'         => (int) $menuPizza->ID_MENU,
            'menuName'   => $menuPizza->NAME,
            'menuDetail' => $menuPizza->DETAIL,
            'menuHarga'  => $menuPizza->HARGA,
            'menuSize'   => $menuPizza->SIZE,
            'image1'     => $menuPizza->IMAGE_PATH_H,
            'image2'     => $menuPizza->IMAGE_PATH_D
        ];
    }
}

