<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class OrderDetail extends Model
{
    protected $fillable = array('FID_ORDER',
                                'NAME',
                                'DETAIL',
                                'HARGA',
                                'SIZE',
                                'IMAGE_PATH_H',
                                'IMAGE_PATH_D',
                                'QTY',
                                'SUB_TOTAL',
                                );
    
    protected $table = 'TBL_ORDER_DETAIL';
    protected $primaryKey = 'ID_MENU';
}
