<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class OrderHeader extends Model
{
    protected $fillable = array('ID_ORDER', 
                                'NAME', 
                                'EMAIL', 
                                'CONTACT_NUMBER',
                                'ADDRESS',
                                'STATUS_ORDER',
                                'STATUS_ORDER_DESC',
                                'DELIVERY_FEE',
                                'SUB_TOTAL',
                                'TOTAL',
                                'TOKEN',
                                'PAYMENT_METHOD'
                                );
    
    protected $table = 'TBL_ORDER_HEADER';
    protected $primaryKey = 'ID_ORDER';
}
