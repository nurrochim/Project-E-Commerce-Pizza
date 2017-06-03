<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreatePizzaMenuTabel extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::create('TBL_MENU_PIZZA', function (Blueprint $table) {
            $table->increments('ID_MENU');
            $table->string('NAME');
            $table->string('DETAIL');
            $table->string('HARGA');
            $table->string('SIZE');
            $table->string('IMAGE_PATH_H');
            $table->string('IMAGE_PATH_D');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::drop('TBL_MENU_PIZZA');
    }

}
