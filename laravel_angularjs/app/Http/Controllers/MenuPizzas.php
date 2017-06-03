<?php

namespace App\Http\Controllers;

use App\MenuPizza;
use App\Http\Controllers\Controller;
use League\Fractal\Resource\Collection;
use League\Fractal\Resource\Item;
use League\Fractal\Manager;

class MenuPizzas extends Controller
{
    
    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index($id = null) {
        if ($id == null) {
            $manager = new Manager();
            $menuPizzas = MenuPizza::orderBy('ID_MENU', 'asc')->get();
            $menuPizzaTransform = new Collection($menuPizzas, new MenuPizzaTransformer());
            $menuJson = $manager ->createData($menuPizzaTransform)->toArray();
            return \Response::json($menuJson, 200) ;
        } else {
            return $this->show($id);
        }
    }
    
    public function show($id) {
        $manager = new Manager();
        $menu = MenuPizza::find($id);
        $menuPizzaTransform = new Item($menu, new MenuPizzaTransformer());
        $menuJson = $manager ->createData($menuPizzaTransform)->toArray();
        return \Response::json($menuJson, 200) ;
    }
}
