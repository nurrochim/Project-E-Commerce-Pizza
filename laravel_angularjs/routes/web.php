<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('index');
});

// send message to firebase
Route::get('/sendMessage', 'SendMessageToFirebase@sendMessage');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');

Route::get('/orderlist', function(){return view('orderList');});
Route::get('/menulist', function(){return view('menuList');});
