<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\OrderHeader;
use App\Http\Controllers\Controller;

class OrderHeaderController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index($id = null) {
        if ($id == null) {
            return OrderHeader::orderBy('ID_ORDER', 'asc')->get();
        } else {
            return $this->show($id);
        }
    }
    
    /**
     * Store a newly created resource in storage.
     *
     * @param  Request  $request
     * @return Response
     */
    public function store(Request $request) {
        $orderHeader = new OrderHeader;
        $orderHeader->ID_ORDER = $request->input('id_order');
        $orderHeader->NAME = $request->input('name');
        $orderHeader->EMAIL = $request->input('email');
        $orderHeader->CONTACT_NUMBER = $request->input('contact_number');
        $orderHeader->ADDRESS = $request->input('address');
        $orderHeader->STATUS_ORDER = $request->input('status_order');
        $orderHeader->STATUS_ORDER_DESC = $request->input('status_order_desc');
        $orderHeader->DELIVERY_FEE = $request->input('delivery_fee');
        $orderHeader->SUB_TOTAL = $request->input('sub_total');
        $orderHeader->TOTAL = $request->input('total');
        $orderHeader->TOKEN = $request->input('token');
        $orderHeader->PAYMENT_METHOD = $request->input('paymentMethod');
        $orderHeader->save();

        return 'Order list record successfully created with id ' . $orderHeader->ID_ORDER;
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return Response
     */
    public function show($id) {
        return OrderHeader::find($id);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  Request  $request
     * @param  int  $id
     * @return Response
     */
    public function update(Request $request, $id) {
        $orderHeader = OrderHeader::find($id);

        $orderHeader->NAME = $request->input('name');
        $orderHeader->EMAIL = $request->input('email');
        $orderHeader->CONTACT_NUMBER = $request->input('contact_number');
        $orderHeader->ADDRESS = $request->input('address');
        $orderHeader->STATUS_ORDER = $request->input('status_order');
        $orderHeader->STATUS_ORDER_DESC = $request->input('status_order_desc');
        $orderHeader->DELIVERY_FEE = $request->input('delivery_fee');
        $orderHeader->SUB_TOTAL = $request->input('sub_total');
        $orderHeader->TOTAL = $request->input('total');
        $orderHeader->TOKEN = $request->input('token');
        $orderHeader->save();

        return "Sucess updating order_header #" . $orderHeader->ID_ORDER;
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return Response
     */
    public function destroy(Request $request) {
        $orderHeader = OrderHeader::find($request->id);

        $orderHeader->delete();

        return "Employee record successfully deleted #" . $request->id;
    }
    
    public function updateStatus($id, $status, $statusdesc) {
        $orderHeader = OrderHeader::find($id);
        
        $orderHeader->STATUS_ORDER = $status;
        $orderHeader->STATUS_ORDER_DESC = $statusdesc;
        
        $orderHeader->save();
        return "Sucess updating order_header #" . $orderHeader->ID_ORDER;
    }
}
