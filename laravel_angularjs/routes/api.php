<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

header('Access-Control-Allow-Origin:  *');
header('Access-Control-Allow-Methods:  POST, GET, OPTIONS, PUT, DELETE');
header('Access-Control-Allow-Headers:  Content-Type, X-Auth-Token, Origin, Authorization');

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::get('/v1/employees/{id?}', 'Employees@index');
Route::post('/v1/employees', 'Employees@store');
Route::post('/v1/employees/{id}', 'Employees@update');
Route::delete('/v1/employees/{id}', 'Employees@destroy');

Route::get('/v1/menu/{id?}', 'MenuPizzas@index');
Route::get('/v1/menuweb/{id?}', 'MenuPizzas@indexmenuweb');

Route::get('/v1/order_header/{id?}', 'OrderHeaderController@index');
Route::post('/v1/order_header', 'OrderHeaderController@store');
Route::post('/v1/order_header/{id}', 'OrderHeaderController@update');
Route::post('/v1/order_header/{id}/{status}/{statusdesc}', 'OrderHeaderController@updateStatus');
Route::delete('/v1/order_header/{id}', 'OrderHeaderController@destroy');

Route::get('/v2/order_detail/{id?}', 'OrderDetailController@index');
Route::get('/v1/order_detail/{id?}', 'OrderDetailController@showOrder');
Route::post('/v1/order_detail', 'OrderDetailController@store');
Route::post('/v1/order_detail/{id}', 'OrderDetailController@update');
Route::delete('/v1/order_detail/{id}', 'OrderDetailController@destroy');

Route::post('v1/sendMessage/{id}/{statusOrder}/{msgBody}/{msgTitle}/{to}', 'SendMessageToFirebase@sendMessageWithParam');
