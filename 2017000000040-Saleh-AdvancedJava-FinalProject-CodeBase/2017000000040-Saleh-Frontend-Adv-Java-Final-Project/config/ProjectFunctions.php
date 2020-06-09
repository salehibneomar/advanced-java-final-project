<?php

    class ProjectFunctions{

        public function session_check(){
            if(isset($_SESSION['suid'])){
                return true;
            }
            else{
                return false;
            }
        }

        public function getResponse($api_url){
            $ch = curl_init();

            curl_setopt($ch, CURLOPT_URL, $api_url);
            curl_setopt($ch, CURLOPT_POST, false);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

            $result = curl_exec($ch);
            curl_close($ch);
            $result = json_decode($result, true);

            return $result;
        }

        
        public function getSingleResponseByPostingBody($api_url, $payload){
            $ch = curl_init();

            curl_setopt($ch, CURLOPT_URL, $api_url);
            curl_setopt($ch, CURLINFO_HEADER_OUT, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);

            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Accept: application/json',
                'Content-Type: application/json',
                'Content-Length: ' . strlen($payload))
            );

            $result = curl_exec($ch);
            curl_close($ch);
            $result = json_decode($result, true);

            return $result;
        }

        public function deleteRequest($api_url, $param){
            $ch = curl_init();

            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE"); 
            curl_setopt($ch,CURLOPT_URL,$api_url);
            curl_setopt($ch,CURLOPT_RETURNTRANSFER,true);
            curl_setopt($ch,CURLOPT_HEADER, false); 
            curl_setopt($ch, CURLOPT_POSTFIELDS, $param); 

            $result = curl_exec($ch);
            curl_close($ch);

            return $result;

        }

        public function postRequest($api_url, $payload){
            $ch = curl_init();

            curl_setopt($ch, CURLOPT_URL, $api_url);
            curl_setopt($ch, CURLINFO_HEADER_OUT, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);

            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Accept: application/json',
                'Content-Type: application/json',
                'Content-Length: ' . strlen($payload))
            );

            $result = curl_exec($ch);
            curl_close($ch);

            return $result;
        }

        public function putRequest($api_url, $payload){
            $ch = curl_init();

            curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT"); 
            curl_setopt($ch, CURLOPT_URL, $api_url);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch,CURLOPT_HEADER, false); 
            curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);

            curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Accept: application/json',
                'Content-Type: application/json',
                'Content-Length: ' . strlen($payload))
            );

            $result = curl_exec($ch);
            curl_close($ch);

            return $result;
        }


    }

?>