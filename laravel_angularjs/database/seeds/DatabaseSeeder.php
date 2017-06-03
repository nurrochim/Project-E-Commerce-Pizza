<?php

use Illuminate\Database\Seeder;
use MenuDatabaseSeeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        Eloquent::unguard();
        $this->call('MenuDatabaseSeeder');
    }
}
