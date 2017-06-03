<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class MenuPizza extends Model
{
    protected $fillable = array('ID_MENU', 'NAME', 'DETAIL','HARGA','SIZE', 'IMAGE_PATH_H', 'IMAGE_PATH_D');
    
    protected $table = 'TBL_MENU_PIZZA';
    
    protected $primaryKey = 'ID_MENU';
}
