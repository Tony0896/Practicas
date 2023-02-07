<?php
	$ws = 'https://148.244.239.193:8453/WCFServiceLayer/WCFServiceLayer/api/BussinesPartner/BPToVic';
	$body = '{"DB":"EBE_FINAL"}';
	$ws_do = curl_init();
	curl_setopt($ws_do, CURLOPT_URL, $ws); 
	curl_setopt($ws_do, CURLOPT_POST, true); 
	curl_setopt($ws_do, CURLOPT_TIMEOUT, 0);
	curl_setopt($ws_do, CURLOPT_RETURNTRANSFER, 1);
	curl_setopt($ws_do, CURLOPT_SSL_VERIFYHOST, false); 
	curl_setopt($ws_do, CURLOPT_SSL_VERIFYPEER, false);
	curl_setopt($ws_do, CURLOPT_ENCODING, 'UTF-8');
	curl_setopt($ws_do, CURLOPT_CONNECTTIMEOUT, 10);
	curl_setopt($ws_do, CURLOPT_POSTFIELDS, $body);
    $headers = array(
        "X-Custom-Header: value",
        "Content-Type: application/json",
     );
    curl_setopt($ws_do, CURLOPT_HTTPHEADER, $headers);
	curl_setopt($ws_do, CURLOPT_HEADER, 0);
	$jsonResponse = curl_exec($ws_do);
	curl_close($ws_do);
    $resultado = str_replace('CardCode', 'IdCte', $jsonResponse);
    $resultado = str_replace('CardName', 'Nombre', $resultado);
    $resultado = str_replace('CntctPrsn', 'NombreContacto', $resultado);
    $resultado = str_replace('Phone1', 'Telefono', $resultado);
    $resultado = str_replace('E_Mail', 'Correo', $resultado);
    $json_parsed = json_decode($resultado);
    $resultado = str_replace('null','""', $json_parsed);

    $array = json_decode($resultado);
    $arr= array();

    foreach ($array as $value) {
        $id = $value->IdCte;
        $Nombre = $value->Nombre;
        $NombreContacto = $value ->NombreContacto;
        $Telefono = $value ->Telefono;
        $Correo = $value ->Correo;
        array_push($arr,array("IdCte"=>$id,"Nombre"=>$Nombre,"Telefono"=>$Telefono,"Correo"=>$Correo,"NombreContacto"=>$NombreContacto));
    }
    
    print json_encode($arr, JSON_UNESCAPED_UNICODE);
    die;
?>
