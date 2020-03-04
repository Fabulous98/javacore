<?php
//$your_email="moderation@routenote.com";
//$your_email="copyright@youtube.com";
$your_email = "info@brilliantmusic.net";
if(!empty($_POST))
{
	$name=$_POST['name'];
	$email=$_POST['email'];
	$message=$_POST['message'];
	
	$to      = $your_email;
	$subject = 'A Message From Brilliant Music Website!';
	$headers = 'From: '.$name.' <'.$email.'>' . "\r\n";
	$message = $name.' sent you a message via the contact form :'."\r\n".$message;
	//$message = 'Dear Youtube Team,'."\r\n\n".$message;
	
	mail($to, $subject, $message, $headers);
}

?>
