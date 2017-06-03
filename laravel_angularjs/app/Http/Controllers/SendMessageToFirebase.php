<?php
namespace App\Http\Controllers;

use App\Http\Controllers\Controller;

class SendMessageToFirebase extends Controller
{
	public function sendMessage(){
		// Your ID and token
		$blogID = '8070105920543249955';
		$authToken = 'key=AAAA1oUTkvM:APA91bGJ5gJ2xJYm9dYvUkJ8NmMq4v-AlFp-R-eBkP71UvgKwc_9aZOko9rLORYsCb0uD7Y56hBijk-otwnwz7ylW9ujFlVMlcAZjbfzinS-DRG7Fs-4acnwstPgeHXYvLMWI_wlNuy3SIuNExRxAt-nUjPu1tpdhg';

		// The data to send to the API
		$postData = array(
			'to' => 'dd9Ye8y5zPk:APA91bGNKHcclbO7lvqfLPob4IRwek-cglr_I4xCiBWBPNSMFd3dhaOSVhY3UCxnCkupZBSM_bXlVlDFRxjoagCIcj6k0F_iYAF9jkowjXZthGbwfL9_qfiw511bWE6w28OsHDClCubP', 
			'priority' => 'normal',
			'time_to_live'=> 108,
			'delay_while_idle' => true,
			'notification'=> array(
							'body' => 'Coba Jasa Service From PHP',
							'title'=> 'Coba Jasa PHP',
							'text'=> 'Ada Service Bro dari PHP'
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
}