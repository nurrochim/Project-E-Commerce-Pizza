<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;
use App\OrderDetail;
use App\Http\Controllers\Controller;

class OrderDetailController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return Response
     */
    public function index($id = null) {
        if ($id == null) {
            return OrderDetail::orderBy('ID_MENU', 'asc')->get();
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
        $orderDetail = new OrderDetail;

        $orderDetail->FID_ORDER = $request->input('fid_order');
        $orderDetail->NAME = $request->input('name_menu');
        $orderDetail->DETAIL = $request->input('detail');
        $orderDetail->HARGA = $request->input('harga');
        $orderDetail->SIZE = $request->input('size');
        $orderDetail->IMAGE_PATH_H = $request->input('image_path_h');
        $orderDetail->IMAGE_PATH_D = $request->input('image_path_d');
        $orderDetail->QTY = $request->input('qty');
        $orderDetail->SUB_TOTAL = $request->input('sub_total');
        $orderDetail->save();

        return 'OrderDetail record successfully created with id ' . $orderDetail->ID_MENU;
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return Response
     */
    public function show($id) {
        return OrderDetail::find($id);
    }
    
    public function showOrder($id) {
        return OrderDetail::where('FID_ORDER', $id)->orderBy('ID_MENU', 'asc')->get();
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  Request  $request
     * @param  int  $id
     * @return Response
     */
    public function update(Request $request, $id) {
        $orderDetail = OrderDetail::find($id);

        $orderDetail->FID_ORDER = $request->input('fid_order');
        $orderDetail->NAME = $request->input('name_menu');
        $orderDetail->DETAIL = $request->input('detail');
        $orderDetail->HARGA = $request->input('harga');
        $orderDetail->SIZE = $request->input('size');
        $orderDetail->IMAGE_PATH_H = $request->input('image_path_h');
        $orderDetail->IMAGE_PATH_D = $request->input('image_path_d');
        $orderDetail->QTY = $request->input('qty');
        $orderDetail->SUB_TOTAL = $request->input('sub_total');
        $orderDetail->save();

        return "Sucess updating user #" . $orderDetail->ID_MENU;
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return Response
     */
    public function destroy(Request $request) {
        $employee = OrderDetail::find($request->id);

        $employee->delete();

        return "OrderDetail record successfully deleted #" . $request->id;
    }
}
