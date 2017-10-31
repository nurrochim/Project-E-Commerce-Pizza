<?php
namespace App\Http\Controllers;

use App\Http\Controllers\Controller;

class SendMessageToFirebase extends Controller
{
	public function sendMessage(){
		// Your ID and token
		$blogID = '8070105920543249955';
		$authToken = 'key=AAAARyxq5uM:APA91bHQgtxYb8AHGKQ7LkTCGRPu3jLJTG8t0Lw-jfZTjfSyKYpR3DETDw4udYjML85dWFufEJUkZqBRIYvtmJp8nE5NkRjTC3zgQP0gDO2lVPGvIqohKX7N02sLw5BYNINlTEAaKZbj';

		// The data to send to the API
		$postData = array(
			'to' => 'drvpEwnEktU:APA91bFTkaroCiqf48wPOEPG2NrxJk68nL2cXnyxPJ36g64QGDsWumwaK7aO9794T5B5PtbzClJXGE92L-MR7ArYIYsx-Gr1-3xPMN3edHi8MdSUJwu6EBFgEpn0Bnz_tMZznohLFzPB',//'esMbtEAh0zQ:APA91bEVvZG1qZ4SvosqZmXnKlVxAZrjrWHr6Cj-AQLxnkNwS3g-eWyhy9pnOvO6xLP8Pc7YbLIG-Q_DZgPa1hOqEp1sfHnQhupLRJJr6sE4yemuLjupriou-Msu7RlJpe-4_hwb6PrP', 
			'priority' => 'normal',
			'time_to_live'=> 108,
			'delay_while_idle' => true,
			'notification'=> array(         'click_action' => '.view.main.MainActivity',
							'body' => 'Coba Jasa Service From PHP Test7',
							'title'=> 'Coba Jasa PHP Test7',
							'text'=> 'Ada Service Bro dari PHP'
							),
                        'data'=> array( 'status_order' => 1
                    )
		);

		// Setup cURL
		$ch = curl_init('https://gcm-http.googleapis.com/gcm/send');
		curl_setopt_array($ch, array(
			CURLOPT_POST => TRUE,
			CURLOPT_RETURNTRANSFER => TRUE,
			CURLOPT_HTTPHEADER => array(
				'Authorization: '.$authToken,
				'Content-Type: application/json'
			),
			CURLOPT_POSTFIELDS => json_encode($postData)
		));

		// Send the request
		$response = curl_exec($ch);

		// Check for errors
		if($response === FALSE){
			die(curl_error($ch));
		}

		// Decode the response
		$responseData = json_decode($response, TRUE);

		// Print the date from the response
		//echo $responseData['published'];
		echo "Sukses";
	}
        
        public function sendMessageWithParam($idOrder, $statusOrder, $msgBody, $msgTitle, $to){
		// Your ID and token
		$authToken = 'key=AAAARyxq5uM:APA91bHQgtxYb8AHGKQ7LkTCGRPu3jLJTG8t0Lw-jfZTjfSyKYpR3DETDw4udYjML85dWFufEJUkZqBRIYvtmJp8nE5NkRjTC3zgQP0gDO2lVPGvIqohKX7N02sLw5BYNINlTEAaKZbj';

		// The data to send to the API
		$postData = array(
			'to' => $to, 
			'priority' => 'normal',
			'time_to_live'=> 108,
			'delay_while_idle' => true,
			'notification'=> array(         'click_action' => '.view.main.MainActivity',
							'body' => $msgBody,
							'title'=> $msgTitle,
							),
                        'data'=> array( 'status_order' => $statusOrder, 'id_order' =>  $idOrder)
		);

		// Setup cURL
		$ch = curl_init('https://gcm-http.googleapis.com/gcm/send');
		curl_setopt_array($ch, array(
			CURLOPT_POST => TRUE,
			CURLOPT_RETURNTRANSFER => TRUE,
			CURLOPT_HTTPHEADER => array(
				'Authorization: '.$authToken,
				'Content-Type: application/json'
			),
			CURLOPT_POSTFIELDS => json_encode($postData)
		));

		// Send the request
		$response = curl_exec($ch);

		// Check for errors
		if($response === FALSE){
			die(curl_error($ch));
		}

		// Decode the response
		$responseData = json_decode($response, TRUE);

		// Print the date from the response
		//echo $responseData['published'];
		return "Sukses";
	}
}