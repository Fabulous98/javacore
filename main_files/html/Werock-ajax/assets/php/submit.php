<?php
$your_email="info@brilliantmusic.net";

if(!empty($_POST))
{
	$name=$_POST['name'];
	$email=$_POST['email'];
	$message=$_POST['message'];
	
	$to      = $your_email;
	$subject = 'Message from Werock: ';
	$headers = 'From: '.$name.' <'.$email.'>' . "\r\n";
	$message = $name.' sent you a message via the contact form :'."\r\n".$message;
	
	mail($to, $subject, $message, $headers);
}

?>