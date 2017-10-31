<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateOrderHeaderTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up() {
        Schema::create('TBL_ORDER_HEADER', function (Blueprint $table) {
            $table->integer('ID_ORDER');
            $table->string('NAME');
            $table->string('EMAIL');
            $table->string('CONTACT_NUMBER');
            $table->string('ADDRESS');
            $table->string('STATUS_ORDER');
            $table->string('STATUS_ORDER_DESC');
            $table->string('DELIVERY_FEE');
            $table->string('SUB_TOTAL');
            $table->string('TOTAL');
            $table->string('TOKEN');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down() {
        Schema::drop('TBL_ORDER_HEADER');
    }
}
