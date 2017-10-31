<!DOCTYPE html>
<html lang="en-US" ng-app="employeeRecords">
    <head>
        <title>E-Commerce Pizza</title>

        <!-- Load Bootstrap CSS -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
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
            <a href="menulist">Menu List</a>
            <a class="menucurrent" href="orderlist">Order List</a>
        </div>
        <div class="container">

            <h1 style="border-bottom:#e8491d 3px solid;margin-bottom: 30px;text-align: center;">Order List</h1>
            <div  ng-controller="orderController">

                <!-- Table-to-load-the-data Part -->
                <table class="table">
                    <thead>
                        <tr>
                            <th>Order Info</th>
                            <th>Customer Name</th>
                            <th>Contact Info</th>
                            <th>Address</th>
                            <th>Status Order</th>
                            <th>Detail Order</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="order_header in order_headers">
                            <td>Order Id : {{ order_header.ID_ORDER}}
                                </br>
                                Tanggal Order : {{ order_header.DELIVERY_FEE}}
                                </br>
                                Payment Method : {{ order_header.PAYMENT_METHOD}}
                            </td>
                            <td>{{ order_header.NAME}}</td>
                            <td>Email : {{ order_header.EMAIL}}
                                </br>
                                Contact No : {{ order_header.CONTACT_NUMBER}}
                            </td>
                            <td>{{ order_header.ADDRESS}}</td>
                            <td>{{ order_header.STATUS_ORDER_DESC}}</td>
                            <td>
                                <button class="btn btn-primary btn-xs btn-detail" ng-click="toggle('view_detail', order_header.ID_ORDER, order_header.SUB_TOTAL, order_header.TOTAL, order_header.TOKEN)">View Detail Order</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <!-- End of Table-to-load-the-data Part -->
                <!-- Modal (Pop up when detail button clicked) -->
                <div class="modal fade" id="myModal" tabindex="-1" 
                     role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"

                     >
                    <div class="modal-dialog" style="width: 850px">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" 
                                        aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">{{form_title}}</h4>
                            </div>
                            <div class="modal-body">
                                <form name="frmOrderDetail" class="form-horizontal" novalidate="">
                                    <!--Table Detail Order-->  
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Menu Name</th>
                                                <th>Ingredient</th>
                                                <th>Price Info</th>
                                                <th>Sub Total</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr ng-repeat="order_detail in order_details">
                                                <td>{{ order_detail.NAME}}</td>
                                                <td style="width: 200px">{{ order_detail.DETAIL}}</td>
                                                <td>Price = {{ order_detail.HARGA}}
                                                    </br>
                                                    Size = {{ order_detail.SIZE}}
                                                    </br>
                                                    Quantity = {{ order_detail.QTY}}
                                                </td>
                                                <td>{{ order_detail.SUB_TOTAL}}</td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                            <div class="modal-footer">
                                Total Belanja = Rp. {{form_sub_total}}
                                </br>
                                Delivery Fee = Rp. 15.0000
                                </br>
                                Total Bayar = Rp. {{form_total}}
                                </br>
                                </br>
                                <button type="button" class="btn btn-primary" id="btn-confirm" ng-click="updateStatusOrder('ContohId', '2', 'Your Order Confirmed', 'Confirmed')" ng-disabled="frmOrderDetail.$invalid">Confirm Order</button>
                                <button type="button" class="btn btn-primary" id="btn-process" ng-click="updateStatusOrder('ContohId', '3', 'Your Order On Process', 'On Process')" ng-disabled="frmOrderDetail.$invalid">Process</button>
                                <button type="button" class="btn btn-primary" id="btn-delivery" ng-click="updateStatusOrder('ContohId', '4', 'Your Order On Delivery', 'On Delivery')" ng-disabled="frmOrderDetail.$invalid">Delivery</button>
                                <button type="button" class="btn btn-primary" id="btn-finish" ng-click="updateStatusOrder('ContohId', '5', 'Your Order already finish', 'Finish Order')" ng-disabled="frmOrderDetail.$invalid">Finish</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <footer class="container-fluid bg-4 text-center">
            <p>Nurochim (nurochim@ymail.com)</p>
            <p>Copyright © 2017</p>

        </footer>

        <!-- Load Javascript Libraries (AngularJS, JQuery, Bootstrap) -->
        <script src="app/lib/angular/angular.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>

        <!-- AngularJS Application Scripts -->
        <script src="app/app.js"></script>
        <script src="app/controllers/orderlist.js"></script>
    </body>
</html>