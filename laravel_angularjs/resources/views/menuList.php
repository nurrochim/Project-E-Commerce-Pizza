<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="en-US" ng-app="employeeRecords">

    <head>
        <!-- Charset -->
        <meta charset="utf-8">

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <!-- IE Force -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <link href="css/style.css" rel="stylesheet">

        <link data-require="bootstrap@3.3.6" data-semver="3.3.6" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
        <link data-require="bootstrap@3.3.6" data-semver="3.3.6" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.6/cosmo/bootstrap.min.css" />
        <link data-require="bootstrap@3.3.6" data-semver="3.3.6" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="style.css">

        <link rel="stylesheet" href="//cdn.jsdelivr.net/bootstrap.block-grid/latest/bootstrap3-block-grid.min.css" />

        <script data-require="jquery@2.1.4" data-semver="2.1.4" src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script data-require="bootstrap@3.3.6" data-semver="3.3.6" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


        <style>
            .product-image {
                height: 120px;
            }

            .product-image img {
                max-height: 100%;
            }
        </style>
    </head>

    <body>
        <header>
            <div class="container">
                <div id="branding">
                    <img src="http://evening-scrubland-10355.herokuapp.com/storage/pizza_icon.png" style="width: 80px">
                </div>
                <div id="name">
                    E-Commerce Pizza
                </div>
            </div>
        </header>
        <div class="menu">
            <a href="index.php">User List</a>
            <a class="menucurrent" href="menulist">Menu List</a>
            <a href="orderlist">Order List</a>
        </div>
        <div class="container text-center" ng-controller="menuController">

            <h1 style="border-bottom:#e8491d 3px solid;margin-bottom: 30px; font-weight: bold;">Menu List</h1>

            <ul class="block-grid-xs-1 block-grid-sm-3 block-grid-md-4">
                <li ng-repeat="menu_item in menulist">

                    <a>
                        <div class="product-image">
                            <img src="http://evening-scrubland-10355.herokuapp.com/storage/{{ menu_item.IMAGE_PATH_D}}">
                        </div>

                        <div class="product-text">
                            <h3><strong> {{ menu_item.NAME}} </strong> </h3>
                            <p>{{ menu_item.DETAIL}}</p>
                            <p><i class="fi-star"></i><i class="fi-star"></i><i class="fi-star"></i><i class="fi-star"></i> <strong>Size {{menu_item.SIZE}} </strong></p>
                            <p><strong>Rp. {{ menu_item.HARGA}}</strong></p>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <footer class="container-fluid bg-4 text-center">
            <p>Nurochim (nurochim@ymail.com)</p>
            <p>Copyright © 2017</p>

        </footer>
        <!-- AngularJS Application Scripts -->
        <script src="app/lib/angular/angular.min.js"></script>
        <script src="app/app.js"></script>
        <script src="app/controllers/menulist.js"></script>
    </body>

</html>